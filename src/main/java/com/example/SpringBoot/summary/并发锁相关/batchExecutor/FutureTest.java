package com.example.springboot.summary.并发锁相关.batchExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author duwenxu
 * @create 2020-06-19 14:28
 */
public class FutureTest {

    private static Logger logger = LoggerFactory.getLogger(FutureTest.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(() -> {
            logger.error("异步future测试开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.error("异步future测试结束");
            return 10;
        });

        Integer res = submit.get();
        System.out.println("最终计算结果:"+res);
    }
}
