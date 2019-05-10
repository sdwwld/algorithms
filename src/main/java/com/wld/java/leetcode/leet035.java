package com.wld.java.leetcode;

public class leet035 {
    public static void main(String args[]) {
        int[] nums = {1, 3};
        int target = 2;
        System.out.print(new leet035().searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + high >> 1;
            int midValue = nums[mid];
            if (midValue > target)
                high = mid - 1;
            else if (midValue < target)
                low = mid + 1;
            else return mid;
        }
        return low;
    }
}
