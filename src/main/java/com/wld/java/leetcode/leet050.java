package com.wld.java.leetcode;

public class leet050 {

    public static void main(String args[]) {
        System.out.println(new leet050().myPow(2, -2));

    }


//    public double myPow(double x, int n) {
//        if (n == 0)
//            return 1;
//        if (n < 0) {
//            return 1 / x * myPow(1 / x, -(n + 1));
//        }
//        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
//    }

//    public double myPow(double x, int n) {
//        if (n == 0)
//            return 1;
//        if (n < 0) {
//            return 1 / x * myPow(x, n + 1);
//        }
//        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
//    }

    public double myPow(double a, int b) {
        double r = 1, base = a;
        while (b != 0) {
            if ((b & 1) == 1) r *= base;
            base *= base;
            b >>= 1;
        }
        return r;
    }

}