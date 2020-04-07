package com.example.springboot.summary.并发锁相关.threadpool;


import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author duwenxu
 * @create 2019-09-23 17:11
 */
public class CompletionServiceTest {

    /**
     * Runtime.getRuntime() 返回当前应用程序的运行环境对象    只可获取，无法自己创建
     * Runtime.getRuntime().availableProcessors()    获取java虚拟机的可用处理器个数
     */
    private static final int DEFAULT_POOL_SIZE=Runtime.getRuntime().availableProcessors()<=1?3:Runtime.getRuntime().availableProcessors()*2;
    private static int MAX_POOL_SIZE=32;
    private static long KEEP_ALIVE_TIME=30;
    private static int TOTAL_TASK=Runtime.getRuntime().availableProcessors()*10;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(DEFAULT_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    /**
     * 使用阻塞队列的方式获取执行结果
     * @throws Exception
     */
    private static void callableAndFutureTest() throws Exception {
        long startTime = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        LinkedBlockingQueue<Future<Integer>> taskResultQueue = new LinkedBlockingQueue<>();

        //线程池提交任务后，并将任务结果Future<>加入阻塞队列中
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future = threadPoolExecutor.submit(() -> {
                int sleepTime = new Random().nextInt(1000);
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + "sleep " + sleepTime  + " ms");
                return sleepTime;
            });
            taskResultQueue.add(future);
        }

        //从队列中获取执行结果，统计消耗时间
        for (int i = 0; i < TOTAL_TASK; i++) {
            int processSleepTime = taskResultQueue.take().get();
            count.addAndGet(processSleepTime);
        }

        threadPoolExecutor.shutdown();
        System.out.println("The total sleepTime of task1: "+ count.get()+"---speed time: "+(System.currentTimeMillis()-startTime));
    }

    private static void completionServiceTest() throws Exception {
        long startTime = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);

        CompletionService<Integer> service = new ExecutorCompletionService<>(threadPoolExecutor);
        for (int i = 0; i < TOTAL_TASK; i++) {
            service.submit(() -> {
                int sleepTime = new Random().nextInt(1000);
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + ":sleep " + sleepTime + " ms");
                return sleepTime;
            });
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            Integer processSleepTime = service.take().get();
            count.addAndGet(processSleepTime);
        }

        threadPoolExecutor.shutdown();
        System.out.println("The total sleepTime of task1: "+ count.get()+"---speed time: "+(System.currentTimeMillis()-startTime));
    }



    public static void main(String[] args) throws Exception {
//        callableAndFutureTest();
        completionServiceTest();
    }
}
