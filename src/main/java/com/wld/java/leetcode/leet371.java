package com.wld.java.leetcode;

public class leet371 {

    public static void main(String args[]) {
        System.out.println();

    }

    public int getSum(int a, int b) {
        if (a == 0 || b == 0)
            return a ^ b;
        return getSum(a ^ b, (a & b) << 1);
    }

//    public int getSum(int a, int b) {
//        while (b != 0) {
//            int temp = a ^ b;
//            b = (a & b) << 1;
//            a = temp;
//        }
//        return a;
//    }


}