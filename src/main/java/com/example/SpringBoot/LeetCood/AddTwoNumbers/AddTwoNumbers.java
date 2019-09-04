package com.example.springboot.LeetCood.AddTwoNumbers;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class AddTwoNumbers {

    /**
     * 注意链表的指向特性
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNode = new ListNode(0);   //相当于先从最低位开始算起
        ListNode a = l1, b = l2,curr=sumNode;   //此处curr是sumNode的引用  curr.next指向与sumNode.next指向相同
        int carry = 0;   //进位
        while (a != null || b != null) {
            int x = (a != null) ? a.val : 0;//判断为空即遍历l1和l2到末尾
            int y = (b != null) ? b.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);  //存放下一个元素
            curr=curr.next;     //连接链表  curr相当于一个不断向后移动的Node
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
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;
        ListNode L_res=addTwoNumbers(l1, l4);
        System.out.println(L_res.val+""+L_res.next.val+""+L_res.next.next.val+"");
    }
}
