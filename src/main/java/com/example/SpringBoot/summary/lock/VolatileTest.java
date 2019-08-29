package com.example.springboot.summary.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS以及concurrent包原子类的测试类
 * CAS:它涉及到三个操作数：内存值、预期值、新值。当且仅当预期值和内存值相等时才将内存值修改为新值 。
 * CAS的三个缺点：
 *     1. 只能保证对一个共享变量的原子操作       解决： 1. JDK1.5以后AtomicReference类可以保证引用对象之间的原子性，可以把多个变量放在一个对象里来进行 CAS 操作
 *                                                 2.使用锁
 *     2. ABA问题       解决：JDK1.5以后的AtomicStampedReference 类：相当于是带有版本标记的CAS实现
 *                          以原子的方式对 reference和stamp的Pair 进行 CAS（底层调用的是 native compareAndSwapObject()方法 native方法：JNI调用native方法调用由C++编写的硬件级别指令）
 *     3. CAS是一个自旋操作(不成功就一直循环重试)。如果长时间不成功，会给CPU带来非常大的执行开销
 *
 * @author duwenxu
 * @create 2019-08-28 14:58
 */
public class VolatileTest {
    /**
     * 可见性：
     *
     * volatile 关键字：保证可见性，但不保证操作的原子性
     *     Volatile实现内存可见性是通过store和load指令完成的；也就是对volatile变量执行写操作时，会在写操作后加入一条store指令，即强迫线程将最新的值刷新到主内存中；
     *     而在读操作时，会加入一条load指令，即强迫从主内存中读入变量的值。但volatile不保证volatile变量的原子性，例如：
     *     private int num=0;
     *     num++;  /  num=num+1
     *     因为自增不是一个原子操作，而可以分为3步： 1.读取num的值  2.值+1  3. 写入最新的num的值
     *
     * Synchronized关键字：保证可见性和原子性
     *     Synchronized能够实现原子性和可见性；在Java内存模型中，synchronized规定，线程在加锁时
     *     先清空工作内存-->在主内存中拷贝最新变量的副本到工作内存-->执行完代码-->将更改后的共享变量的值刷新到主内存中-->释放互斥锁
     *
     * Synchronized和Volatile的比较
     *     1）Synchronized保证内存可见性和操作的原子性
     *     2）Volatile只能保证内存可见性
     *     3）Volatile不需要加锁，比Synchronized更轻量级，并不会阻塞线程（volatile不会造成线程的阻塞；synchronized可能会造成线程的阻塞。）
     *     4）volatile标记的变量不会被编译器优化,而synchronized标记的变量可以被编译器优化（如编译器重排序的优化）.
     *     5）volatile是变量修饰符，仅能用于变量，而synchronized是一个方法或块的修饰符。
     *       volatile本质是在告诉JVM当前变量在寄存器中的值是不确定的(见文知意：volatile:不稳定的)，使用前，需要先从主存中读取，因此可以实现可见性。
     *       而对n=n+1,n++等操作时，volatile关键字将失效，不能起到像synchronized一样的线程同步（原子性）的效果。
     *
     *
     */
//    public static volatile int race = 0;   //volatile不能保证原子性所以
    public static AtomicInteger race = new AtomicInteger(0);
    private static final int THREADS_COUNT = 20;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

    public static void increase() {
//        race++;
        race.getAndIncrement(); //相当于 普通Int类型的 自增操作
        /**
         * getAndIncrement()-->getAndAddInt()-->compareAndSwapInt() 原子类最终还是通过CAS来实现的
         */
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    /**
                     * CountDownLatch是一个计数器闭锁，通过它可以完成类似于阻塞当前线程的功能，即：一个线程或多个线程一直等待，直到其他线程执行的操作完成。
                     *      初始化:可以用一个给定的计数器（count值）来初始化，该计数器的操作是原子操作，即同时只能有一个线程去操作该计数器
                     *      await方法：await的线程会一直处于阻塞状态，直到其他线程调用countDown方法使当前计数器的值变为零
                     *      countDown:每次调用countDown方法使计数器的值减一，当计数器的值减为0时，释放所有阻塞的线程继续执行
                     *      CountDownLatch只会出现一次计数器为0的情况，因为他的计数器不能被重置。  CycliBarrier：可重置计数器次数
                     */
                    countDownLatch.countDown();
                }
            });
            threads[i].start();
        }
        countDownLatch.await();
//        countDownLatch.await(2000, TimeUnit.MILLISECONDS);//设置超过指定时间就 跳过等待直接向下执行
        System.out.println(race);
        System.out.println(System.currentTimeMillis());
    }
}
