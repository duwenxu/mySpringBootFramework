package com.example.springboot.leetcood.leetcood_297;

/**
 * leetcood 297.二叉树的序列化与反序列化
 *
 * @author duwenxu
 * @create 2021-01-21 15:16
 */

import java.util.LinkedList;

/**
 * 后序遍历
 */
public class Leetcood_297_postorder {

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

    /**
     * 后序遍历时root节点位于最后
     * @param root
     * @param sb
     */
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root==null) {
            sb.append(nullIns).append(sp);
            return;
        }
        serialize(root.left,sb);
        serialize(root.right,sb);
        sb.append(root.val).append(sp);
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
        //每次取出最后一个节点
        String rootVal = nodes.removeLast();
        //若为空(叶子节点),则return到下一个迭代
        if (rootVal.equals(nullIns)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        //后续遍历从后往前取 因此要先构造右子树，再构造左子树
        root.right=deserialize(nodes);
        root.left=deserialize(nodes);
        return root;
    }



}
