package com.wld.java.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class leet409 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int longestPalindrome(String s) {
//        if (s == null || s.length() == 0)
//            return 0;
//        HashSet<Character> hs = new HashSet<>();
//        int count = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (hs.contains(s.charAt(i))) {
//                hs.remove(s.charAt(i));
//                count++;
//            } else {
//                hs.add(s.charAt(i));
//            }
//        }
//        if (!hs.isEmpty())
//            return count * 2 + 1;
//        return count * 2;
//    }

//    public int longestPalindrome(String s) {
//        if (s == null || s.length() == 0) return 0;
//        Set<Character> set = new HashSet<>();
//        for (char c : s.toCharArray()) {
//            if (set.contains(c))
//                set.remove(c);
//            else
//                set.add(c);
//        }
//        return set.size() == 0 ? s.length() : s.length() - set.size() + 1;
//    }


//    public int longestPalindrome(String s) {
//        if (s == null)
//            return 0;
//        Map<Character, Integer> charToCount = new HashMap();
//        for (char ch : s.toCharArray()) {
//            charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
//        }
//        int result = 0;
//        for (int count : charToCount.values()) {
//            result += count % 2 == 0 ? count : count - 1;
//        }
//        return result == s.length() ? result : result + 1;
//    }


//    public int longestPalindrome(String s) {
//        if (s == null || s.length() == 0)
//            return 0;
//        Map<Character, Integer> charToCount = new HashMap();
//        for (char ch : s.toCharArray()) {
//            charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
//        }
//        int result = 0;
//        for (char key : charToCount.keySet()) {
//            if (charToCount.get(key) % 2 == 0)
//                result += charToCount.get(key);
//            else
//                result += (charToCount.get(key) / 2) * 2;
//        }
//        return result == s.length() ? result : result + 1;
//
//    }

//    public int longestPalindrome(String s) {
//        Set<Character> set = new HashSet<>();
//        int len = 0;
//        for (char c : s.toCharArray()) {
//            if (set.remove(c))
//                len += 2;
//            else
//                set.add(c);
//        }
//        return set.size() > 0 ? len + 1 : len;
//    }

//    public int longestPalindrome(String s) {
//        HashSet<Character> set = new HashSet<Character>();
//        for (int i = 0; i < s.length(); i++) {
//            if (set.contains(s.charAt(i))) {
//                set.remove(s.charAt(i));
//            } else {
//                set.add(s.charAt(i));
//            }
//        }
//        if (set.size() < 2) {
//            return s.length();
//        } else {
//            return s.length() - set.size() + 1;
//        }
//    }

//    public int longestPalindrome(String s) {
//        int[] freq = new int[256];
//        int count = 0;
//        for (char ch : s.toCharArray()) {
//            freq[ch - 'A']++;
//            if (freq[ch - 'A'] == 2) {
//                count += 2;
//                freq[ch - 'A'] = 0;
//            }
//        }
//        if (count < s.length())
//            count += 1;
//        return count;
//    }


    public int longestPalindrome(String s) {
        boolean[] set = new boolean[256];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += set[s.charAt(i)] ? 2 : 0;
            set[s.charAt(i)] = !set[s.charAt(i)];
        }
        if (count < s.length()) count++;
        return count;
    }

}