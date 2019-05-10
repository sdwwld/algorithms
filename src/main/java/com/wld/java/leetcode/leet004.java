package com.wld.java.leetcode;

public class leet004 {

    public static void main(String args[]) {
        System.out.println();

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int temp[] = new int[totalLength];
        int index = 0;
        for (int i = 0, j = 0; i + j < totalLength; ) {
            if (i >= nums1.length) {
                temp[index++] = nums2[j++];
                continue;
            }
            if (j >= nums2.length) {
                temp[index++] = nums1[i++];
                continue;
            }
            if (nums1[i] > nums2[j]) {
                temp[index++] = nums2[j++];
            } else if (nums1[i] < nums2[j]) {
                temp[index++] = nums1[i++];
            } else {
                temp[index++] = nums1[i++];
                temp[index++] = nums2[j++];
            }
        }
        return (temp[totalLength >> 1] + temp[((totalLength - 1) >> 1)]) * .5;
    }
}