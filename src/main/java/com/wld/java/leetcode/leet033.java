package com.wld.java.leetcode;

public class leet033 {

    public static void main(String args[]) {
        System.out.println();

    }

    public static int search(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            double num = (nums[mid] < nums[0]) == (target < nums[0])
                    ? nums[mid]
                    : (target < nums[0] ? -Double.MAX_VALUE : Double.MAX_VALUE);
            if (num < target)
                lo = mid + 1;
            else if (num > target)
                hi = mid;
            else
                return mid;
        }
        return -1;
    }
}