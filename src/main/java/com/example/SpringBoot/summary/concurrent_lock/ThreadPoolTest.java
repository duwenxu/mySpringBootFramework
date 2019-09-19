package com.example.springboot.summary.concurrent_lock;

import java.util.concurrent.*;

/**
 * concurrent 包中线程池相关原理和使用 (详情见  https://www.cnblogs.com/xiaoxi/p/7692250.html)
 * 继承/实现结构：
 *      Executor<----ExecutorService<----AbstractExecutorService<----ThreadPoolExecutor
 * <p>
 * java通过Executor是提供4种线程池，分别为：
 * 1）newCachedThreadPool：创建一个可缓存的线程池，有任务来临时如果线程池中有空闲的线程，那么就使用空闲的线程执行任务（即线程是可以复用），如果没有空闲线程则创建新的线程执行任务。
 *         corePoolSize设置为0，意味着所有的任务都会直接进入缓存(等待)队列
 *         maximumPoolSize 最大线程数设置为 Integer.MAX_VALUE ,意味着线程数量是没有限制的
 *         KeepAlive时间被设置成60秒，意味着在没有任务的时候线程等待60秒以后退出
 *         对任务的处理策略是提交的任务会立即分配一个线程进行执行，线程池中线程数量会随着任务数的变化自动扩张和缩减，在任务执行时间无限延长的极端情况下会创建过多的线程。
 * 2）newFixedThreadPool：创建一个定长的线程池，线程池的线程数量固定，当任务来临，但是又没有空闲线程，则把任务放入队列中等待直到有空闲线程来处理它。
 *         a. corePoolSize 和 maximumPoolSize 设置成了相同的值
 *         b. 任务队列无界，即使用的是不限制大小的 LinkedBlockingQueue(即不去指定其容量大小，则其容量大小默认为Integer.MAX_VALUE)   ----------> 当前线程数达到 corePoolSize 时，由于任务存储队列无界，可以将其他任务都放入等待队列中
 *         行为如下：
 *              1）从线程池中获取可用线程执行任务，如果没有可用线程则使用ThreadFactory创建新的线程，直到线程数达到nThreads。
 *              2）线程池线程数达到nThreads以后，新的任务将被放入队列。
 *               FixedThreadPool的优点是能够保证所有的任务都被执行，永远不会拒绝新的任务；
 *                            同时缺点是队列数量没有限制，在任务执行时间无限延长的这种极端情况下会造成内存问题。
 * 3）newScheduledThreadPool：创建一个定长的线程，但是能支持定时或周期性的执行。
 * 4）newSingleThreadPool：创建一个单线程化的线程池，线程池中只有一个唯一的线程来执行任务，保证所有任务按照指定顺序（FIFO,LIFO,优先级）执行。
 *          这个工厂方法中使用无界LinkedBlockingQueue，并且将线程数设置成1，除此以外还使用FinalizableDelegatedExecutorService类进行了包装。
 *          这个包装类的主要目的是为了屏蔽ThreadPoolExecutor中动态修改线程数量的功能，仅保留ExecutorService中提供的方法。
 *          虽然是单线程处理，一旦线程因为处理异常等原因终止的时候，ThreadPoolExecutor会自动创建一个新的线程继续进行工作。
 * <p>
 * 阿里规范：
 * 在实际应用中，最好是通过 ThreadPoolExecutor 自定义创建线程，上述线程底层都是使用 ThreadPoolExecutor创建的，只不过增加了许多限制
 * ThreadPoolExecutor类中其他的一些比较重要成员变量：
 * <p>
 * ThreadPoolExecutor类中比较重要的一些成员变量：
 * private final BlockingQueue<Runnable> workQueue;              //任务缓存队列，用来存放等待执行的任务
 *          阻塞队列：
 *          阻塞队列一般有以下几种：
 *                  ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小
 *                  LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE
 *                  SynchronousQueue：一个只有1个元素的队列，入队的任务需要一直等待直到队列中的元素被移出
 * private final ReentrantLock mainLock = new ReentrantLock();   //线程池的主要状态锁，对线程池状态（比如线程池大小
 * //、runState等）的改变都要使用这个锁
 * private final HashSet<Worker> workers = new HashSet<Worker>();  //用来存放工作集
 * private volatile long  keepAliveTime;    //线程存活时间
 * private volatile boolean allowCoreThreadTimeOut;   //是否允许为核心线程设置存活时间
 * private volatile int   corePoolSize;     //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
 * private volatile int   maximumPoolSize;   //线程池最大能容忍的线程数
 * private volatile int   poolSize;       //线程池中当前的线程数
 * private volatile RejectedExecutionHandler handler; //任务拒绝策略
 *          拒绝处理任务时的策略：
 *                  ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 *                  ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 *                  ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 *                  ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 * private volatile ThreadFactory threadFactory;   //线程工厂，用来创建线程
 * private int largestPoolSize;   //用来记录线程池中曾经出现过的最大线程数
 * private long completedTaskCount;   //用来记录已经执行完毕的任务个数
 *
 *ThreadPoolExecutor中几个重要的方法：
 *          execute()：Executor顶层接口中声明的唯一方法。是ThreadPoolExecutor的核心方法，通过这个方法可以向线程池提交一个任务，交由线程池去执行。
 *          submit()：也是用来向线程池提交任务的，但是它和execute()方法不同，它能够返回任务执行的结果。实际上还是调用的execute()方法，只不过它利用了Future来获取任务执行结果
 *          shutdown()：---------关闭线程池  线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；
 *          shutdownNow()：------           线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；
 *          线程池中线程数初始化：
 *              prestartCoreThread()：初始化一个核心线程；
 *              prestartAllCoreThreads()：初始化所有核心线程
 *          动态调整线程池容量：
 *              setCorePoolSize：设置核心池大小
 *              setMaximumPoolSize：设置线程池最大能创建的线程数目大小
 *
 *
 * 任务提交后线程池执行情况：
 *      1. 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 *      2. 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 *      3. 如果当前线程池中的线程数目达到 maximumPoolSize，则会采取任务拒绝策略进行处理；
 *      4. 如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止,直到核心池中的线程数减少到0。
 *
 *
 * @author duwenxu
 * @create 2019-09-17 11:31
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
//        scheduledThreadPoolTest();
        customThreadPoolTest();
    }

    private static void scheduledThreadPoolTest() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; i++) {
            int index = i;
            ses.scheduleWithFixedDelay(() -> System.out.println("当前线程：" + Thread.currentThread().getName() + "index:" + index), 1, 3, TimeUnit.SECONDS);
            if (index==50){
                ses.shutdownNow();
            }
        }
        ses.shutdown();
    }

    /**
     * 使用 ThreadPoolExecutor 进行自定义线程池
     */
    private static void customThreadPoolTest() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 100; i++) {
            int index = i;
            threadPool.execute(() -> System.out.println("当前线程：" + Thread.currentThread().getName() + "      index:" + index));
        }
        threadPool.shutdown();
    }

}