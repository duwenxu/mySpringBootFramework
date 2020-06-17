package com.example.springboot.summary.并发锁相关.threadpool;

import java.util.concurrent.*;

/**
 * concurrent 包中线程池相关原理和使用 (详情见  https://www.cnblogs.com/xiaoxi/p/7692250.html)
 * 继承/实现结构：(父类/接口<-----子类/接口)
 *      Executor<----ExecutorService<----AbstractExecutorService<----ThreadPoolExecutor
 *      concurrent包中其它相关类的结构：
 *              CompletionServiceTest<-----ExecutorCompletionService
 *              ForkJoinPool<-----AbstractExecutorService               ForkJoinPool:拆分进行并行计算并合并计算结果
 *              Future和Callable         使用callable接口使线程具有返回值，并通过Future获取返回值
 *
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
 *               FixedThreadPool的优点是能够保证所有的任务都被执行，永远不会拒绝新的任务；(corePoolSize与maximumPoolSize相等，永远都不会走到当前线程数与maximumPoolSize比较这一步，不会采取拒绝策略)
 *                            同时缺点是队列数量没有限制，在任务执行时间无限延长的这种极端情况下会造成内存问题。
 * 3）newScheduledThreadPool：创建一个定长的线程，但是能支持定时或周期性的执行。
 * 4）newSingleThreadPool：创建一个单线程化的线程池，线程池中只有一个唯一的线程来执行任务，保证所有任务按照指定顺序（FIFO,LIFO,优先级）执行。
 *          这个工厂方法中使用无界LinkedBlockingQueue，并且将线程数设置成1，除此以外还使用FinalizableDelegatedExecutorService类进行了包装。
 *          这个包装类的主要目的是为了屏蔽ThreadPoolExecutor中动态修改线程数量的功能，仅保留ExecutorService中提供的方法。
 *          虽然是单线程处理，一旦线程因为处理异常等原因终止的时候，ThreadPoolExecutor会自动创建一个新的线程继续进行工作。
 *          SingleThreadExecutor 适用于：
 *               在逻辑上需要单线程处理任务的场景，同时无界的LinkedBlockingQueue保证新任务都能够放入队列，不会被拒绝；缺点和FixedThreadPool相同，当处理任务无限等待的时候会造成内存问题。
 * 5）jdk1.8新增
 * <p>
 * 阿里规范：
 * 在实际应用中，最好是通过 ThreadPoolExecutor 自定义创建线程，上述线程底层都是使用 ThreadPoolExecutor创建的，只不过增加了许多限制
 * ThreadPoolExecutor类中其他的一些比较重要成员变量：
 * <p>
 * ThreadPoolExecutor类中比较重要的一些成员变量：
 * private final BlockingQueue<Runnable> workQueue;              //任务缓存队列，用来存放等待执行的任务
 *          阻塞队列：
 *          阻塞队列一般有以下几种：
 *                  ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小。
 *                      对于数组阻塞队列，可以选择是否需要公平性
 *                  LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE
 *                  SynchronousQueue：是一个缓存值为1的阻塞队列，但是SynchronousQueue内部并没有数据缓存空间，数据是在配对的生产者和消费者线程之间直接传递的。
 *                                    一个比较特殊的队列，虽然它是无界的，但它不会保存任务，每一个新增任务的线程必须等待另一个线程取出任务
 * private final ReentrantLock mainLock = new ReentrantLock();   //线程池的主要状态锁，对线程池状态（比如线程池大小、runState等）的改变都要使用这个锁
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
 *      也就是：
 *      如果运行的线程少于 corePoolSize，则 Executor 始终首选添加新的线程，而不进行排队。（如果当前运行的线程小于corePoolSize，则任务根本不会存放，添加到queue中，而是直接抄家伙（thread）开始运行）如果运行的线程等于或多于 corePoolSize，则 Executor 始终首选将请求加入队列，而不添加新的线程。如果无法将请求加入队列，则创建新的线程，除非创建此线程超出 maximumPoolSize，在这种情况下，任务将被拒绝。
 *
 * 任务在阻塞队列中的排队策略：
 *      1. 直接提交： 即使用 SynchronousQueue，相当于只是中转任务，它将任务直接提交给线程而不保持它们。
 *                   直接提交通常要求无界 maximumPoolSizes 以避免拒绝新提交的任务。因为最大线程数量是无界的，即可以无限的去创建线程，因此其吞吐量要高于LinkedBlockingQueue 和 ArrayBlockingQueue。
 *                   因为maximumPoolSizes线程数量为Integer.MAX_VALUE，所以为了根据任务的流量动态调节线程数目，应当设置 keepAliveTime,使得当任务减少时可以终止多余的线程。
 *                   例如： CachedThreadPool
 *      2. 无界队列：
 *                   默认定义的 LinkedBlockingQueue 就是无界队列。在当前线程池线程繁忙时，任务都会被加入队列中等待。
 *                   存在问题：等待任务过多时会存在内存空间耗尽的问题
 *      3. 有界队列：
 *                   使用有界的阻塞队列(如 ArrayBlockingQueue)，可以防止内存资源耗尽，但比较难以协调队列与线程池大小关系。
 *                   一般来说：使用大型队列和小型池可以最大限度地降低 CPU 使用率、操作系统资源和上下文切换开销，但是会降低吞吐量。
 *                            使用小型队列和大型池cpu使用率较高
 *
 *
 * 关于线程池常见的几个问题：
 * 线程池的执行流程?
 * 为什么newFixedThreadPool中要将corePoolSize和maximumPoolSize设置成一样？ 答：因为newFixedThreadPool中用的是LinkedBlockingQueue（是无界队列），只要当前线程大于等于corePoolSize来的任务就直接加入到无界队列中，所以线程数不会超过corePoolSize，这样maximumPoolSize没有用。例如，在 Web 页服务器中。这种排队可用于处理瞬态突发请求，当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性。
 * 为什么newFixedThreadPool中队列使用LinkedBlockingQueue？答：设置的corePoolSize 和 maximumPoolSize相同，则创建的线程池是大小固定的，要保证线程池大小固定则需要LinkedBlockingQueue（无界队列）来保证来的任务能够放到任务队列中，不至于触发拒绝策略。
 * 为什么newFixedThreadPool中keepAliveTime会设置成0？因为corePoolSize和maximumPoolSize一样大，KeepAliveTime设置的时间会失效，所以设置为0。
 * 为什么newCachedThreadPool中要将corePoolSize设置成0？答:因为队列使用SynchronousQueue，队列中只能存放一个任务，保证所有任务会先入队列，用于那些互相依赖的线程，比如线程A必须在线程B之前先执行。
 * 为什么newCachedThreadPool中队列使用SynchronousQueue？答：线程数会随着任务数量变化自动扩张和缩减，可以灵活回收空闲线程，用SynchronousQueue队列整好保证了CachedTheadPool的特点。
 * 为什么newSingleThreadExecutor中使用DelegatedExecutorService去包装ThreadPoolExecutor？答：SingleThreadExecutor是单线程化线程池，用DelegatedExecutorService包装为了屏蔽ThreadPoolExecutor动态修改线程数量的功能，仅保留Executor中的方法。
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