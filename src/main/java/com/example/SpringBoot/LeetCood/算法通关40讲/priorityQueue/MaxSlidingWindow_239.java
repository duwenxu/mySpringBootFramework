package com.example.springboot.leetcood.算法通关40讲.priorityQueue;

import java.util.PriorityQueue;

/**
 * 滑动窗口的最大值
 *
 * 解法1：
 *  优先队列
 *
 * @author duwenxu
 * @create 2020-05-24 20:55
 */
public class MaxSlidingWindow_239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length<k){
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int a:nums) {
            if (queue.size()<k){
                queue.add(a);
            }
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = 0;i<nums.length - k + 1;i++){
            res[i] = queue.peek();
            queue.poll();
            queue.add(nums[i+k]);
        }
        return res;
    }

}
