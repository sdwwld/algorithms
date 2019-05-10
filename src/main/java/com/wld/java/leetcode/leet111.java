package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

public class leet111 {

    public static void main(String args[]) {
        System.out.println();

    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

//    public int minDepth(TreeNode root) {
//        if (root == null)
//            return 0;
//        if (root.left != null && root.right != null)
//            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
//        return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
//    }


//    public int minDepth(TreeNode root) {
//        if (root == null) return 0;
//        Queue<TreeNode> Q = new LinkedList<>();
//        Q.add(root);
//        int i = 0;
//        while (!Q.isEmpty()) {
//            i++;
//            int k = Q.size();
//            for (int j = 0; j < k; j++) {
//                TreeNode rt = Q.peek();
//                if (rt.left != null) Q.add(rt.left);
//                if (rt.right != null) Q.add(rt.right);
//                Q.poll();
//                if (rt.left == null && rt.right == null) return i;
//            }
//        }
//        return -1;
//    }

}