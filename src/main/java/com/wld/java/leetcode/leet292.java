package com.wld.java.leetcode;

public class leet292 {

    public static void main(String args[]) {
        System.out.println();

    }
    public boolean canWinNim(int n) {
        return (n & 3) != 0;
    }

//    public boolean canWinNim(int n) {
//        return n % 4 != 0;
//    }

//    public boolean canWinNim(int n) {
//        if (n >= 134882061) {
//            return n % 4 != 0;
//        }
//        int[] array = new int[n + 1];
//        return dfs(n, array);
//    }
//
//    public boolean dfs(int n, int[] array) {
//        if (array[n] != 0) {
//            return array[n] == 1 ? true : false;
//        }
//        if (n <= 3) {
//            array[n] = 1;
//            return true;
//        } else {
//            for (int i = 1; i <= 3; i++) {
//                if (!dfs(n - i, array)) {
//                    array[n - i] = -1;
//                    array[n] = 1;
//                    return true;
//                }
//            }
//            array[n] = -1;
//            return false;
//        }
//    }

}