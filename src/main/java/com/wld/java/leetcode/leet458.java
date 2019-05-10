package com.wld.java.leetcode;

public class leet458 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
//        int pigs = 0;
//        while (Math.pow(minutesToTest / minutesToDie + 1, pigs) < buckets) {
//            pigs += 1;
//        }
//        return pigs;
//    }

//    public int poorPigs(int buckets, int m, int p) {
//        int t = p / m + 1;
//        return (int) Math.ceil(Math.log(buckets) / Math.log(t));
//    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int count = 0;
        if (buckets > 0 && minutesToDie <= minutesToTest) {
            int base = minutesToTest / minutesToDie;
            count = 0;
            while (buckets > 1) {
                buckets /= base;
                count += 1;
            }
        }
        return count;
    }


}