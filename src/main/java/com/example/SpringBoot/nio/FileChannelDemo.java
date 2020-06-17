package com.example.springboot.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO channel
 *
 * @author duwenxu
 * @create 2020-06-09 14:03
 */
public class FileChannelDemo {

    /**
     * 因为FileChannel一定是阻塞的，所以不能够使用Selector
     * 只有 非阻塞的channel才能够注册到Selector中
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        /**
         * RandomAccessFile  四种模式
         * r	以只读的方式打开文本，也就意味着不能用write来操作文件
         * rw	读操作和写操作都是允许的
         * rws	每当进行写操作，同步的刷新到磁盘，刷新内容和元数据
         * rwd	每当进行写操作，同步的刷新到磁盘，刷新内容
         */
        //打开channel
        RandomAccessFile file = new RandomAccessFile("D:/SpringBoot/src/main/resources/application.properties", "rw");
        FileChannel channel = file.getChannel();

        /**
         *buffer中的三个值：
         * limit: 当前可以读/写到的指针位置
         * position:当前读写的指针位置
         * capacity:与模式无关，分配的容量大小
         */
        /**
         * 可以分配直接内存 （Direct Buffer）
         *  Direct Buffer:
         *   1. 分配堆外内存，减少JVM Heap堆内存的占用
         *   2. 申请和释放 Direct Buffer 的开销比较大. 因此正确的使用 Direct Buffer 的方式是在初始化时申请一个 Buffer, 然后不断复用此 buffer, 在程序结束后才释放此 buffer.
         *   3. 使用 Direct Buffer 时, 当进行一些底层的系统 IO 操作时, 效率会比较高, 因为此时 JVM 不需要拷贝 buffer 中的内存到中间临时缓冲区中.
         *
         *
         *   Buffer.rewind():重置position值为0，可以重新对buffer进行读写（一般主要用于读模式）
         *   Buffer.mark() :保存当前position
         *   Buffer.reset() :回复 mark的position值
         *
         *   Buffer 的比较：
         *      两个 Buffer 是相同类型的
         *      两个 Buffer 的剩余的数据个数是相同的
         *      两个 Buffer 的剩余的数据都是相同的
         */
//        ByteBuffer buffer = ByteBuffer.allocateDirect(48);
        ByteBuffer buffer = ByteBuffer.allocate(48);

        /**
         * channel读写的区别：
         *      channel.read(buf): 从channel读入到buffer
         *      channel.write(buf): (从buf中)写入到channel
         */
        //从channel中读取文件流到buffer
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            /**
             * 将buffer设置为 读模式
             * flip(): 将读写指针指向到 buffer头部，即将buffer的指针position设置为0，并设置了当前最多能读出的数据大小
             */
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }

            /**
             * 切换写模式   或者用 buffer.compact()
             * 清理buffer，待下一次写入
             *          *   buffer.clear()：将position设置为0，将limit设置为capacity
             *          *      并未真正删除buffer中的数据，使用场景(准备写入数据之前)：
             *          *           * buf.clear();     // Prepare buffer for reading
             *          *           * in.read(buf);    // Read data</pre></blockquote>
             *
             *              buffer.compact():若在准备写入数据之前，想要保存当前buffer中未读完的数据
             *                  则使用compact将所有未读的数据拷贝至Buffer的起始处
             */
            buffer.clear();
            bytesRead = channel.read(buffer);
        }
        file.close();
    }
}
