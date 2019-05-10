package com.wld.java.leetcode;

public class leet172 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int trailingZeroes(int n) {
//        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
//    }


//    public int trailingZeroes(int n) {
//        int result = 0;
//        for (long i = 5; n / i > 0; i *= 5) {
//            result += (n / i);
//        }
//        return result;
//    }

    public int trailingZeroes(int n) {
        int r = 0;
        while (n > 0) {
            n /= 5;
            r += n;
        }
        return r;
    }
}