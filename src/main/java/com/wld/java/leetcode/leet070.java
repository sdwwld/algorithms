package com.wld.java.leetcode;

public class leet070 {

    public static void main(String args[]) {
        System.out.println();

    }

    public int climbStairs(int n) {
        if (n < 0)
            return -1;
        if (n <= 2) return n;
        int first = 1, second = 2, sum = 0;
        while (n-- > 2) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }

//    public int climbStairs(int n) {
//        if (n <= 2) return n;
//        return climbStairs(n - 1) + climbStairs(n - 2);
//    }
}