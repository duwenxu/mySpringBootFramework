package com.example.springboot.leetcood.数据结构.sort;

/**
 * 堆排序：对选择排序的优化
 * 是指利用堆积树（堆）这种数据结构所设计的一种排序算法，它是选择排序的一种。可以利用数组的特点快速定位指定索引的元素。
 * 堆的时间复杂度是O(N*logN)，空间复杂度是O(1)，且是一种不稳定的排序方式。
 * 分类： 数据结构不同分为：最大堆，根节点元素都不小于其孩子节点
 * 最小堆，根节点元素都不大于其左右孩子
 * 堆排序的核心思想： 构造堆，将数据构造成堆经过以下步骤就可以得到有序的数据：
 * <p>
 * 1. 建立堆
 * 2. 得到堆顶元素，为最大元素
 * 3. 去掉堆顶，将堆最后一个元素放到堆顶，
 * 4. 此时可通过一次调整重新使堆有序。
 * 5. 堆顶元素为第二大元素。 重复步骤3，直到堆变空。
 * <p>
 * 关键：
 * 1. 如何将 n 个待排序的数建成堆
 * 2. 堆顶元素输出后如何调整剩余的n-1个元素，
 * <p>
 * 1.  调整小顶堆的方法：
 * 1）设有m 个元素的堆，输出堆顶元素后，剩下m-1 个元素。将堆底元素送入堆顶（（最后一个元素与堆顶进行交换），堆被破坏，其原因仅是根结点不满足堆的性质。 故只需要从根节点处继续调整。
 * 2）将根结点与左、右子树中较小元素的进行交换。
 * 3）若与左子树交换：如果左子树堆被破坏，即左子树的根结点不满足堆的性质，则重复方法 （2）.
 * 4）若与右子树交换，如果右子树堆被破坏，即右子树的根结点不满足堆的性质。则重复方法 （2）.
 * 5）继续对不满足堆性质的子树进行上述交换操作，直到叶子结点，堆被建成。
 * <p>
 * 2. 对n个元素初始建堆的过程
 * 建堆方法：对初始序列建堆的过程，就是一个反复进行筛选的过程。
 * 1）n 个结点的完全二叉树，则最后一个结点是第个结点的子树。
 * 2）筛选从第个结点为根的子树开始，该子树成为堆。
 * 3）之后向前依次对各结点为根的子树进行筛选，使之成为堆，直到根结点。
 *
 * @author duwenxu
 * @create 2019-09-12 14:32
 */
public class HeapSort {

    public static int[] heapSort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr, len);  //创建最大堆

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);  //每次将根节点元素 arr[0] 置于 i 处，即使最大元素从后向前排列
            len--;  //最大堆根节点元素出堆后，再次调整堆结构时元素个数减一
            heapify(arr, 0, len);
        }
        return arr;
    }


    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {   //在二叉树中，从右向左，从后向前对每一个子堆进行调整
            heapify(arr, i, len);
        }
    }

    /**
     * 调整数据结构使其重新成为 最大堆
     */
    private static void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < len && arr[left] > arr[max]) {
            max = left;
        }
        if (right < len && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max, len);  //调整完当前子树后，可能会破环左右堆的结构，因此进行递归调整
        }
    }


    /**
     * 交换数组两索引的值
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = {7, 8, 6, 41, 3, 5, 101, 4, 25, 54555, 3, 2, 3, 52, 5, 4, 6, 8, 41, 6, 6, 21, 6, 3, 21, 58, 6, 3, 12, 36, 5, 65, 6, 2, 278, 8, 4888, 55, 422, 4, 65, 1, 6, 36, 4, 6569};
        int[] res=heapSort(arr);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
