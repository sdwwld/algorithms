package com.wld.java.leetcode;

import java.util.Arrays;

public class leet088 {
    public static void main(String args[]) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.print(Arrays.toString(nums1));
    }

//    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        int temp[] = new int[m + n];
//        int index = 0;
//        int i = 0;
//        int j = 0;
//        while (i < m && j < n) {
//            if (nums1[i] <= nums2[j])
//                temp[index++] = nums1[i++];
//            else
//                temp[index++] = nums2[j++];
//        }
//        for (; i < m; ) {
//            temp[index++] = nums1[i++];
//        }
//        for (; j < n; ) {
//            temp[index++] = nums2[j++];
//        }
//        for (int k = 0; k <m + n ; k++) {
//            nums1[k]=temp[k];
//        }
//    }

//    public static void merge(int A[], int m, int B[], int n) {
//        int i = m - 1;
//        int j = n - 1;
//        int k = m + n - 1;
//        while (i >= 0 && j >= 0) {
//            if (A[i] > B[j])
//                A[k--] = A[i--];
//            else
//                A[k--] = B[j--];
//        }
//        while (j >= 0)
//            A[k--] = B[j--];
//    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, tar = m + n - 1;
        while (j >= 0) {
            nums1[tar--] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }

}
