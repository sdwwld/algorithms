package com.wld.java.leetcode;

public class leet058 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int lengthOfLastWord(String s) {
//        int len = 0, tail = s.length() - 1;
//        while (tail >= 0 && s.charAt(tail) == ' ') tail--;
//        while (tail >= 0 && s.charAt(tail) != ' ') {
//            len++;
//            tail--;
//        }
//        return len;
//    }


    public int lengthOfLastWord(String s) {
        int len = 0, tail = s.length() - 1;
        while (tail >= 0 && s.charAt(tail) == ' ') tail--;
        while (tail >= 0 && s.charAt(tail--) != ' ') {
            len++;
        }
        return len;
    }
}