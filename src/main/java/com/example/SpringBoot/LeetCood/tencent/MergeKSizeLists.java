package com.example.springboot.leetcood.tencent;

import com.example.springboot.leetcood.AddTwoNumbers.ListNode;

import java.util.*;


/**
 * 合并k个有序链表，返回合并后的排序链表
 *
 * @author duwenxu
 * @create 2019-09-27 17:26
 */
public class MergeKSizeLists {


    /**
     * 使用两个链表的合并求K个链表   或者叫  分治法
     * 思想： 通过不断地二分递归求 k 个有序链表的合并，最终全都转化为了 mergeTwoLists() 进行合并
     * 这也是分治法的一般思想，通过无限的细分最终转化为简单问题
     *
     * @param lists
     * @return
     */
    private static ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        } else if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        /**
         * 从中间处将两个链表分开，递归合并
         */
        int len = lists.length;
        int mid = len / 2;
        ListNode[] left = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            left[i] = lists[i];
        }

        ListNode[] right = new ListNode[len - mid];
        for (int i = mid, j = 0; i < len; i++, j++) { //注意此处循环的结束条件
            right[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists1(left), mergeKLists1(right));
    }

    /**
     * 暴力法  直接遍历拿出所有节点放入数组，再将数组拼接为ListNode
     * 时间复杂度： O(m*n)
     *
     * @param lists
     * @return
     */
    private static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ArrayList<Integer> nodeValueList = new ArrayList<>();
        int len = lists.length;
        for (int i = 0; i < len; i++) {
            ListNode listI = lists[i];
            while (listI != null) {
                nodeValueList.add(listI.val);
                listI = listI.next;
            }
        }
        Collections.sort(nodeValueList);
        return createListNode(nodeValueList);
    }

    /**
     * 类似 贪心算法
     * 使用 priorityQueue(优先队列)进行排序
     * 注意：一般使用堆(最大堆、最小堆)实现优先队列
     *
     * @param lists
     * @return
     */
    private static ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        ListNode resNode = new ListNode(-1);
        ListNode curr = resNode;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(len, Comparator.comparingInt(node -> node.val)); //比较每个ListNode的头元素的值
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode smallerNode = priorityQueue.poll();
            /**注意 链表的拼接过程**/
            curr.next = smallerNode;
            curr = curr.next;
            if (curr.next != null) {
                priorityQueue.add(curr.next);
            }
        }
        return resNode.next;
    }

    /**
     * 数组 转  链表  list--->listNode
     *
     * @param datas
     * @return
     */
    private static ListNode createListNode(List<Integer> datas) {
        if (datas.isEmpty()) {
            return null;
        }
        ListNode firstNode = new ListNode(datas.get(0));
        ListNode nextNode = createListNode(datas.subList(1, datas.size()));//subList 包左不包右
        firstNode.next = nextNode;
        return firstNode;
    }


    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;
        ListNode l7 = new ListNode(2);
        ListNode l8 = new ListNode(5);
        ListNode l9 = new ListNode(8);
        l7.next = l8;
        l8.next = l9;
        ListNode[] lists = {l1, l4, l7};
//        ListNode listNode = mergeKLists1(lists);
//        System.out.println(mergeKLists2(lists).toString());
        System.out.println(mergeKLists3(lists).toString());
    }


}
