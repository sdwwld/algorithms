package com.wld.java.leetcode;

public class leet136 {

    public static void main(String args[]) {
        System.out.println();

    }

    //    public int singleNumber(int A[]) {
//        int result = 0;
//        for (int i = 0, length = A.length; i < length; i++)
//            result ^= A[i];
//        return result;
//    }

    public int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            nums[0] ^= nums[i];
        return nums[0];
    }
}