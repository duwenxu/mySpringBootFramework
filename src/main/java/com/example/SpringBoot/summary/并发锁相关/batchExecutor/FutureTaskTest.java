package com.example.springboot.summary.并发锁相关.batchExecutor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 并发执行器 Future和Callable 使用
 *
 * @author duwenxu
 * @create 2020-04-02 19:01
 */
public class FutureTaskTest {
    public static void main(String[] args) {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(6000);
                return new Random().nextInt();
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(futureTask.isDone());
            System.out.println(futureTask.get());
            System.out.println(futureTask.isDone());
            System.out.println("hello end");
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
