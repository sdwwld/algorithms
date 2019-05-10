package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class leet257 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> answer = new ArrayList<>();
//        if (root != null) searchBT(root, "", answer);
//        return answer;
//    }
//
//    private void searchBT(TreeNode root, String path, List<String> answer) {
//        if (root.left == null && root.right == null) answer.add(path + root.val);
//        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
//        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
//    }


//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> res = new ArrayList<String>();
//        if (root == null) {
//            return res;
//        }
//        StringBuilder sb = new StringBuilder();
//        dfs(root, res, sb);
//        return res;
//    }
//
//    private void dfs(TreeNode root, List<String> res, StringBuilder sb) {
//        if (root.left == null && root.right == null) {
//            sb.append("" + root.val);
//            res.add(sb.toString());
//            return;
//        }
//        if (root.left != null) {
//            String prev = sb.toString();
//            sb.append(root.val + "->");
//            dfs(root.left, res, sb);
//            sb = new StringBuilder(prev);
//        }
//        if (root.right != null) {
//            sb.append(root.val + "->");
//            dfs(root.right, res, sb);
//        }
//    }

    /**
     * dfs + stack
     */
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> res = new ArrayList<>();
//        if (root == null)
//            return res;
//        Stack<Object> stack = new Stack<>();
//        stack.push(root);
//        stack.push("");
//        while (!stack.isEmpty()) {
//            String ls = (String) stack.pop();
//            TreeNode node = (TreeNode) stack.pop();
//            if (node.left == null && node.right == null) {
//                res.add(ls + node.val);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//                stack.push(ls + node.val + "->");
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//                stack.push(ls + node.val + "->");
//            }
//        }
//        return res;
//    }

    /**
     * bfs + queue
     */
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> res = new ArrayList<>();
//        if (root == null)
//            return res;
//        Queue<Object> queue = new LinkedList<>();
//        queue.offer(root);
//        queue.offer("");
//        while (!queue.isEmpty()) {
//            TreeNode node = (TreeNode) queue.poll();
//            String ls = (String) queue.poll();
//            if (node.left == null && node.right == null) {
//                res.add(ls + node.val);
//            }
//            if (node.left != null) {
//                queue.offer(node.left);
//                queue.offer(ls + node.val + "->");
//            }
//            if (node.right != null) {
//                queue.offer(node.right);
//                queue.offer(ls + node.val + "->");
//            }
//        }
//        return res;
//    }
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> paths = new LinkedList<>();
//        if (root == null) return paths;
//        if (root.left == null && root.right == null) {
//            paths.add(root.val + "");
//            return paths;
//        }
//        for (String path : binaryTreePaths(root.left)) {
//            paths.add(root.val + "->" + path);
//        }
//        for (String path : binaryTreePaths(root.right)) {
//            paths.add(root.val + "->" + path);
//        }
//        return paths;
//    }
//    public List<String> binaryTreePaths(TreeNode root) {
//        if (root == null) return new ArrayList<>();
//        if (root.left == null && root.right == null) {
//            List<String> temp = new ArrayList<>();
//            temp.add(root.val + "");
//            return temp;
//        }
//        List<String> left = binaryTreePaths(root.left);
//        for (int i = 0; i < left.size(); i++) {
//            left.set(i, root.val + "->" + left.get(i));
//        }
//        List<String> right = binaryTreePaths(root.right);
//        for (int i = 0; i < right.size(); i++) {
//            right.set(i, root.val + "->" + right.get(i));
//        }
//        if (left.isEmpty() || right.isEmpty()) return left.isEmpty() ? right : left;
//        left.addAll(right);
//        return left;
//    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> l = new ArrayList<>();
        if (root != null)
            pre(l, root, "");
        return l;
    }

    void pre(List<String> l, TreeNode r, String s) {
        if (r == null) return;
        if (s.isEmpty())
            s += r.val;
        else s += ("->" + r.val);
        if (r.left != null || r.right != null) {
            pre(l, r.left, s);
            pre(l, r.right, s);
        } else
            l.add(s);
    }


}