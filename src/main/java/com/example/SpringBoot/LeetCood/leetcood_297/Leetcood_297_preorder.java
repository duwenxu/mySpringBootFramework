package com.example.springboot.leetcood.leetcood_297;

/**
 * leetcood 297.二叉树的序列化与反序列化
 *
 * @author duwenxu
 * @create 2021-01-21 15:16
 */

import java.util.LinkedList;

/**
 * encode: 通过前序遍历将其转化为 按指定分隔符拼接的String即可
 * decode: 通过String还原二叉树（即 如何通过遍历结果还原二叉树）：
 *          一般语境下，单单前序遍历结果是不能还原二叉树结构的，因为缺少空指针的信息，至少要得到前、中、后序遍历中的两种才能还原二叉树。
 *          但是这里的 node 列表包含空指针的信息，所以只使用 node 列表就可以还原二叉树
 *
 *  decode: 关键在于 通过遍历结果中的 # 标记叶子节点，从而进行下一个节点的递归迭代
 *
 *  tips: 中序遍历由于不能够确定根节点的位置，所以无法实现反序列化
 */
public class Leetcood_297_preorder {

    /**
     * Node定义
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    String sp=",",nullIns="#";

    /**
     * 序列化
     * @param root
     * @return
     */
    public String serialize(TreeNode root){
        StringBuilder sb= new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root==null){
            sb.append(nullIns).append(sp);return;
        } else {
            sb.append(root.val).append(sp);
        }
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    /**
     * 反序列化
     * @param data
     * @return
     */
    public TreeNode deserialize(String data){
        LinkedList<String> nodes = new LinkedList<>();
        for (String val :data.split(sp)) {
            nodes.add(val);
        }
        return deserialize(nodes);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()){
            return null;
        }
        //每次取出第一个节点
        String rootVal = nodes.removeFirst();
        //若为空(叶子节点),则return到下一个迭代
        if (rootVal.equals(nullIns)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        root.left=deserialize(nodes);
        root.right=deserialize(nodes);
        return root;
    }



}
