package com.example.springboot.LeetCood.tencent;

/**
 * 寻找两个有序数组的中位数
 *
 * @author duwenxu
 * @create 2019-09-04 15:24
 */
public class FindMedianSortedArrays_04 {
    /**
     * 1.合并有序两数组
     * 2.根据奇偶分别找中位数
     *
     * 时间复杂度 O(len1+len2)
     * @param nums1
     * @param nums2
     * @return
     */
    public static double getNumsMid1(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int i = 0, j = 0, count = 0;
        int[] nums = new int[len1 + len2];
        if (len1 == 0) {
            return getMid(nums2, len2);
        }
        if (len2 == 0) {
            return getMid(nums1, len1);
        }
        while (count != len1 + len2) {      //当数组组合完毕时跳出
            if (i == len1) {        //当一个数组添加结束时只添加另一个
                while (j < len2) {
                    nums[count++] = nums2[j++];
                }
            }
            if (j == len2) {
                while (i < len1) {
                    nums[count++] = nums1[i++];
                }
            }
            if (i == len1 && j == len2) break;
            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }
        return getMid(nums, nums.length);
    }

    /**
     * 游标碰撞
     * 1. 在a,b数组中各设置一个游标
     * @param nums1
     * @param nums2
     * @return
     */
    public static double getNumsMid2(int[] nums1, int[] nums2){

    }

    private static double getMid(int[] nums2, int len) {
        if (len % 2 == 1) {         //判断奇偶还可以用： len & 1==0---->偶数    len & 1==1---->奇数
            return nums2[(len -1) / 2];
        } else {
            return (nums2[len / 2] + nums2[len / 2 - 1]) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {28};
        int[] nums2 = {};
        System.out.println(getNumsMid1(nums1, nums2));
    }
}
