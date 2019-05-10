package com.wld.java.leetcode;

public class leet189 {

    public static void main(String args[]) {
        System.out.println();

    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


//    public void rotate(int nums[], int k) {
//        int n = nums.length;
//        if ((n == 0) || (k <= 0))
//            return;
//        int numsCopy[] = new int[n];
//        for (int i = 0; i < n; i++) {
//            numsCopy[i] = nums[i];
//        }
//        for (int i = 0; i < n; i++) {
//            nums[(i + k) % n] = numsCopy[i];
//        }
//    }

}