package com.example.springboot.leetcood;

/**
 * Leetcood 654.最大二叉树
 *
 * @author duwenxu
 * @create 2021-01-19 15:30
 */
public class ConstructMaximumBinaryTree {

    /**
     * 对于构造二叉树的问题，根节点要做的就是把想办法把自己构造出来
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int from, int to) {
        if (nums.length == 0) return null;

        //确定当前数组中的最大值及其索引
        int index = -1, max = Integer.MIN_VALUE;
        for (int i = from; i < to; i++) {
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }

        //构建当前节点
        TreeNode root = new TreeNode(max);
        root.left = construct(nums, from, index - 1);
        root.right = construct(nums, index, to);

        return root;
    }


    public static class TreeNode {
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
}
