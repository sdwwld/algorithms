package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

import java.util.Stack;

public class leet104 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int maxDepth(TreeNode root) {
//        if (root == null)
//            return 0;
//        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
//    }

    //BFS
//    public int maxDepth(TreeNode root) {
//        if (root == null)
//            return 0;
//        Deque<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        int count = 0;
//        while (!stack.isEmpty()) {
//            int size = stack.size();
//            while (size-- > 0) {
//                TreeNode cur = stack.pop();
//                if (cur.left != null)
//                    stack.addLast(cur.left);
//                if (cur.right != null)
//                    stack.addLast(cur.right);
//            }
//            count++;
//        }
//        return count;
//    }

    //DFS
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if (node.left != null) {
                stack.push(node.left);
                value.push(temp + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                value.push(temp + 1);
            }
        }
        return max;
    }

}