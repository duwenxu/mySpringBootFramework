package com.example.springboot.leetcood.数据结构.sort;

/**
 * 归并排序
 *
 *  算法思想：
 *      合并：即将两个有序表进行合并成一个有序表
 *      将待排序列分为若干个有序的子序列，进行递归的合并得到最终的有序序列
 *
 *  时间复杂度为O(nlog₂n) 这是该算法中最好、最坏和平均的时间性能。空间复杂度为 O(n)。归并排序比较占用内存，但却是一种效率高且稳定的算法。
 * @author duwenxu
 * @create 2019-09-12 11:16
 */
public class MergeSort {

    /**
     * 合并两有序序列
     */
    private static void merge(int[] nums,int left,int mid,int right){
        int[] tmp=new int[right-left+1];
        int i=left,j=mid+1,k=0; //两个子序列和最终序列的起始index

        while (i<=mid && j<=right){
            if (nums[i]<nums[j]){
                tmp[k++]=nums[i++];
            }else {
                tmp[k++]=nums[j++];
            }
        }
        while (i<=mid){
            tmp[k++]=nums[i++];
        }
        while (j<=right){
            tmp[k++]=nums[j++];
        }
        /**
         * System.arraycopy 相当于是for循环复制元素
         * 参数含义：  原数组   原数组开始位置   目标数组   目标数组开始位置   复制长度
         */
        System.arraycopy(tmp, 0, nums, left, tmp.length);  //将合并后的有序序列填充到原数组中
    }

    public static int[] mergeSort(int[] nums,int left,int right){  //递归二分
        int mid=(left+right)/2;
        if (left<right){
            mergeSort(nums, left, mid);
            mergeSort(nums, mid+1, right);
            merge(nums, left, mid, right);
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 8, 6, 41, 3, 5, 101, 4, 25, 36, 3, 2, 3, 52, 5, 4, 6, 8, 41, 6, 6, 21, 6, 3, 21, 58, 6, 3, 12, 36, 5, 65, 6, 2, 278, 8, 4888, 55, 422, 4, 65, 1, 6, 54555, 4, 6569};

        Long sTime1 = System.currentTimeMillis();
        int[] res = mergeSort(arr1, 0, arr1.length - 1);
        Long eTime1 = System.currentTimeMillis();

        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("mergeSort 排序耗时：" + Long.toString(eTime1 - sTime1) + "ms");
    }
}
