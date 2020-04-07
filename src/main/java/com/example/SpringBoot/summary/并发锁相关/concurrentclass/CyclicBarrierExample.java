package com.example.springboot.summary.并发锁相关.concurrentclass;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 类示例
 *
 * @author duwenxu
 * @create 2019-09-09 13:34
 */
public class CyclicBarrierExample {

    private static final int threadCount=300;

    /**
     * 构造方法：  public CyclicBarrier(int parties, Runnable barrierAction)
     *              parties  定义了每组到达屏障需要的线程数
     *              barrierAction 定义了线程数到达屏障后优先执行的代码逻辑
     */
    private static final CyclicBarrier cyclicBarrier=new CyclicBarrier(5, () -> System.out.println("线程数达到设定值后的优先执行逻辑--"));  //

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            Thread.sleep(1000);
            int threadNum=i;
            threadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();  //关闭线程池
    }

    private static void test(int threadNum) throws BrokenBarrierException, InterruptedException {
        System.out.println("threadNum: "+threadNum+" is ready");
        cyclicBarrier.await();  //await() 方法表示当前线程通知CyclicBarrier 已到达了屏障
        System.out.println("threadNum: "+threadNum+" is finish");
    }

}
