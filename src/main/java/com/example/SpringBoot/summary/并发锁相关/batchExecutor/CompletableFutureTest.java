package com.example.springboot.summary.并发锁相关.batchExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture 执行异步任务Demo
 * <p>
 * CompletableFuture 类声明了 CompletionStage 接口，CompletionStage 接口实际上提供了同步或异步运行计算的舞台
 * <p>
 * 1.使用工厂方法创建CompletableFuture
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * <p>
 * 方法区别：
 * 1.方法参数中没有Executor时，将会使用ForkJoinPool.commonPool()
 * 2.Runnable 接口方法 public abstract void run(); 没有返回值
 * Supplier 接口方法 T get(); 有返回值。如果你需要处理异步操作并返回结果，使用前两种 Supplier<U> 方法
 * <p>
 * 2.转换和作用与异步任务的结果（可以叠加功能，将多个future组合在一起）  相当于回调函数
 * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 * <p>
 * thenApply:当一个线程依赖另一个线程时,获取上一个任务返回的结果,并返回当前任务的返回值
 * 用于将该阶段的计算结果作为参数传递给fn,并会返回一个新的 CompletionStage
 * <p>
 * 3.运行完成的异步任务的结果 (thenAccept/thenRun)
 * 类似的也有3个重载方法：同步，异步，异步指定线程池
 * 异步和非异步的区别：
 * thenAccept: 执行当前任务的线程执行继续执行thenAccept的任务
 * thenAcceptAsync: 把thenAcceptAsync这个任务提交给线程池来执行
 * <p>
 * thenRun:处理完任务后执行thenRun后面的方法
 * thenAccept:消费处理结果。接受任务的执行结果并消费处理,无返回结果
 * <p>
 * 4.结合两个 CompletableFuture （thenCompose和thenCombine）
 * <p>
 * 5.并行执行多个异步任务
 * 等待所有异步任务结束，然后组合解结果  使用 allOf()
 * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
 * <p>
 * 只要任何一个任务完成就返回结果   使用anyOf()
 * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
 * <p>
 * <p>
 * 6.whenComplete(计算完成时回调方法)
 * public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor)
 * public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
 * <p>
 * whenComplete可以感知正常和异常的计算结果,无异常时直接返回结果,在感知到异常时使用exceptionally处理异常情况
 * whenComplete和whenCompleteAsync的区别:
 * whenComplete : 是执行当前任务的线程执行继续执行whenComplete的任务
 * whenCompleteAsync :是执行把whenCompleteAsync这个任务继续提交给线程池来执行
 * 方法不以Async结尾,意味着Action使用相同的线程执行,而Async可能会使用其他线程执行(如果是使用相同的线程池也可能会被同一个线程选中执行)
 *
 * @author duwenxu
 * @create 2020-06-19 14:32
 */
public class CompletableFutureTest {

    private static Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);

    public static void main(String[] args) throws Exception {
//        CompletableFutureTest();
        whenCompleteTest();
    }

    private static void whenCompleteTest() throws Exception {
        ExecutorService stealingPool = Executors.newWorkStealingPool();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            logger.error("当前线程：" + Thread.currentThread().getName());
            int res = 10 / 0;
            return res;
        }, stealingPool)
                //两个参数分别可以感知正常和异常的计算结果，
                .whenCompleteAsync((data, throwable) -> {
                    logger.error("计算结果为：" + data);
                    logger.error("异常为：" + throwable + " 异常信息：" + throwable.getMessage());
                })
                .exceptionally(throwable -> {
                            return 10;
                        }
                );

        Integer integer = completableFuture.get();
        logger.error("get得到的计算结果为："+ integer);
    }


    private static void CompletableFutureTest() throws InterruptedException, ExecutionException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            logger.error("获取数据任务开始");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.error("拿到数据");
            return 10;
        }).thenApplyAsync(data -> {
            logger.error("对拿到的数进{1}行处理", data);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data << 2;
        }).whenCompleteAsync((data, throwable) -> logger.error("最终的数据" + data));

        logger.error("主线程执行耗时任务-1");
        Thread.sleep(3000);
        logger.error("主线程耗时任务完成-2");
        Thread.sleep(5000);
        logger.error("主线程任务执行完毕-3");

        completableFuture.get();
    }
}