package com.example.springboot.LeetCood.publicLinkedList;


/**
 * 单向链表
 * 打印两个有序链表的公共部分
 *
 * @author duwenxu
 * @create 2019-03-20 16:40
 */
public class Node {
    public int value;
    public Node next;

    public Node(int data) {
        this.value = data;
    }


    public static void printCommonPart(Node node1, Node node2) {
        System.out.println("CommonPart:");
        while (node1 != null && node2 != null) {
            if (node1.value < node2.value) {
                node2 = node2.next;
            } else if (node1.value > node2.value) {
                node1 = node1.next;
            } else {
                System.out.println(node1.value + "");
                node1 = node1.next;
                node2 = node2.next;
            }
        }
    }
}
