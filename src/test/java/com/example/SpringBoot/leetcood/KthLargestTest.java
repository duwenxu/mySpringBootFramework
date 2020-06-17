package com.example.SpringBoot.leetcood;

import com.example.springboot.leetcood.算法通关40讲.priorityQueue.KthLargest_703;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KthLargestTest {

    @Test
    public void add1() {
        int k = 3;
        int[] arr = new int[] { 4, 5, 8, 2 };
        KthLargest_703 kthLargest = new KthLargest_703(k, arr);
        assertEquals(4, kthLargest.add(3));
        assertEquals(5, kthLargest.add(5));
        assertEquals(5, kthLargest.add(10));
        assertEquals(8, kthLargest.add(9));
        assertEquals(8, kthLargest.add(4));
    }

}