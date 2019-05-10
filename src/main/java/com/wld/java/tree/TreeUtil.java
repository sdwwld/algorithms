package com.wld.java.tree;

import com.wld.java.utils.TreeNode;

public class TreeUtil {
    //二叉树的深度
    public int treeDepth(TreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = treeDepth(node.left);
        int rightDepth = treeDepth(node.right);
        return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
    }

    //判断二叉树是否平衡
    public boolean isbalance(TreeNode node) {
        if (node == null)
            return true;
        int leftDepth = treeDepth(node.left);
        int rightDepth = treeDepth(node.right);
        if (leftDepth - rightDepth > 1 || leftDepth - rightDepth < -1)
            return false;
        return isbalance(node.left) && isbalance(node.right);
    }

}
