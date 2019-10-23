package com.example.springboot.leetcood.tencent;

import java.util.Arrays;

/**
 * 数组中最接近target的三个数的和
 *
 * @author duwenxu
 * @create 2019-09-25 14:27
 */
public class ClosestThreeNums {


    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        int[] nums2 = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(closestSum1(nums2, target));
    }

    /**
     * 思想：
     * 排序+双指针遍历
     * *比较大小有关的要先排序
     *
     * @param nums
     * @param target
     * @return
     */
    private static int closestSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left, right;   //左右指针
        int len = nums.length;
        int temp;
        int range = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            left = i + 1;
            right = len - 1;
            while (left < right) {
                temp = nums[i] + nums[left] + nums[right] - target;
                if (Math.abs(temp) < Math.abs(range)) range = temp;
                if (temp > 0) {      //range记录了当前与target的最小值，此处应该使用temp比较。
                    right--;
                } else if (temp < 0) {
                    left++;
                } else {
                    return target;
                }
            }
        }
        return target + range;
    }


    /**
     * 思路一样，不过直接记录和值
     *
     * @param nums
     * @param target
     * @return
     */
    private static int closestSum1(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = nums[0] + nums[1] + nums[2], temp;
        for (int i = 0; i < len; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                temp = nums[i] + nums[left] + nums[right];
                if (Math.abs(temp - target) < Math.abs(sum - target)) sum = temp;
                if (temp > target) {      //range记录了当前与target的最小值，此处应该使用temp比较。
                    right--;
                } else if (temp < target) {
                    left++;
                } else {
                    return target;
                }
            }
        }
        return sum;
    }
}
