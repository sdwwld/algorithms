package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class leet226 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public TreeNode invertTree(TreeNode root) {//DFS
//        if (root == null)
//            return null;
//        final TreeNode left = root.left, right = root.right;
//        root.left = invertTree(right);
//        root.right = invertTree(left);
//        return root;
//    }


//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return null;
//        TreeNode tmp = root.left;
//        root.left = invertTree(root.right);
//        root.right = invertTree(tmp);
//        return root;
//    }


    public TreeNode invertTree(TreeNode root) {//BFS
        if (root == null)
            return null;
        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }


//    public TreeNode invertTree(TreeNode root) {
//        if (root != null) {
//            TreeNode temp = root.left;
//            root.left = root.right;
//            root.right = temp;
//            invertTree(root.left);
//            invertTree(root.right);
//        }
//        return root;
//    }


//    public TreeNode invertTree(TreeNode root) {//BFS
//        Stack<TreeNode> stk = new Stack<>();
//        stk.push(root);
//        while (!stk.empty()) {
//            TreeNode p = stk.pop();
//            if (p != null) {
//                TreeNode temp = p.left;
//                p.left = p.right;
//                p.right = temp;
//                stk.push(p.left);
//                stk.push(p.right);
//            }
//        }
//        return root;
//    }

}