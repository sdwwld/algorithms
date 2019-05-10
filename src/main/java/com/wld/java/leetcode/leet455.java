package com.wld.java.leetcode;

import java.util.Arrays;

public class leet455 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int findContentChildren(int[] g, int[] s) {
//        Arrays.sort(g);
//        Arrays.sort(s);
//        int i = 0;
//        for (int j = 0; i < g.length && j < s.length; j++) {
//            if (g[i] <= s[j]) i++;
//        }
//        return i;
//    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = g.length - 1, j = s.length - 1, count = 0;
        while (i >= 0 && j >= 0) {
            if (g[i] > s[j]) i--;
            else if (g[i--] <= s[j--]) count++;
        }
        return count;
    }

//    public int findContentChildren(int[] g, int[] s) {
//        int count = 0;
//        Arrays.sort(g);
//        Arrays.sort(s);
//        int j = 0;
//        for (int i = 0; i < g.length; i++) {
//            while (j < s.length && s[j] < g[i]) j++;
//            if (j == s.length) break;
//            count++;
//            j++;
//        }
//        return count;
//    }

}