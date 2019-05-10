package com.wld.java.leetcode;

public class leet026 {
    public static void main(String args[]) {
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int i = 0, j = i + 1;
        while (j != nums.length) {
            if (nums[i] != nums[j]) {
                i++;
                if (i != j) {
                    nums[i] = nums[j];
                }
            }
            j++;
        }
        return i + 1;
    }
}
