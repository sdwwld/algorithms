package com.wld.java.tree;

import com.wld.java.utils.TreeNode;

import java.util.Stack;

public class Mirror {

    public TreeNode mirror(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || stack.size() != 0) {
            while (node != null) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                stack.push(node);
                node = node.left;
            }
            if (stack.size() != 0) {
                node = stack.pop().right;
            }
        }
        return node;
    }
}
