package com.wld.java.leetcode;

import java.util.Arrays;

public class leet628 {
    public static void main(String args[]) {
        int aa[] = {9, 5, 6, 1, 3, 2, 4, 7};
        System.out.println(Arrays.toString(aa));
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int a = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        int b = nums[0] * nums[1] * nums[nums.length - 1];
        return a > b ? a : b;
    }
}
