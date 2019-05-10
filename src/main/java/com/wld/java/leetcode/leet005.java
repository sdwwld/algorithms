package com.wld.java.leetcode;

public class leet005 {

    public static void main(String args[]) {
        System.out.println(longestPalindrome("eeeeeeee"));
        System.out.println(longestPalindrome("caaaaaaab"));
        System.out.println(longestPalindrome("abcfcbx"));

    }
//    private int lo, maxLen;
//
//    public String longestPalindrome(String s) {
//        int len = s.length();
//        if (len < 2)
//            return s;
//
//        for (int i = 0; i < len-1; i++) {
//            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
//            extendPalindrome(s, i, i+1); //assume even length.
//        }
//        return s.substring(lo, lo + maxLen);
//    }
//
//    private void extendPalindrome(String s, int j, int k) {
//        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
//            j--;
//            k++;
//        }
//        if (maxLen < k - j - 1) {
//            lo = j + 1;
//            maxLen = k - j - 1;
//        }
//    }


//    String longestPalindrome(String s) {
//        if (s.isEmpty()) return "";
//        if (s.length() == 1) return s;
//        int min_start = 0, max_len = 1;
//        for (int i = 0; i < s.length(); ) {
//            if (s.length() - i <= max_len / 2) break;
//            int j = i, k = i;
//            while (k < s.length() - 1 && s.charAt(k + 1) == s.charAt(k))
//                ++k; // Skip duplicate characters.
//            i = k + 1;
//            while (k < s.length() - 1 && j > 0 && s.charAt(k + 1) == s.charAt(j - 1)) {
//                ++k;
//                --j;
//            } // Expand.
//            int new_len = k - j + 1;
//            if (new_len > max_len) {
//                min_start = j;
//                max_len = new_len;
//            }
//        }
//        return s.substring(min_start, max_len);
//    }


//    String longestPalindrome(String s) {
//        if (s.isEmpty()) return "";
//        if (s.length() == 1) return s;
//        int min_start = 0, max_len = 1;
//        for (int i = 0; i < s.length(); ) {
//            if (s.length() - i <= max_len / 2) break;
//            int j = i, k = i;
//            while (k < s.length() - 1 && s.charAt(k + 1) == s.charAt(k))
//                ++k; // Skip duplicate characters.
//            i = k + 1;
//            while (k < s.length() - 1 && j > 0 && s.charAt(k + 1) == s.charAt(j - 1)) {
//                ++k;
//                --j;
//            } // Expand.
//            int new_len = k - j + 1;
//            if (new_len > max_len) {
//                min_start = j;
//                max_len = new_len;
//            }
//        }
//        return s.substring(min_start, max_len + min_start);
//    }

    private static String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        int len = s.length(), max_left = 0, max_len = 1, left, right;
        //max_len表示查找到的最长的字符串，
        for (int start = 0; start < len && len - start > max_len / 2; ) {
            //每次开始的时候left往左移，right往右移
            left = right = start;
            //如果有相同的则跳过相同的
            while (right < len - 1 && s.charAt(right + 1) == s.charAt(right))
                ++right;
            start = right + 1;
            //一个往前一个往后移动，然后对比
            while (right < len - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                ++right;
                --left;
            }
            //记录查找的最长子回文串的开始位置以及长度
            if (max_len < right - left + 1) {
                max_left = left;
                max_len = right - left + 1;
            }
        }
        //截取最长的子回文串
        return s.substring(max_left, max_len + max_left);
    }


}