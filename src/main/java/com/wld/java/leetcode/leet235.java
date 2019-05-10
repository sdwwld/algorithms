package com.wld.java.leetcode;

import com.wld.java.utils.TreeNode;

public class leet235 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        while ((root.val - p.val) * (root.val - q.val) > 0)
//            root = p.val < root.val ? root.left : root.right;
//        return root;
//    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return (root.val - p.val) * (root.val - q.val) <=0 ? root :
                lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        while (root != null) {
//            int v = root.val, pv = p.val, qv = q.val;
//            if (v > Math.max(pv, qv)) root = root.left;
//            else if (v < Math.min(pv, qv)) root = root.right;
//            else return root;
//        }
//        return root;
//    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if (root.val > p.val && root.val > q.val) {
//            return lowestCommonAncestor(root.left, p, q);
//        } else if (root.val < p.val && root.val < q.val) {
//            return lowestCommonAncestor(root.right, p, q);
//        } else {
//            return root;
//        }
//    }

    //    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        TreeNode cur = root;
//        while (true) {
//            if ((cur.val > p.val) && (cur.val > q.val)) {
//                cur = cur.left;
//            } else if ((cur.val < p.val) && (cur.val < q.val)) {
//                cur = cur.right;
//            } else {
//                return cur;
//            }
//        }
//    }
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        while (true) {
//            if ((root.val - p.val) * (root.val - q.val) <= 0)
//                return root;
//            root = p.val < root.val ? root.left : root.right;
//        }
//    }

}