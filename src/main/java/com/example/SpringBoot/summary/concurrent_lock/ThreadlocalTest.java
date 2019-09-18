package com.example.springboot.summary.concurrent_lock;

/**
 * ThreadLocal 类的原理、使用
 * @author duwenxu
 * @create 2019-09-17 11:30
 *
 * ThreadLocal提供了在线程内部存储变量的能力，这些变量不同于一般变量之处在于每个线程读取到的变量内容是相互独立的。
 * ThreadLocal实现原理：
 *      1.思想： ThreadLocal与Synchronized 的异同点：(应用场景不同)
 *          相同点：都解决的是线程间的变量冲突问题。
 *          不同点： Synchronized：悲观锁的实现，进行同一资源在线程间共享时的信息同步，避免线程间的变量信息冲突
 *                  ThreadLocal：解决的是每个线程都需要一个单独的变量进行统一操作，但该变量只能在线程内共享，线程间隔离。即如何优雅的解决 变量在线程间隔离与线程内共享的问题。(不需要每个线程去主动的创建该对象)
 *                               当某些数据是以线程为作用域并且不同线程具有不同的数据副本的时候，就可以考虑采用ThreadLocal
 *      2.具体的实现：
 *          ThreadLocal的 静态内部类(static class) ThreadLocalMap为每个Thread维护了一个Entry数组table,每个ThreadLocal根据其key值确定了一个数组下标，而这个下标就是value存储的对应位置
 *          ThreadLocal.set(T value)：
 *                  1.获取当前线程及其对应的ThreadLocalMap,set值时调用了ThreadLocalMap的set()方法
 *                  2.ThreadLocalMap类：
 *                              默认构造： a. 实例化了一个长度为16的一个Entry数组
 *                                        b. 生成数组下标方式： int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1) ---------默认构造时
 *                                                            int i = key.threadLocalHashCode & (len-1)       ------------一般构造时
 *                                                            table[i] = new Entry(firstKey, firstValue);
 *
 *                              threadLocalHashCode：
 *                                         private static final int HASH_INCREMENT = 0x61c88647;  //每次的threadLocalHashCode的增量(+)
 *                                         官方解释：连续生成的散列码之间的差异: 在2的幂次方的数组中将隐式顺序线程的本地id转换为接近最佳的hashcode分布
 *                                                  就是说使用这个量作为每次ThreadLocal变量的hashcode增量，能够使hashcode较为均匀的分布，从而避免hash冲突。
 *                                         为什么是0x61c88647?
 *                                                  斐波那契散列的乘数公式：(long) ((1L << 31) * (Math.sqrt(5) - 1))
 *                                                  (1L << 32) - (long) ((1L << 31) * (Math.sqrt(5) - 1)) = 0x61c88647
 *          ThreadLocal.get()：通过计算出索引直接从数组对应位置读取即可
 *          总结如下：
 *                  1. 对于某一ThreadLocal来讲，他的索引值i是确定的，在不同线程之间访问时访问的是不同的table数组的同一位置即都为table[i]，只不过这个不同线程之间的table是独立的。
 *                  2. 对于同一线程的不同ThreadLocal来讲，这些ThreadLocal实例共享一个table数组，然后每个ThreadLocal实例在table中的索引i是不同的。
 */
public class ThreadlocalTest {

    /**
     * 一般总将ThreadLocal定义为static类型的：
     *      1. ThreadLocal对于当前线程共享，设置为static，仅需在第一次使用时分配一块内存空间，使当前线程内的所有类都共享此变量。
     *      2. static使ThreadLocal 变为强引用，key不会在GC时被清理，执行remove方法时能够正确定位并删除。
     */
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                    /**
                     * 弱引用：描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，该对象仅仅被弱引用关联，那么就会被回收。
                     *      Entry的key与ThreadLocal的引用是弱引用，所以仅仅只有ThreadLocalMap中的Entry的key指向ThreadLocal的时候，ThreadLocal会进行回收的！！！
                     *      ThreadLocal被回收后在ThreadLocalMap里对应Entry的键值会变为null，但其中保存的value没法进行回收。
                     * 所以，在不使用ThreadLocal时应该使用ThreadLocal.remove()进行清理所有的键值
                     */
                }
            }
        });
        thread1.start();

        Thread thread2=new Thread(() -> {
            System.out.println((int)((1L << 32) - (long) ((1L << 31) * (Math.sqrt(5) - 1))));
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                }
            }
        });
        thread2.start();
    }
}
