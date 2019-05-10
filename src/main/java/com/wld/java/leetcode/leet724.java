package com.wld.java.leetcode;

public class leet724 {
    public static void main(String args[]) {
        int[] nums = {-1, -1, -1, -1, -1, 0};
        System.out.print(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        int smallTotal = 0;
        for (int j = 0; j < nums.length; j++) {
            smallTotal += nums[j];
            if (sum - nums[j] == smallTotal - nums[j] << 1)
                return j;
        }
        return -1;
    }
}
