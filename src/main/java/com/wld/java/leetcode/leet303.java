package com.wld.java.leetcode;

public class leet303 {

    public static void main(String args[]) {
        System.out.println();

    }

    public class NumArray {
        int[] nums;

        public NumArray(int[] nums) {
            for (int i = 1; i < nums.length; i++)
                nums[i] += nums[i - 1];
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            if (i == 0)
                return nums[j];
            return nums[j] - nums[i - 1];
        }
    }

}