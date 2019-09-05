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
     * <p>
     * 时间复杂度 O(len1+len2)
     *
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
     * 不用数组存储合并的数组 用变量记录  可改善空间复杂度为O(1)
     * 1.用 x 存储当前位置的元素，当排序至 (m+n)/2 时得到中位数  关键：1.保证排序的有序 2.确定中位数出现的位置
     * 2.奇数：（len+1）/2    偶数：len/2和(len/2+1)
     *
     * @return
     */
    public static double getNumsMid2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int left = 0, right = 0, astart = 0, bstart = 0;
        for (int i = 1; i <= len / 2 + 1; i++) {        //不需要去合并数组中的所有元素
            left = right;  //left记录上一个元素的值,right记录当前元素的值
            if (astart < m && (bstart >= n || nums1[astart] <= nums2[bstart])) {
                right = nums1[astart++];
            } else {
                right = nums2[bstart++];
            }
        }
        if ((len & 1) == 1) {
            return right;
        } else {
            return (left + right) / 2.0;
        }
    }

//    /**
//     * 二分法
//     *
//     *
//     * @return
//     */
//    public static double getNumMid3(int[] A, int[] B) {
//        int m=A.length,n=B.length;
//        if (m>n){
//            int[] tmp=A; A=B;B=tmp;
//            m=A.length;n=B.length;
//        }
//        int iMin=0,iMax=m,halfLen=(m+n+1)/2;
//        while (iMin<=iMax){
//            int i=(iMin+iMax)/2;
//            int j=halfLen-i;
//            if (i<iMax && B[j-1]>A[i]){
//                iMin=i+1;
//            }else if (i>iMin && B[j]<A[i-1]){
//                iMax=i-1;
//            }else {
//                int maxLeft=0;
//                if (i==0) {maxLeft=B[j-1];}
//                else if (j==0) {maxLeft=A[i-1];}  //todo
//                else {maxLeft = Math.max(A[i-1], B[j-1]);}
//            }
//
//        }
//    }


    private static double getMid(int[] nums2, int len) {
        if (len % 2 == 1) {         //判断奇偶还可以用： len & 1==0---->偶数    len & 1==1---->奇数
            return nums2[(len - 1) / 2];
        } else {
            return (nums2[len / 2] + nums2[len / 2 - 1]) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {2, 5, 8, 8, 9};
        System.out.println(getNumsMid1(nums1, nums2));
        System.out.println(getNumsMid2(nums1, nums2));
    }
}
