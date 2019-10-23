package com.example.springboot.leetcood.tencent;

import com.example.springboot.leetcood.AddTwoNumbers.ListNode;

/**
 * 合并两个有序链表
 *
 *
 *
 * @author duwenxu
 * @create 2019-09-26 17:27
 */

public class MergeTwoLists {

    /**
     * 1. 递归
     * 注意到两个链表都有序
     *      因此可以分解为小段链表的分别合并，采用递归的方法
     *      注： 链表的合并和组合，其实就是指针的指向。
     *          链表的插入操作：断开要插入的链表，重新链接新元素与前后元素的指针指向即可
     * @param l1
     * @param l2
     * @return
     */
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


    /**
     * 2. 迭代插入
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(-1);

        ListNode prev=headNode;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                prev.next=l1;   //拼接l1 当前的node节点值
                l1=l1.next;     //使l1指向下一个节点
            }else {
                prev.next=l2;
                l2=l2.next;
            }
            prev=prev.next;     //使prev指向它的下一个节点位置
        }
        prev.next=l1==null? l2:l1;  //当出现有一个链表节点为空时，直接拼接另一个(有序链表)
        return headNode.next;
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
        ListNode resList = mergeTwoLists(l1, l4);
        ListNode resList1 = mergeTwoLists1(l1, l4);
    }



}



