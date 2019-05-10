package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

public class leet112 {

    public static void main(String args[]) {
        System.out.println();

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum - root.val == 0) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


//    public boolean hasPathSum(TreeNode root, int sum) {
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (!stack.isEmpty() && root != null) {
//            TreeNode cur = stack.pop();
//            if (cur.left == null && cur.right == null) {
//                if (cur.val == sum) return true;
//            }
//            if (cur.right != null) {
//                cur.right.val = cur.val + cur.right.val;
//                stack.push(cur.right);
//            }
//            if (cur.left != null) {
//                cur.left.val = cur.val + cur.left.val;
//                stack.push(cur.left);
//            }
//        }
//        return false;
//    }

//    public boolean hasPathSum(TreeNode root, int sum) {
//        if (root == null)
//            return false;
//        Stack<TreeNode> path = new Stack<>();
//        Stack<Integer> sub = new Stack<>();
//        path.push(root);
//        sub.push(root.val);
//        while (!path.isEmpty()) {
//            TreeNode temp = path.pop();
//            int tempVal = sub.pop();
//            if (temp.left == null && temp.right == null) {
//                if (tempVal == sum) return true;
//            } else {
//                if (temp.left != null) {
//                    path.push(temp.left);
//                    sub.push(temp.left.val + tempVal);
//                }
//                if (temp.right != null) {
//                    path.push(temp.right);
//                    sub.push(temp.right.val + tempVal);
//                }
//            }
//        }
//        return false;
//    }

}