package com.wld.java.leetcode;

public class leet066 {
    public static void main(String args[]) {
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int temp[] = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }
}
