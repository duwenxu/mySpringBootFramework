package com.example.SpringBoot.LeetCood.AddTwoNumbers;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 调试错误---------------------------------------------------------------------------------------------------------------------------------！！！！
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNode = new ListNode(0);   //相当于先从最低位开始算起
        ListNode a = l1, b = l2, curr = sumNode;
        int carry = 0;   //进位
        while (a != null || b != null) {
            int x = (a != null) ? a.val : 0;//判断为空即遍历l1和l2到末尾
            int y = (b != null) ? b.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr = new ListNode(sum % 10);
            if (a != null) a = a.next;
            if (b != null) b = b.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return sumNode.next;
    }

    //IDEA中的main函数快捷键 psvm
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        System.out.println(addTwoNumbers(listNode1,listNode2));
    }
}
