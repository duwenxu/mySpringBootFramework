package com.example.springboot.nio;

/**
 * 选择器（多路复用器）示例
 *
 * Selector基本使用流程：
 *  1. 打开 Selector
 *  2. 将channel注册到Selector,并设置监听事件
 *  3. 轮询：
 *      调用select方法阻塞的获取所有 可操作的I/O事件
 *      调用selector.selectedKeys()获取selectedKeys
 *      迭代每个selectKey:
 *          获取每个channel和对应的附加信息
 *          判断已经就绪的 I/O 事件类型，并处理：
 *              对于 OP_ACCEPT 事件，获取SocketChannel 并将其设置为非阻塞的，然后将Channel注册到 Selector 中
 *          根据需要更改监听事件
 *          将已经过处理的 selectKey 从集合中删除
 * @author duwenxu
 * @create 2020-06-09 16:28
 */
public class SelectorDemo {

    private static final int NUF_SIZE = 256;
    private static final int TIME_OUT = 3000;

    public static void main(String[] args) {

    }

}
