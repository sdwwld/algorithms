package com.wld.java.leetcode;

public class leet045 {

    public static void main(String args[]) {
        int[] nums = {2, 3, 1, 1, 4, 3};
//        int[] nums = {2, 3, 1, 4, 1, 3};
//        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(jump2(nums));
    }

    public static int jump(int A[]) {
        if (A.length < 2) return 0;
        int jumps = 0, currentMax = 0, i = 0, nextMax = 0;
        while (i <= currentMax) {
            jumps++;
            for (; i <= currentMax; i++) {
                nextMax = Math.max(nextMax, A[i] + i);
                if (nextMax >= A.length - 1)
                    return jumps;
            }
            currentMax = nextMax;
        }
        return 0;
    }

    public static int jump2(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}