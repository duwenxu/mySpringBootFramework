package com.example.springboot.LeetCood.tencent;

import com.example.springboot.LeetCood.AddTwoNumbers.ListNode;


/**
 * 合并k个有序链表，返回合并后的排序链表
 *
 * @author duwenxu
 * @create 2019-09-27 17:26
 */
public class MergeKSizeLists {

    public static ListNode mergeKSizeLists(ListNode[] lists){
        int len = lists.length;
        ListNode headNode = new ListNode(-1);
        ListNode prev=headNode;
        for (int i = 0; i < len; i++) {
            prev.next= mergeTwoLists(prev, lists[i]);
        }
        return headNode.next;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }else if (l2==null){
            return l1;
        }else if (l1.val<l2.val){
            l1.next=mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next=mergeTwoLists(l1, l2.next);
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
        ListNode[] lists={l1,l4,l7};
        ListNode listNode = mergeKSizeLists(lists);
    }

}
