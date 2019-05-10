package com.wld.java.leetcode;

import java.util.Arrays;

public class leet016 {
    public static void main(String args[]) {
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minGap = Integer.MAX_VALUE;
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int tempMinGap = target - nums[i] - nums[lo] - nums[hi];
                if (Math.abs(tempMinGap) < Math.abs(minGap)) {
                    minGap = tempMinGap;
                    sum = nums[i] + nums[lo] + nums[hi];
                }
                if (tempMinGap == 0)
                    return target;
                else if (tempMinGap > 0)
                    lo++;
                else hi--;
            }
        }
        return sum;
    }
}
