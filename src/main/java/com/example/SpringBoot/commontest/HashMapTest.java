package com.example.springboot.commontest;

/**
 * HashMap一些原理测试
 *
 * @author duwenxu
 * @create 2019-07-25 10:01
 */
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Returns a power of two size for the given target capacity.
     * 返回给定目标容量的2次幂
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
//        System.out.println(tableSizeFor(12));
        System.out.println(MAXIMUM_CAPACITY==Math.pow(2,30));
        System.out.println(Integer.MAX_VALUE);
    }
}
