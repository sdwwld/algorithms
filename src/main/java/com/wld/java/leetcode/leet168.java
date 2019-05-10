package com.wld.java.leetcode;

public class leet168 {

    public static void main(String args[]) {
        System.out.println();

    }

    public String convertToTitle(int n) {
        return n == 0 ? "" : convertToTitle(--n / 26) + (char) ('A' + (n % 26));
    }
}