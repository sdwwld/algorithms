package com.wld.java.leetcode;

public class leet441 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int arrangeCoins(int n) {
//        int i = 0;
//        while (n > 0) {
//            i += 1;
//            n -= i;
//        }
//        return n == 0 ? i : i - 1;
//    }

//    public int arrangeCoins(int n) {
//        long l = 0, r = n, t = 2 * (long) n;
//        while (l < r) {
//            long m = (l + r + 1) / 2;
//            if ((1 + m) * m <= t) l = m;
//            else r = m - 1;
//        }
//        return (int) l;
//    }

//    public int arrangeCoins(int n) {
//        return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2);
//    }

//    public int arrangeCoins(int n) {
//        if (n < 1)
//            return 0;
//        for (int i = 1; ; i++) {
//            n -= i;
//            if (n < 0)
//                return i - 1;
//        }
//    }

//    public int arrangeCoins(int n) {
//        long nLong = (long) n;
//        long st = 0;
//        long ed = nLong;
//        long mid = 0;
//        while (st <= ed) {
//            mid = st + (ed - st) / 2;
//            if (mid * (mid + 1) <= 2 * nLong) {
//                st = mid + 1;
//            } else {
//                ed = mid - 1;
//            }
//        }
//        return (int) (st - 1);
//    }

}