package com.example.springboot.LeetCood.数据结构.sort;

/**
 * low sort   3种 简单排序
 *
 * @author duwenxu
 * @create 2019-09-12 8:35
 */
public class SimpleSort {


    /**
     * 插入排序
     * https://www.cnblogs.com/zhuminghui/p/8401129.html#menu3
     * 算法思路：
     * 类似扑克牌的抓牌过程    摸牌时，从后往前比，摸到的牌小于当前位置牌就当前牌往后错一位，并将摸到的牌插入
     * 平均时间复杂度 O(n^2)  空间复杂度 O(1)
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int length = arr.length;
        int insertNum;
        for (int i = 1; i < length; i++) {  //从 1(第二个元素) 开始插入
            insertNum = arr[i];
            for (; i > 0 && insertNum < arr[i - 1]; i--) {
                arr[i] = arr[i - 1];   //从后向前 移出空位，直到当前插入的数大于该位置的数
            }
            arr[i] = insertNum; //i处即为最终该牌的位置
        }
    }


    /**
     * 冒泡排序
     * <p>
     * 算法思路： 1. 相邻元素两两比较交换位置，把小的元素往前调或者把大的元素往后调    2. 每次排序交换元素的个数随排序次数的增加递减
     * 平均时间复杂度为O(n^2)，空间复杂度：O(1)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int length = arr.length, tmp ;
        for (int i = 0; i < length - 1; i++) {        //外循环：排序趟数 len个元素排序 len-1 趟
            for (int j = 0; j < length - 1 - i; j++) {        //内循环：每趟(第i个元素)的比较次数，第i趟比较 len-i次
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序
     * <p>
     * 算法思路：一趟遍历记录最小(最大)的数，放到第一个位置； 再一趟遍历记录剩余列表中最小(最大)的数，继续放置；
     *  //todo   想清楚再补充完整
     * @param arr
     */
    public static void selectSort(int[] arr) {
        int length = arr.length;
        int minIndex,tmp=0;
        for (int i = 0; i < length - 1; i++) {     //排序趟数：len个元素需要 len-1 趟
            minIndex=i;
            for (int j = i+1; j < length ; j++) {      //第i趟排序时的遍历次数：len-i 次
                if (arr[j] > tmp) {
                    tmp = arr[j];
                }
            }
            arr[i] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 8, 6, 41, 3, 5, 101, 4, 25, 36, 3, 2, 3, 52, 5, 4, 6, 8, 41, 6, 6, 21, 6, 3, 21, 58, 6, 3, 12, 36, 5, 65, 6, 2, 278, 8, 4888, 55, 422, 4, 65, 1, 6, 54555, 4, 6569};

        Long sTime1 = System.currentTimeMillis();
//        insertSort(arr1);
//        bubbleSort(arr1);
        selectSort(arr1);
        Long eTime1 = System.currentTimeMillis();

        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("insertSort 排序耗时：" + Long.toString(eTime1 - sTime1) + "ms");

    }
}
