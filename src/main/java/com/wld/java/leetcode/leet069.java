package com.wld.java.leetcode;

public class leet069 {

    public static void main(String args[]) {
        System.out.println(new leet069().mySqrt(64));

    }


//    public int mySqrt(int x) {
//        if (x == 0)
//            return 0;
//        int left = 1, right = Integer.MAX_VALUE;
//        while (true) {
//            int mid = left + (right - left) / 2;
//            if ((long) mid * mid > x) {
//                right = mid - 1;
//            } else {
//                if ((long) (mid + 1) * (mid + 1) > x)
//                    return mid;
//                left = mid + 1;
//            }
//        }
//    }

//    public int mySqrt(int x) {
//        if (x == 0)
//            return 0;
//        int left = 1, right = Integer.MAX_VALUE;
//        while (true) {
//            int mid = left + (right - left) / 2;
//            if (mid > x / mid) {
//                right = mid - 1;
//            } else {
//                if (mid + 1 > x / (mid + 1))
//                    return mid;
//                left = mid + 1;
//            }
//        }
//    }


    public int mySqrt(int x) {
        long r = x;
        while (r * r > x)
            r = (r + x / r) >> 1;
        return (int) r;
    }

}