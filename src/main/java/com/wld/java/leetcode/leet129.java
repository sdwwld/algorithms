package com.wld.java.leetcode;

public class leet129 {

    public static void main(String args[]) {
        System.out.println();

    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == 0) return 0;
        int sign = dividend < 0 ^ divisor < 0 ? -1 : 1;
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        int res = 0;
        int minDiv = Integer.MIN_VALUE >> 1;
        while (dividend <= divisor) {
            int tryDiv = divisor;
            int times = 1;
            while (tryDiv >= minDiv && dividend <= tryDiv << 1) {
                tryDiv <<= 1;
                times <<= 1;
            }
            dividend -= tryDiv;
            res -= times;
        }
        if (sign > 0 && res == Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return sign < 0 ? res : 0 - res;
    }
}