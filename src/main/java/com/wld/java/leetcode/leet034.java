package com.wld.java.leetcode;

public class leet034 {

    public static void main(String args[]) {
//        int[] aa = {6, 7, 9};
        int[] aa = {6, 7, 7, 7, 7, 7, 9};
        System.out.println(bs(aa, 8));
//        System.out.println(bs(aa, 7 + .5));

    }

    public static int[] searchRange(int[] nums, int target) {
        double left = target - 0.5, right = target + 0.5;
        int _left = bs(nums, left), _right = bs(nums, right);
        if (_left == _right)
            return new int[]{-1, -1};
        return new int[]{_left, _right - 1};
    }

    public static int bs(int[] nums, double target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int m = low + (high - low) / 2;
            if (target > nums[m])
                low = m + 1;
            else
                high = m - 1;
        }
        return low;
    }
}