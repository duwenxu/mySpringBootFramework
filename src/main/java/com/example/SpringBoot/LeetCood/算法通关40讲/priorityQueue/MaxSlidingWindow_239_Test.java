package com.example.springboot.leetcood.算法通关40讲.priorityQueue;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 测试
 *
 * @author duwenxu
 * @create 2020-05-24 21:10
 */
public class MaxSlidingWindow_239_Test {


    @Test
    public void TestMaxSlidingWindow(){
        int k = 3;
        int[] nums = {1,3,-1,-3,5,3,6,7};
        MaxSlidingWindow_239 maxSlidingWindow_239 = new MaxSlidingWindow_239();

        int[] result = {3,3,5,5,6,7};
        assertEquals(maxSlidingWindow_239.maxSlidingWindow(nums, k),result);
    }
}

