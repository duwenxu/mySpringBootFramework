package com.example.springboot.LeetCood.数据结构.sort;


/**
 * 快速排序
 *
 * 基本思想：
 *         通过一趟排序将要排序的的数据分割成独立的两部分，其中一部分的数据比另一部分都要小。再按此方法对这两部分数据分别进行递归排序
 *         实现思路：    1. 选择一个元数据设为key，可以采用头、中、尾的中位数选择法
 *                      2. 从后向前找小于key的元素，并与key互换
 *                         从前往后找大于key的元素，并与key互换
 *                         当i(当前小于key的元素下标)>=j(当前大于key的元素下标)时结束
 *                         *******************(下述算法实现时采用的是直接进行赋值的方式)
 *                         总体思路： 将比key的数前移，将比key大的数后移
 *                      3. 递归操作排序每一段
 * @author duwenxu
 * @create 2019-09-10 11:21
 */
public class FastSort {

    public static void quickSort(int[] arr){
        if (arr==null && arr.length==0){
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int pivot = arr[start];
        int i = start, j = end;
        while (i < j) {
            while (i < j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] < pivot) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = pivot;
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }


    public static void main(String[] args) {
        int[] arr = {7, 8, 6, 41, 3, 5};
        Long sTime = System.currentTimeMillis();
        quickSort(arr);
        Long eTime = System.currentTimeMillis();
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("排序耗时：" + Long.toString(eTime - sTime)+"ms");
    }
}
