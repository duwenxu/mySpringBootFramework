package com.example.springboot.summary.concurrent_lock.threadpool;


/**
 * 线程池中使用到的阻塞队列
 *
 * 阻塞队列：
 *      在队列的基础特性上附加了两个操作：(阻塞的意义)
 *          1. 当从队列中获取或者移除元素时，如果队列为空，需要等待，直到队列不为空；
 *          2. 如果向队列中添加元素时，此时如果队列无可用空间，也需要等待
 *       使用场景：
 *          常用于生产者和消费者的场景(与消息中间件的功能相似)。由于阻塞的存在，队列会自动地平衡负载。
 *
 * @author duwenxu
 * @create 2019-09-20 8:42
 */
public class BlockingQueue {

}