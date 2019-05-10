package com.wld.java.leetcode;

public class leet075 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public void sortColors(int[] nums) {
//        int bucket[] = new int[3];
//        for (int i = 0, length = nums.length; i < length; i++) {
//            bucket[nums[i]]++;
//        }
//        for (int i = 1; i < bucket.length; i++) {
//            bucket[i] += bucket[i - 1];
//        }
//        int temp[] = new int[nums.length];
//        System.arraycopy(nums, 0, temp, 0, nums.length);
//        for (int i = 0, length = nums.length; i < length; i++) {
//            nums[--bucket[temp[i]]] = temp[i];
//        }
//    }

//    public void sortColors(int[] nums) {
//        int second = nums.length - 1, zero = 0;
//        for (int i = 0; i <= second; i++) {
//            while (nums[i] == 2 && i < second) swap(nums, i, second--);
//            while (nums[i] == 0 && i > zero) swap(nums, i, zero++);
//        }
//    }
//
//    private void swap(int[] A, int i, int j) {
//        if (i != j) {
//            A[i] ^= A[j];
//            A[j] ^= A[i];
//            A[i] ^= A[j];
//        }
//    }


//    public void sortColors(int[] nums) {
//        int num0 = 0, num1 = 0, num2 = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) ++num0;
//            else if (nums[i] == 1) ++num1;
//            else if (nums[i] == 2) ++num2;
//        }
//        for (int i = 0; i < num0; ++i) nums[i] = 0;
//        for (int i = 0; i < num1; ++i) nums[num0 + i] = 1;
//        for (int i = 0; i < num2; ++i) nums[num0 + num1 + i] = 2;
//    }


//    public void sortColors(int[] nums) {
//        int n0 = 0, n1 = 0, n2 = 0;
//        for (int i = 0; i < nums.length; ++i) {
//            if (nums[i] == 0) {
//                nums[n2++] = 2;
//                nums[n1++] = 1;
//                nums[n0++] = 0;
//            } else if (nums[i] == 1) {
//                nums[n2++] = 2;
//                nums[n1++] = 1;
//            } else if (nums[i] == 2) {
//                nums[n2++] = 2;
//            }
//        }
//    }

    public void sortColors(int[] nums) {
        int zero = 0, l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] == 0)
                swap(nums, l++, zero++);
            else if (nums[l] == 2)
                swap(nums, l, r--);
            else
                l++;
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }
}