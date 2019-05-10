package com.wld.java.leetcode;

public class leet461 {

    public static void main(String args[]) {
        System.out.println(new leet461().hammingDistance(3, 1));

    }

    //    public int hammingDistance(int x, int y) {
//        int count = 0;
//        while (x != 0 || y != 0) {
//            count += (x ^ y) & 1;
//            x >>>= 1;
//            y >>>= 1;
//        }
//        return count;
//    }

//    public int hammingDistance(int x, int y) {
//        return Integer.bitCount(x ^ y);
//    }

    //计算异或结果，转变为求1的个数
    public int hammingDistance(int x, int y) {
        int dist = 0, n = x ^ y;
        while (n != 0) {
            ++dist;
            n &= n - 1;
        }
        return dist;
    }

}