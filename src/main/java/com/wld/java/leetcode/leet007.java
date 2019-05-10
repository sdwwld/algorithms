package com.wld.java.leetcode;

public class leet007 {

    public static void main(String args[]) {
        System.out.println();

    }

    //    public int reverse(int x) {
//        int result = 0;
//        while (x != 0) {
//            int tail = x % 10;
//            int newResult = result * 10 + tail;
//            if ((newResult - tail) / 10 != result) {
//                return 0;
//            }
//            result = newResult;
//            x = x / 10;
//        }
//        return result;
//    }

//    public int reverse(int x) {
//        long res = 0;
//        while (x != 0) {
//            res *= 10;
//            res += x % 10;
//            x /= 10;
//        }
//        return (int) res == res ? (int) res : 0;
//    }

    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }
}