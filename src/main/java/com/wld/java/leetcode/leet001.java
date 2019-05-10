package com.wld.java.leetcode;

public class leet001 {
    public static void main(String args[]) {
        System.out.print(8 + 9 >> 1);
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }
}
