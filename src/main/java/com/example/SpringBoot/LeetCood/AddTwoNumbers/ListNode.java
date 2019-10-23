package com.example.springboot.leetcood.AddTwoNumbers;

/**
 * 链表节点类：
 */
public class ListNode {
    public int val;  //数据域
    public ListNode next;  //指针域

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        ListNode currNode=this;
        StringBuilder str = new StringBuilder();
        while (currNode!=null){
            str.append(currNode.val);
            str.append("-");
            currNode=currNode.next;
        }
        str.append("null");
        return str.toString();
    }
}
