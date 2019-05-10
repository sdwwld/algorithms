package com.wld.java.leetcode;

public class leet326 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean isPowerOfThree(int n) {
//        // 1162261467 is 3^19,  3^20 is bigger than int
//        return (n > 0 && 1162261467 % n == 0);
//    }


//    public boolean isPowerOfThree(int n) {
//        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
//    }


//    public boolean isPowerOfThree(int n) {
//        if(n>1)
//            while(n%3==0) n /= 3;
//        return n==1;
//    }


    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

}