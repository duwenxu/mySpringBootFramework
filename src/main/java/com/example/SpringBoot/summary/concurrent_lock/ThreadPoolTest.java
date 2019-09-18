package com.example.springboot.summary.concurrent_lock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * concurrent 包中线程池相关原理和使用
 *
 * java通过Executor是提供4种线程池，分别为：
 * 1）newCachedThreadPool：创建一个可缓存的线程池，有任务来临时如果线程池中有空闲的线程，那么就使用空闲的线程执行任务（即线程是可以复用），如果没有空闲线程则创建新的线程执行任务。
 * 2）newFixedThreadPool：创建一个定长的线程池，线程池的线程数量固定，当任务来临，但是又没有空闲线程，则把任务放入队列中等待直到有空闲线程来处理它。
 * 3）newScheduledThreadPool：创建一个定长的线程，但是能支持定时或周期性的执行。
 * 4）newSingleThreadPool：创建一个单线程化的线程池，线程池中只有一个唯一的线程来执行任务，保证所有任务按照指定顺序（FIFO,LIFO,优先级）执行。
 *
 * @author duwenxu
 * @create 2019-09-17 11:31
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        scheduledThreadPoolTest();
    }

    private static void scheduledThreadPoolTest() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; i++) {
            int index=i;
            ses.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程："+Thread.currentThread().getName()+"index:"+index);
                }
            }, 1,3, TimeUnit.SECONDS);
        }
    }
}
