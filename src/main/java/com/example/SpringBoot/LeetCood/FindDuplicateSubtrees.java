package com.example.springboot.leetcood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Leetcood 652.寻找重复子树
 *
 * @author duwenxu
 * @create 2021-01-20 14:05
 */
public class FindDuplicateSubtrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private HashMap<String, Integer> count = new HashMap<>();
    private static List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        treeDesc(root);
        return result;
    }

    private StringBuilder treeDesc(TreeNode root) {
        if (root == null) return new StringBuilder("#");
        StringBuilder leftDesc = treeDesc(root.left);
        StringBuilder rightDesc = treeDesc(root.right);
        StringBuilder treeDesc = new StringBuilder(root.val + "").append("_").append(leftDesc).append("_").append(rightDesc);
        String treeHash = treeDesc.toString();
        if (!count.containsKey(treeHash)){
            count.put(treeHash,1);
            count.put(treeHash,count.get(treeHash)+1);
        }
        if (count.get(treeHash) == 2){
            result.add(root);
        }
        return treeDesc;
    }
}
