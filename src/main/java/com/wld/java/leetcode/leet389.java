package com.wld.java.leetcode;

public class leet389 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public char findTheDifference(String s, String t) {
//        int alp[] = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            alp[s.charAt(i) - 'a']++;
//        }
//        for (int i = 0; i < t.length(); i++) {
//            if (--alp[t.charAt(i) - 'a'] < 0)
//                return t.charAt(i);
//        }
//        return '0';
//    }

//    public char findTheDifference(String s, String t) {
//        int n = t.length();
//        char c = t.charAt(n - 1);
//        for (int i = 0; i < n - 1; ++i) {
//            c ^= s.charAt(i);
//            c ^= t.charAt(i);
//        }
//        return c;
//    }

    public char findTheDifference(String s, String t) {
        char c = 0;
        for (char i : (s + t).toCharArray())
            c ^= i;
        return c;
    }


//    public char findTheDifference(String s, String t) {
//        int charCodeS = 0, charCodeT = 0;
//        for (int i = 0; i < s.length(); ++i) charCodeS += (int) s.charAt(i);
//        for (int i = 0; i < t.length(); ++i) charCodeT += (int) t.charAt(i);
//        return (char) (charCodeT - charCodeS);
//    }

//    public char findTheDifference(String s, String t) {
//        int charCode = t.charAt(s.length());
//        for (int i = 0; i < s.length(); ++i) {
//            charCode -= (int) s.charAt(i);
//            charCode += (int) t.charAt(i);
//        }
//        return (char) charCode;
//    }

}