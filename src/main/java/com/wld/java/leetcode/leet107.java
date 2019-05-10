package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet107 {

    public static void main(String args[]) {
        System.out.println();

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new LinkedList<>();
        if (root == null) return wrapList;
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null) queue.add(queue.peek().left);
                if (queue.peek().right != null) queue.add(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }


//    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
//        levelMaker(wrapList, root, 0);
//        return wrapList;
//    }
//
//    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
//        if (root == null) return;
//        if (level >= list.size()) {
//            list.add(0, new LinkedList<Integer>());
//        }
//        levelMaker(list, root.left, level + 1);
//        levelMaker(list, root.right, level + 1);
//        list.get(list.size() - level - 1).add(root.val);
//    }


//    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        List<List<Integer>> wrapList = new LinkedList<>();
//        if (root == null)
//            return wrapList;
//        Deque<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        List<Integer> mList = new ArrayList<>();
//        mList.add(root.val);
//        wrapList.add(mList);
//        while (!stack.isEmpty()) {
//            int size = stack.size();
//            mList = new ArrayList<>();
//            while (size-- > 0) {
//                TreeNode cur = stack.pop();
//                if (cur.left != null) {
//                    stack.addLast(cur.left);
//                    mList.add(cur.left.val);
//                }
//                if (cur.right != null) {
//                    stack.addLast(cur.right);
//                    mList.add(cur.right.val);
//                }
//            }
//            if (!mList.isEmpty())
//                wrapList.add(0, mList);
//        }
//        return wrapList;
//    }


}