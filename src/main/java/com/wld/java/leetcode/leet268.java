package com.wld.java.leetcode;

import java.util.Arrays;

public class leet268 {

    public static void main(String args[]) {
        System.out.println(new leet268().missingNumber(new int[]{0, 1, 3}));

    }

//    public int missingNumber(int[] nums) {
//        int xor = 0, i = 0;
//        for (i = 0; i < nums.length; i++) {
//            xor = xor ^ i ^ nums[i];
//        }
//        return xor ^ i;
//    }


    public int missingNumber(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++)
            xor ^= nums[i] ^ (i + 1);
        return xor;
    }

//    public int missingNumber(int[] nums) {
//        int xor = nums.length;
//        for (int i = 0; i < nums.length; i++) {
//            xor ^= i;
//            xor ^= nums[i];
//        }
//        return xor;
//    }


//    public int missingNumber(int[] nums) {
//        int len = nums.length;
//        int sum = (0 + len) * (len + 1) / 2;
//        for (int i = 0; i < len; i++)
//            sum -= nums[i];
//        return sum;
//    }

//    public int missingNumber(int[] nums) {
//        Arrays.sort(nums);
//        int left = 0, right = nums.length, mid = 0;
//        while (left < right) {
//            mid = (left + right) / 2;
//            if (nums[mid] > mid)
//                right = mid;
//            else
//                left = mid + 1;
//        }
//        return left;
//    }


}