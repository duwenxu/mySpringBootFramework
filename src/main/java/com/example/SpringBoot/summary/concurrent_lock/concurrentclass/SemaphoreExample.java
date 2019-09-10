package com.example.springboot.summary.concurrent_lock.concurrentclass;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore类 使用示例
 *
 * @author duwenxu
 * @create 2019-09-09 14:03
 */
public class SemaphoreExample {
    private static final int threadCount = 200;

    /**
     * 较复杂的构造函数： public Semaphore(int permits, boolean fair)
     *                      permits  定义一次只能允许执行的线程数量
     *                      fair  定义实现机制 公平或非公平 (默认是 not fair)
     *                            两者区别： not fair非公平锁: 当可用许可数少于需要的许可数就 acquire-------------抢占式      直接调用protected int tryAcquireShared(int acquires)
     *                                      fair公平锁: 除了满足not fair的条件外，还需要满足仅当本线程在当前队列中没有前置节点时才 acquire成功---------FIFO      先判断队列条件，再调用protected int tryAcquireShared(int acquires)
     *
     *  Semaphore经常用于限制获取(或访问)某种资源的线程数量。
     */
    private static final Semaphore semaphore = new Semaphore(5, false);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(300);

        for (int i = 0; i < threadCount; i++) {
            int threadNum = i;
            threadPool.execute(() -> {
                try {
                    /**
                     * permits: 执行每个线程所需要的许可数
                     *          可同时访问资源的线程数：初始化时的permits总数/acquire()需要的数目
                     *                              此处 20/3=6 .即最多6个线程同时访问资源
                     */
                    semaphore.acquire(3);
//                    semaphore.acquire(3);
//                    semaphore.tryAcquire();   //获取不到许可就立即返回false
                    test(threadNum);
                    semaphore.release(2);  //当每次的permits释放数目小于 需要数目时运行一定次数后。 因为permits数目不足，acquire()方法将阻塞
                    System.out.println(Thread.currentThread().getName()+" :许可已释放");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName()+": finish");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+":time--"+System.currentTimeMillis());
        Thread.sleep(1000);
    }
}
