package com.wld.java.leetcode;

public class leet342 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean isPowerOfFour(int num) {
//        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
//    }

    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) == num;
    }

//    public boolean isPowerOfFour(int num) {
//        return num > 0 && ((num & (num - 1)) == 0) && (num & 0xaaaaaaaa) == 0;
//    }

    //4^x-1=(2^x-1)(2^x+1). And 2^x mod 3 is not 0. So either 2^x-1 or 2^x +1 must be 0.
    //连续的3个数2^x-1，2^x ，2^x +1必有一个能被3整除，显然2^x的质因数全部都是2，不能被3整除。
//    public boolean isPowerOfFour(int num) {
//        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
//    }

//    public boolean isPowerOfFour(int num) {
//        return num > 0 && (num == 1 || (num % 4 == 0 && isPowerOfFour(num / 4)));
//    }
}