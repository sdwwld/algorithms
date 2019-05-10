package com.wld.java.leetcode;

public class leet055 {
    //参见45题
    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean canJump(int[] nums) {
//        int i = 0;
//        for (int reach = 0; i < nums.length && i <= reach; ++i)
//            reach = Math.max(i + nums[i], reach);
//        return i == nums.length;
//    }
//
//    public boolean canJump(int[] nums) {
//        int curMax = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (curMax < i) return false;
//            curMax = Math.max(curMax, i + nums[i]);
//        }
//        return true;
//    }
//
//    public boolean canJump(int[] nums) {
//        int n = nums.length;
//        int reach = 0;
//        for (int i = 0; i < n && i <= reach; i++) {
//            reach = Math.max(i + nums[i], reach);
//            if (reach >= n - 1) return true;
//        }
//        return false;
//    }
//
//    public boolean canJump(int[] nums) {
//        int last = nums.length - 1, i;
//        for (i = nums.length - 2; i >= 0; i--) {
//            if (i + nums[i] >= last) last = i;
//        }
//        return last <= 0;
//    }


    public boolean canJump(int[] nums) {
        int size = nums.length;
        int step = nums[0];
        for (int i = 1; i < size; ++i) {
            step--;
            if (step < 0)
                return false;
            if (nums[i] > step)
                step = nums[i];
        }
        return true;
    }

}