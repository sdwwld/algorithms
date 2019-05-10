package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;


public class leet429 {

    public static void main(String args[]) {
        System.out.println();


    }

    // public List<List<Integer>> levelOrder(Node root) {//二叉树的遍历，不是这一题的。
    //     List<List<Integer>> res = new ArrayList<>();
    //     if (root == null) return res;
    //     Queue<TreeNode> q = new LinkedList<>();
    //     q.offer(root);
    //     while (!q.isEmpty()) {
    //         int size = q.size();
    //         List<Integer> level = new ArrayList<>();
    //         for (int i = 0; i < size; i++) {
    //             TreeNode tmp = q.poll();
    //             level.add(tmp.val);
    //             if (tmp.left != null)
    //                 q.offer(tmp.left);
    //             if (tmp.right != null)
    //                 q.offer(tmp.right);
    //         }
    //         res.add(level);
    //     }
    //     return res;
    // }}

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

//    public List<List<Integer>> levelOrder(Node root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        Queue<Node> q = new LinkedList<>();
//        q.offer(root);
//        while (!q.isEmpty()) {
//            int size = q.size();
//            List<Integer> level = new ArrayList<>();
//            for (int i = 0; i < size; i++) {
//                Node tmp = q.poll();
//                level.add(tmp.val);
//                q.addAll(tmp.children);
//            }
//            res.add(level);
//        }
//        return res;
//    }

    public List<List<Integer>> levelOrder(Node root) {
        return levelOrder(root, 0, new ArrayList<>());
    }

    private List<List<Integer>> levelOrder(Node node, int level, List<List<Integer>> order) {
        if (node == null) {
            return order;
        }
        List<Integer> list = order.size() > level ? order.get(level) : new ArrayList<>();
        list.add(node.val);
        if (order.size() <= level) {
            order.add(list);
        }
        for (Node n : node.children) {
            levelOrder(n, level + 1, order);
        }
        return order;
    }


}