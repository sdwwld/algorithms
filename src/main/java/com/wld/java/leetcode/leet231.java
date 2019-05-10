package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet231 {

    public static void main(String args[]) {
        System.out.println(Util.bitInt32(8));
        System.out.println(Util.bitInt32(-8));

    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

//    public boolean isPowerOfTwo(int n) {
//        return n > 0 && (n & -n) == n;
//    }
//
//    public boolean isPowerOfTwo(int n) {
//        return n > 0 && Math.pow(2, 31) % n == 0;
//    }
//
//    public boolean isPowerOfTwo(int n) {
//        return n > 0 && (Math.log10(n) / Math.log10(2)) % 1 == 0;
//    }
//
//    public boolean isPowerOfTwo(int n) {
//        return n > 0 && Integer.bitCount(n) == 1;
//    }
//
//    public boolean isPowerOfTwo(int n) {
//        if (n == 0) return false;
//        while (n % 2 == 0) n /= 2;
//        return n == 1;
//    }
}