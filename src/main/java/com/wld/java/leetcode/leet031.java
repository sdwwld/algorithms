package com.wld.java.leetcode;

import java.util.Arrays;

public class leet031 {
    public static void main(String args[]) {
//        int aa[] = {9,5,6,1,3,2,4,7};
        int aa[] = {1,2,9,8,7,6,5,0};
        new leet031().nextPermutation(aa);
        System.out.println(Arrays.toString(aa));
    }

    public void nextPermutation(int[] A) {
        if (A == null || A.length <= 1) return;
        int i = A.length - 2;
        while (i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
        if (i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while (A[j] <= A[i]) j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while (i < j) swap(A, i++, j--);
    }
}
