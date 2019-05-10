package com.wld.java.leetcode;


import com.wld.java.utils.TreeNode;

import java.util.Stack;

public class leet404 {
    public static void main(String args[]) {
        System.out.print(8 + 9 >> 1);
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        return sumOfLeftLeaves(root, false);
    }

    public int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        if (isLeft && root.left == null & root.right == null)
            return root.val;
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }

//    public int sumOfLeftLeaves(TreeNode root) {
//        if (root == null) return 0;
//        int total = 0;
//        if (root.left != null) {
//            if (root.left.left == null && root.left.right == null)
//                total += root.left.val;
//            else
//                total += sumOfLeftLeaves(root.left);
//        }
//        total += sumOfLeftLeaves(root.right);
//        return total;
//    }

//    public int sumOfLeftLeaves(TreeNode root) {
//        if (root == null)
//            return 0;
//        TreeNode left = root.left;
//        if (left != null && left.left == null && left.right == null) {
//            return left.val + sumOfLeftLeaves(root.right);
//        } else {
//            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
//        }
//    }

//    public int sumOfLeftLeaves(TreeNode root) {
//        if (root == null) return 0;
//        int ans = 0;
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//
//        while (!stack.empty()) {
//            TreeNode node = stack.pop();
//            if (node.left != null) {
//                if (node.left.left == null && node.left.right == null)
//                    ans += node.left.val;
//                else
//                    stack.push(node.left);
//            }
//            if (node.right != null) {
//                if (node.right.left != null || node.right.right != null)
//                    stack.push(node.right);
//            }
//        }
//        return ans;
//    }

//    public int sumOfLeftLeaves(TreeNode root) {
//        int res = 0;
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            if (node != null) {
//                if (node.left != null && node.left.left == null && node.left.right == null)
//                    res += node.left.val;
//                stack.push(node.left);
//                stack.push(node.right);
//            }
//        }
//        return res;
//    }


//    public int sumOfLeftLeaves(TreeNode root) {
//        int leafs=0;
//        if(root==null) return leafs;
//        if(root.left!=null && (root.left.left==null && root.left.right==null)) leafs+=root.left.val;
//        leafs+=sumOfLeftLeaves(root.left);
//        leafs+=sumOfLeftLeaves(root.right);
//        return leafs;
//    }

//    public int sumOfLeftLeaves(TreeNode root) {
//        if (root == null || root.left == null && root.right == null) return 0;
//        int res = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            TreeNode curr = queue.poll();
//            if (curr.left != null && curr.left.left == null && curr.left.right == null)
//                res += curr.left.val;
//            if (curr.left != null) queue.offer(curr.left);
//            if (curr.right != null) queue.offer(curr.right);
//        }
//        return res;
//    }

}
