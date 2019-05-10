package com.wld.java.leetcode;

import java.util.Arrays;

public class leet003 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int lengthOfLongestSubstring(String s) {
//        if (s.length() == 0) return 0;
//        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
//        int max = 0;
//        for (int i = 0, j = 0; i < s.length(); ++i) {
//            if (map.containsKey(s.charAt(i))) {
//                j = Math.max(j, map.get(s.charAt(i)) + 1);
//            }
//            map.put(s.charAt(i), i);
//            max = Math.max(max, i - j + 1);
//        }
//        return max;
//    }

//    int lengthOfLongestSubstring(String s) {
//        int[] dict = new int[256];
//        Arrays.fill(dict, -1);
//        int maxLen = 0, start = -1;
//        for (int i = 0; i != s.length(); i++) {
//            if (dict[s.charAt(i)] > start)
//                start = dict[s.charAt(i)];
//            dict[s.charAt(i)] = i;
//            maxLen = Math.max(maxLen, i - start);
//        }
//        return maxLen;
//    }


    int lengthOfLongestSubstring(String s) {
        int[] dict = new int[256];
        Arrays.fill(dict, -1);
        int longest = 0, m = 0;
        for (int i = 0; i < s.length(); i++) {
            m = Math.max(dict[s.charAt(i)] + 1, m);
            dict[s.charAt(i)] = i;
            longest = Math.max(longest, i - m + 1);
        }
        return longest;
    }

}