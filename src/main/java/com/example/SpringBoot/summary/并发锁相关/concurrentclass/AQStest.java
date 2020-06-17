package com.example.springboot.summary.并发锁相关.concurrentclass;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * AQS相关原理和理解
 *
 *  1.AQS的核心思想：
 *         如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。
 *         如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制AQS是用CLH队列锁实现的，即将暂时获取不到锁的线程加入到队列中
 *              CLH队列锁：一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。
 *                        AQS是将每条请求共享资源的线程封装成一个CLH锁队列的一个结点（Node）来实现锁的分配。
 *          a.同步状态： AQS使用一个int成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。
 *                          private volatile int state;//共享变量，使用volatile修饰保证线程可见性
 *                      AQS使用CAS对该同步状态进行原子操作实现对其值的修改。
 *
 *  2.AQS的设计模式：模板方法模式(基于继承的行为模式)
 *      同步器的设计是基于模板方法模式的，如果需要自定义同步器一般的方式是这样（模板方法模式很经典的一个应用）：
 *          a. 使用者继承AbstractQueuedSynchronizer并重写指定的方法。（这些重写方法很简单，无非是对于共享资源state的获取和释放）
 *          b. 将AQS组合在自定义同步组件的实现中，并调用其模板方法，而这些模板方法会调用使用者重写的方法。
 *              模板方法模式：在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。
 *                           它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
 *  3. AQS定义两种资源共享方式
 *          Exclusive（独占）：只有一个线程能执行，如ReentrantLock。又可分为公平锁和非公平锁：
 *                  公平锁：按照线程在队列中的排队顺序，先到者先拿到锁
 *                  非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
 *          Share（共享）：多个线程可同时执行，如 Semaphore/CountDownLatch
 *  4. 几种同步器的比较：
 *      ReentrantLock(重入锁)：
 *                     state初始化为0，表示未锁定状态
 *                     调用tryAcquire()独占该锁并将state+1;
 *                     调用tryRelease()释放该锁并将state-1;
 *                     直到state重新为0时，其他线程将有机会获得锁；(自身可以重复获取此锁的（state会累加）：可重入。但要注意：获取多少次就要释放多么次，这样才能保证state是能回到零态的。)
 *     CountDownLatch(倒计时器)(重要)：
 *                     是一个计数器闭锁，通过它可以完成类似于阻塞当前线程的功能，即：一个线程或多个线程一直等待，直到其他线程执行的操作完成。
 *                      *      初始化:可以用一个给定的计数器（count值）来初始化，该计数器的操作是原子操作，即同时只能有一个线程去操作该计数器
 *                      *      await方法：await的线程会一直处于阻塞状态，直到其他线程调用countDown方法使当前计数器的值变为0
 *                      *      countDown:每次调用countDown方法使计数器的值减一(CAS原子操作减一)，当计数器的值减为0时，释放所有阻塞的线程继续执行
 *                      *      CountDownLatch只会出现一次计数器为0的情况，因为他的计数器不能被重置。  CycliBarrier：可重置计数器次数
 *             常见问题：1.解释一下CountDownLatch概念？
 *                      2.CountDownLatch 和CyclicBarrier的不同之处？
 *                              CountDownLatch: 一个或者多个线程，等待其他多个线程完成某件事情之后才能执行
 *                              CyclicBarrier : 多个线程互相等待，直到到达同一个同步点，再继续一起执行
 *                              CountDownLatch是计数器，线程完成一个记录一个，只不过计数不是递增而是递减，而CyclicBarrier更像是一个阀门，需要所有线程都到达，阀门才能打开，然后继续执行
 *                      3.给出一些CountDownLatch使用的例子？
 *                          CountDownLatch 的三种典型用法：
 *                              ①某一线程在开始运行前等待n个线程执行完毕。将 CountDownLatch 的计数器初始化为n ：new CountDownLatch(n) ，每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
 *                              ②实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个******共享的(重点)****** CountDownLatch 对象，将其计数器初始化为 1 ：new CountDownLatch(1) ，多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。
 *                              ③死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试产生死锁。
 *                      4.CountDownLatch 类中主要的方法？
 *
 *     Semaphore(信号量):Semaphore是信号量，用于管理一组资源。其内部是基于AQS的共享模式，AQS的状态表示许可证的数量，在许可证数量不够时，线程将会被挂起；
 *                      而一旦有一个线程释放一个资源，那么就有可能重新唤醒等待队列中的线程继续执行。
 *     CyclicBarrier(循环栅栏)：可重复进行的，多个线程之间互相等待直到一定数目(定义为屏障)的线程数到达屏障后再进行接下来的操作。
 *                             在线程数目达到设置的数目之前，每个线程都通过await()方法通知已到达屏障。
 *     ReentrantReadWriteLock(可重入读写锁)：
 *     StampedLock:
 *
 *
 *
 *
 *
 *
 *
 */
public class AQStest extends AbstractQueuedLongSynchronizer {
    /**
     * 独占方式。尝试获取资源，成功则返回true，失败则返回false。
     * @param arg
     * @return
     */
    @Override
    protected boolean tryAcquire(long arg) {
        return  super.tryAcquire(arg);
    }

    /**
     * 独占方式。尝试释放资源，成功则返回true，失败则返回false
     * @param arg
     * @return
     */
    @Override
    protected boolean tryRelease(long arg) {
        return  super.tryRelease(arg);
    }

    /**
     * 共享方式。尝试获取资源。
     * 负数表示失败；
     * 0表示成功，但没有剩余可用资源；
     * 正数表示成功，且有剩余资源
     * @param arg
     * @return
     */
    @Override
    protected long tryAcquireShared(long arg) {
        return super.tryAcquireShared(arg);
    }

    /**
     * 共享方式。尝试释放资源，
     * 成功则返回true，失败则返回false
     * @param arg
     * @return
     */
    @Override
    protected boolean tryReleaseShared(long arg) {
        return super.tryReleaseShared(arg);
    }

    /**
     * 该线程是否正在独占资源。只有用到condition才需要去实现它
     * @return
     */
    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }
}
