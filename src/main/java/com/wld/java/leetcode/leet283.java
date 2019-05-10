package com.wld.java.leetcode;

public class leet283 {

    public static void main(String args[]) {
        System.out.println();
        new leet283().moveZeroes(new int[]{0, 0, 1});
    }

//    public void moveZeroes(int[] nums) {
//        int length = nums.length;
//        int left = 0, right = length - 1;
//        while (left < right) {
//            if (nums[left] == 0) {
//                for (int i = left; i < right; i++) {
//                    nums[i] = nums[i + 1];
//                }
//                nums[right--] = 0;
//                left--;
//            }
//            left++;
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


//    public void moveZeroes(int[] nums) {
//        if (nums == null || nums.length == 0) return;
//        int insertPos = 0;
//        for (int num : nums) {
//            if (num != 0) nums[insertPos++] = num;
//        }
//        while (insertPos < nums.length) {
//            nums[insertPos++] = 0;
//        }
//    }


//    public void moveZeroes(int[] nums) {
//        int n = nums.length;
//        int m = 0;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] == 0) {
//                m++;
//            } else if (m != 0) {
//                nums[i - m] = nums[i];
//                nums[i] = 0;
//            }
//        }
//    }

//    public void moveZeroes(int[] nums) {
//        int n = nums.length;
//        int i = -1;
//        for (int j = 0; j < n; j++) {
//            if (nums[j] != 0) {
//                i++;
//                swap(nums, i, j);
//            }
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

//    public void moveZeroes(int[] nums) {
//        if (nums == null || nums.length <= 1)
//            return;
//        int last = -1;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != 0) {
//                nums[++last] = nums[i];
//                if (i != last)
//                    nums[i] = 0;
//            }
//        }
//    }

//    public void moveZeroes(int[] nums) {
//        int j = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != 0) {
//                swap(nums, i, j);
//                j++;
//            }
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


    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                count++;
            if (count != 0 && nums[i] != 0) {
                nums[i - count] = nums[i];
                nums[i] = 0;
            }
        }
    }


}