package com.wld.java.leetcode;

public class leet344 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public String reverseString(String s) {
//        char[] word = s.toCharArray();
//        int i = 0;
//        int j = s.length() - 1;
//        while (i < j) {
//            word[i] = (char) (word[i] ^ word[j]);
//            word[j] = (char) (word[i] ^ word[j]);
//            word[i] = (char) (word[i] ^ word[j]);
//            i++;
//            j--;
//        }
//        return new String(word);
//    }

//    public String reverseString(String s) {
//        int length = s.length();
//        if (length < 2)
//            return s;
//        return reverseString(s.substring(length / 2)) + reverseString(s.substring(0, length / 2));
//    }

    public String reverseString(String s) {
        int length = s.length();
        StringBuilder ret = new StringBuilder(length);
        for (int i = length - 1; i >= 0; --i)
            ret.append(s.charAt(i));
        return ret.toString();
    }

}