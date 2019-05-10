package com.wld.java.leetcode;

public class leet029 {
    public static void main(String args[]) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(divide(-2147483648, -1));
//        System.out.println(new leet029().divide(-2147483648, -1));
    }

//    public static int divide(int dividend, int divisor) {
//        if (divisor == 0 || dividend == 0) return 0;
//        int sign = dividend < 0 ^ divisor < 0 ? -1 : 1;
//        dividend = dividend < 0 ? dividend : -dividend;
//        divisor = divisor < 0 ? divisor : -divisor;
//        int res = 0;
//        int minDiv = Integer.MIN_VALUE >> 1;
//        while (dividend <= divisor) {
//            int tryDiv = divisor;
//            int times = 1;
//            while (tryDiv >= minDiv && dividend <= tryDiv << 1) {
//                tryDiv <<= 1;
//                times <<= 1;
//            }
//            dividend -= tryDiv;
//            res -= times;
//        }
//        if (sign > 0 && res == Integer.MIN_VALUE) return Integer.MAX_VALUE;
//        return sign < 0 ? res : 0 - res;
//    }
    public static int divide(int dividend, int divisor) {
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

//    public int divide(int dividend, int divisor) {
//        if (dividend != Integer.MIN_VALUE && Math.abs(dividend) < Math.abs(divisor))
//            return 0;
//        int count = 0;
//        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
//            int result = dividend - divisor;
//            count++;
//            while (Math.abs(result) >= Math.abs(divisor)) {
//                result -= divisor;
//                if (count == Integer.MAX_VALUE)
//                    return Integer.MAX_VALUE;
//                count++;
//            }
//            return count;
//        } else {
//            int result = dividend + divisor;
//            count--;
//            while (Math.abs(result) >= Math.abs(divisor)) {
//                result += divisor;
//                if (count == Integer.MIN_VALUE)
//                    return Integer.MAX_VALUE;
//                count--;
//            }
//            return count;
//        }
//    }
}
