package com.wld.java.leetcode;

public class leet067 {

    public static void main(String args[]) {
        System.out.println();

    }

    public String addBinary(String a, String b) {
        StringBuilder s = new StringBuilder();
        int c = 0, i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || c == 1) {
            c += i >= 0 ? a.charAt(i--) - '0' : 0;
            c += j >= 0 ? b.charAt(j--) - '0' : 0;
            s.insert(0, (char) ((c & 1) + '0'));
            c >>>= 1;
        }
        return s.toString();
    }
}