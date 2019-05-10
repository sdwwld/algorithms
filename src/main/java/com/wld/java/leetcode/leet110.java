package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

public class leet110 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean isBalanced(TreeNode root) {
//        if (root == null) return true;
//        int left = depth(root.left);
//        int right = depth(root.right);
//        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//    }
//
//    public int depth(TreeNode root) {
//        if (root == null) return 0;
//        return Math.max(depth(root.left), depth(root.right)) + 1;
//    }

//    public boolean isBalanced(TreeNode root) {
//        return check(root) != -1;
//    }
//
//    private int check(TreeNode root) {
//        if (root == null) return 0;
//        int left = check(root.left);
//        int right = check(root.right);
//        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
//            return -1;
//        return 1 + Math.max(left, right);
//    }


    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (Math.abs(l - r) > 1) {
            result = false;
            return -1;
        }
        return 1 + Math.max(l, r);
    }

}