package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

public class leet101 {

    public static void main(String args[]) {
        System.out.println();

    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return (p.val == q.val) && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

//    public boolean isSymmetric(TreeNode root) {
//        Queue<TreeNode> q = new LinkedList<>();
//        if (root == null) return true;
//        q.add(root.left);
//        q.add(root.right);
//        while (q.size() > 1) {
//            TreeNode left = q.poll(), right = q.poll();
//            if (left == null && right == null) continue;
//            if (left == null ^ right == null) return false;
//            if (left.val != right.val) return false;
//            q.add(left.left);
//            q.add(right.right);
//            q.add(left.right);
//            q.add(right.left);
//        }
//        return true;
//    }
}