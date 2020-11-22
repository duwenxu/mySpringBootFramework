package com.example.springboot.leetcood.数据结构;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LPU缓存实现
 *
 * @author duwenxu
 * @create 2020-11-07 15:24
 */
class LRUCache {

    static class Node {
       public int key, val;
       public Node prev,next;
       public Node(int k,int v){
           this.key = k;
           this.val = v;
       }
    }

    private LinkedList<Node> cache;
    private ConcurrentHashMap<Integer, Node> map;
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new LinkedList<>();
        this.map = new ConcurrentHashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }else {
            Node node = map.get(key);
            //移除该节点并将其插到头部
            cache.remove(node);
            cache.addFirst(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)){
            //更新值，并前置到头结点
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node);
        }else {
            //不存在，先判断容量
            if (cap == cache.size()){
                //删除最后一个节点
                Node lastNode = cache.removeLast();
                map.remove(lastNode.key);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */