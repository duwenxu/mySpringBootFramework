package com.example.springboot.LeetCood.tencent;

import com.example.springboot.LeetCood.AddTwoNumbers.ListNode;

import java.util.ArrayList;
import java.util.Collections;


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
     *       这也是分治法的一般思想，通过无限的细分最终转化为简单问题
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
         * 从从中间处将两个链表分开，递归合并
         */
        int len = lists.length;
        int mid = len / 2;
        ListNode[] left = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            left[i] = lists[i];
        }

        ListNode[] right = new ListNode[len - mid];
//      for (int i = mid, j = 0; j < len - mid; i++, j++) {
        for (int i = mid, j = 0; i<len; i++, j++) { //注意此处循环的结束条件
            right[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists1(left), mergeKLists1(right));
    }

    /**
     * 暴力法
     * @param lists
     * @return
     */
    private static ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length==0){
            return null;
        }
        ArrayList<Integer> nodeValueList = new ArrayList<>();
        int len = lists.length;
        for (int i = 0; i < len; i++) {
            ListNode listI = lists[i];
            while (listI!=null){
               nodeValueList.add(listI.val);
               listI=listI.next;
           }
        }
        Collections.sort(nodeValueList);
        ListNode headNode = new ListNode(-1);
        ListNode tmp=headNode;
        for (int i = 0; i < nodeValueList.size(); i++) {
            tmp.next=new ListNode(nodeValueList.get(i));

        }
        return headNode.next;
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
        mergeKLists2(lists);
    }

}
