package com.example.springboot.leetcood.数据结构.sort;


import java.util.Arrays;

/**
 * 快速排序
 * 选定元素能够被一次性的放置在其正确位置上，不需要像其它排序经过多次的迭代
 * <p>
 * quickSort1 基本思想：
 * 通过一趟排序将要排序的的数据分割成独立的两部分，其中一部分的数据比另一部分都要小。再按此方法对这两部分数据分别进行递归排序
 * 实现思路：
 *
 * 1. 挖坑填数+分而治之
 * 以数组第一个元素作为基准值，依次找 逆序遍历小于基准值的元素和顺序遍历大于基准值的元素 去替换并成为新的基准值
 * 直到首尾基准值的指针相遇，排序结束。并将此时的 i或j 位置处的元素替换为初始基准值，此时的i 或 j即为该基准值在序列中的排序位置
 * 总体思路： 将比key小的数前移，将比key大的数后移
 * 2. 递归操作排序每一段
 * 时间复杂度分析：
 * 1. 最好情况，每次的基准数都恰好能平分当前的数组。 此时，时间复杂度为： O(nlogn)
 * 2. 最差情况，每次取到的基准数都是当前数组中的最大或最小数。 此时，时间复杂度为：1+2+3+...+(n-1)=n(n-1)/2 即为O(n^2)
 * quickSort2 基本思想：
 * 元素交换+分而治之
 * <p>
 * 以第一个为准
 *
 * @author duwenxu
 * @create 2019-09-10 11:21
 */
public class FastSort {
    /**
     * 截断长度，当数组元素小于100时，放弃递归排序的方式
     */
    static final int cutOff = 0;

    @SuppressWarnings("MathRandomCastToInt")
    public static int[] initRandomArr() {
        int[] arr = new int[10000];
        int max = 10000, min = 1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.random() * (max - min) + min;
        }
        return arr;
    }

    private static void quickSort1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort1(arr, 0, arr.length - 1);
    }

    private static void quickSort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort2(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param start
     * @param end
     */
    private static void quickSort1(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int i = start, j = end, pivot = arr[start];
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;       //得到i或j为此时的pivot的位置，递归
        quickSort1(arr, start, i - 1);     //注意分治时的首尾index
        quickSort1(arr, i + 1, end);
    }

    private static void quickSort2(int[] arr, int start, int end) {
        if (end - start >= cutOff) {
            int pivot = getPivot(arr, start, end); //pivot位于 end-1 处
            int i = start, j = end - 1;
            for (; ; ) {
                while (i < j && arr[++i] < pivot) {
                } //左侧为选取的最小值，直接从第二位开始
                while (i < j && arr[--j] > pivot) {
                } //右侧从end-2处 开始比较
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;  //i==j 子集划分完成
                }
            }
            swap(arr, i, end - 1);  //将选定的主元放在 i处
            quickSort2(arr, start, i - 1);
            quickSort2(arr, i + 1, end);
        }
    }


    /**
     * 一般都取第一个元素作为基准数
     * 可以采用中位数法取基准数，相当于是优化
     *
     * @return
     */
    public static int getPivot(int[] arr, int left, int right) {
        int res = 0;
        if (right > 0) {
            try {
                if (left < right) {
                    int center = (left + right) / 2;
                    if (arr[left] > arr[center]) {
                        swap(arr, left, center);
                    }
                    if (arr[left] > arr[right]) {
                        swap(arr, left, right);
                    }
                    if (arr[center] > arr[right]) {
                        swap(arr, center, right);
                    }
                    swap(arr, center, right - 1);  //将中位数(选定的主元置于right-1的位置)  其实这里只是找到了合适的主元，可以自定义其存放位置
                }
                res = arr[right - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    private static void swap(int[] arr, int left, int right) {
        if (left < right) {
            int tmp;
            tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
    }


    public static void main(String[] args) {
        int[] arr1 = {7, 8, 6, 41, 3, 5, 101, 4, 25, 36, 3, 2, 3, 52, 5, 4, 6, 8, 41, 6, 6, 21, 6, 3, 21, 58, 6, 3, 12, 36, 5, 65, 6, 2, 278, 8, 4888, 55, 422, 4, 65, 1, 6, 54555, 4, 6569};
        int[] arr2 = {7, 8, 6, 41, 3, 5, 101, 4, 25, 36, 3, 2, 3, 52, 5, 4, 6, 8, 41, 6, 6, 21, 6, 3, 21, 58, 6, 3, 12, 36, 5, 65, 6, 2, 278, 8, 4888, 55, 422, 4, 65, 1, 6, 54555, 4, 6569};
        Long sTime1 = System.currentTimeMillis();
        quickSort1(arr1);
        Long eTime1 = System.currentTimeMillis();

        Long sTime2 = System.currentTimeMillis();
        quickSort2(arr2);
        Long eTime2 = System.currentTimeMillis();

        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("quickSort1 排序耗时：" + Long.toString(eTime1 - sTime1) + "ms");

        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("quickSort2 排序耗时：" + Long.toString(eTime2 - sTime2) + "ms");

        if (Arrays.equals(arr1, arr2)){
            System.out.println("ok----------ok");
        }
    }
}
