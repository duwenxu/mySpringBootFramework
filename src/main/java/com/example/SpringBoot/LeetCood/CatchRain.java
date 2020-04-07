package com.example.springboot.leetcood;

/**
 * leetCode: 接雨水（困难）
 *
 * @author duwenxu
 * @create 2019-04-02 16:40
 * <p>
 * 思路：
 * 1.指针对撞  从两边向中间遍历，下一个数比上一个数小则能够接到水（因为无论中间是最高的或最低的总是网中间才能接到水哦）
 * 2.需要找到处于中间的最大值  凹槽的出现必然是左右两边均有大于中间数的存在
 *
 * 照着评论区的各位大神的方法写的： 思路：仍然是指针对撞，并且不断地在数组两侧选举最大值，计算两侧各元素与最大值的差值即为能接到的水量，直到最终start==end,两侧的最大值相等，遍历结束
 */
public class CatchRain {
    static int totalRainArea(int[] height) {
        int totalArea = 0, start = 0, end = height.length - 1;
        int leftMax=0,rightMax=0;
        while (start < end) {
            if (height[start] < height[end]) {
                if (height[start] <leftMax) {
                    totalArea += leftMax - height[start];
                }else {
                    leftMax=height[start];
                }
                start++;
            } else {
                if (height[end] < rightMax) {
                    totalArea += rightMax-height[end];
                }else {
                    rightMax=height[end];
                }
                end--;
            }
        }
        return totalArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(totalRainArea(height));
    }
}
