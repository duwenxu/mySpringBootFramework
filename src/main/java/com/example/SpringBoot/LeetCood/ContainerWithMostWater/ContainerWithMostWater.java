package com.example.SpringBoot.LeetCood.ContainerWithMostWater;


/**
 * leetcood题目：盛水最多的容器
 *
 * @author duwenxu
 * @create 2019-04-02 16:27
 */
public class ContainerWithMostWater {
    public static int solution(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
       int[] arr={1,8,6,2,5,4,8,3,7};
        System.out.println(solution(arr));
    }
}
