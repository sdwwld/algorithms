package com.wld.java.leetcode;

public class leet053 {
    public static void main(String args[]) {
    }

    public int maxSubArray(int A[]) {
        int ans = A[0], i, j, sum = 0;
        for (i = 0; i < A.length; i++) {
            sum += A[i];
            ans = sum > ans ? sum : ans;
            sum = sum > 0 ? sum : 0;
        }
        return ans;
    }

    public int maxSubArray2(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = A[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
//            max = Math.max(max, dp[i]);
        }
        for (int i = 0; i < n; i++) {
            if (max < dp[i])
                max = dp[i];
        }
        return max;
    }
}
