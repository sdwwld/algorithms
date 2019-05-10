package com.wld.java.leetcode;

public class leet387 {

    public static void main(String args[]) {
        System.out.println();

    }

    //    public int firstUniqChar(String s) {
//        int freq[] = new int[26];
//        for (int i = 0; i < s.length(); i++)
//            freq[s.charAt(i) - 'a']++;
//        for (int i = 0; i < s.length(); i++)
//            if (freq[s.charAt(i) - 'a'] == 1)
//                return i;
//        return -1;
//    }

//    public int firstUniqChar(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public int firstUniqChar(String s) {
        int alphabet[] = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++alphabet[s.charAt(i) - 'a'];
        }
        int i = 0;
        while (i < s.length() && alphabet[s.charAt(i) - 'a'] > 1) ++i;
        return i == s.length() ? -1 : i;
    }


}