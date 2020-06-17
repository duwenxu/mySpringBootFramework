package com.example.springboot.leetcood.算法通关40讲.priorityQueue;

import com.example.SpringBoot.KotlinTest.commontest.A;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 数据流中的第k大元素
 * <p>
 * 考察：PriorityQueue 优先队列的使用
 * 题目：
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 解法1：
 * 使用优先队列，始终维持 K 个元素
 *
 * 解法2：
 * 使用列表+排序
 *
 * @author duwenxu
 * @create 2020-05-24 17:34
 */
public class KthLargest_703 {

    int k;
    PriorityQueue<Integer> queue;

    public KthLargest_703(int k, int[] nums) {
        this.k = k;
        this.queue = new PriorityQueue<>(k);
        for (int a : nums) {
            add(a);
        }
    }

    public int add(int val) {
        if (queue.size()< k){
            queue.offer(val);
        }else if (queue.peek()<val){
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }
}



class KthLargest_703_1 {

    int k;
    ArrayList<Integer> list;

    public KthLargest_703_1(int k, int[] nums) {
        this.k = k;
        this.list = new ArrayList<>(k);
        for (int a:nums){
            add(a);
        }
    }

    public int add(int val) {
        Collections.sort(list);
        if (list.size()<k){
            list.add(val);
            Collections.sort(list);
        }else if (list.get(0)<val){
            list.remove(0);
            list.add(val);
            Collections.sort(list);
        }
        return list.get(0);
    }
}
