package com.wld.java.leetcode;

public class leet437 {

    public static void main(String args[]) {
        System.out.println();

    }

//    int count = 0;
//
//    public int pathSum(TreeNode root, int sum) {
//        HashMap<Integer, Integer> preSum = new HashMap();
//        preSum.put(0, 1);
//        helper(root, 0, sum, preSum);
//        return count;
//    }
//
//    public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
//        if (root == null)
//            return;
//        currSum += root.val;
//        if (preSum.containsKey(currSum - target)) {
//            count += preSum.get(currSum - target);
//        }
//        if (!preSum.containsKey(currSum)) {
//            preSum.put(currSum, 1);
//        } else {
//            preSum.put(currSum, preSum.get(currSum) + 1);
//        }
//        helper(root.left, currSum, target, preSum);
//        helper(root.right, currSum, target, preSum);
//        preSum.put(currSum, preSum.get(currSum) - 1);
//    }

//    public int pathSum(TreeNode root, int sum) {
//        HashMap<Integer, Integer> preSum = new HashMap();
//        preSum.put(0, 1);
//        return helper(root, 0, sum, preSum);
//    }
//
//    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
//        if (root == null)
//            return 0;
//        currSum += root.val;
//        int res = preSum.getOrDefault(currSum - target, 0);
//        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
//        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
//        preSum.put(currSum, preSum.get(currSum) - 1);
//        return res;
//    }

    //DFS
//    public int pathSum(TreeNode root, int sum) {
//        if (root == null) return 0;
//        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
//    }
//
//    private int pathSumFrom(TreeNode node, int sum) {
//        if (node == null) return 0;
//        return (node.val == sum ? 1 : 0)
//                + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
//    }


//    public int pathSum(TreeNode root, int sum) {
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(0, 1);
//        return backtrack(root, 0, sum, map);
//    }
//
//    public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map) {
//        if (root == null)
//            return 0;
//        sum += root.val;
//        int res = map.getOrDefault(sum - target, 0);
//        map.put(sum, map.getOrDefault(sum, 0) + 1);
//        res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
//        map.put(sum, map.get(sum) - 1);
//        return res;
//    }


//    public int pathSum(TreeNode root, int sum) {
//        int res[] = new int[1];
//        pathSumHelper(root, sum, res, false);
//        return res[0];
//    }
//
//    private void pathSumHelper(TreeNode root, int sum, int res[], boolean parent_used) {
//        if (root == null)
//            return;
//        if (sum - root.val == 0)
//            res[0]++;
//        pathSumHelper(root.left, sum - root.val, res, true);
//        pathSumHelper(root.right, sum - root.val, res, true);
//        if (!parent_used) {
//            pathSumHelper(root.left, sum, res, false);
//            pathSumHelper(root.right, sum, res, false);
//        }
//    }

}