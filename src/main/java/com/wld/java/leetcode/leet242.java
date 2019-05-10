package com.wld.java.leetcode;

public class leet242 {
    //49
    public static void main(String args[]) {
        System.out.println(new leet242().isAnagram("sssa", "aass"));

    }

//    public boolean isAnagram(String s, String t) {
//        int[] alphabet = new int[26];
//        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;//一个加一个减
//        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
//        for (int i : alphabet) if (i != 0) return false;
//        return true;
//    }

//    public boolean isAnagram(String s, String t) {
//        char[] sChar = s.toCharArray();
//        char[] tChar = t.toCharArray();
//        Arrays.sort(sChar);
//        Arrays.sort(tChar);
//        return Arrays.equals(sChar, tChar);
//    }


//    public boolean isAnagram(String s, String t) {
//        if (s == null || t == null || s.length() != t.length()) return false;
//        int[] count = new int[26];
//        int len = t.length();
//        for (int i = 0; i < len; i++) {
//            count[t.charAt(i) - 'a']++;
//        }
//        for (int i = 0; i < len; i++) {
//            char c = s.charAt(i);
//            if (count[c - 'a'] > 0) {
//                count[c - 'a']--;
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }


//    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length())
//            return false;
//        int[] count = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            count[s.charAt(i) - 'a']++;
//            count[t.charAt(i) - 'a']--;
//        }
//        for (int x : count) {
//            if (x != 0) return false;
//        }
//        return true;
//    }


    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] map = new int[127];
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (++map[cs[i]] == 1) count++;
            if (--map[ct[i]] == 0) count--;
        }
        return count == 0;
    }


}