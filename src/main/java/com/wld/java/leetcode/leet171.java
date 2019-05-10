package com.wld.java.leetcode;

public class leet171 {

    public static void main(String args[]) {
        System.out.println();

    }

    public int titleToNumber(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); result = result * 26 + (s.charAt(i) - 'A' + 1), i++) ;
        return result;
    }


}