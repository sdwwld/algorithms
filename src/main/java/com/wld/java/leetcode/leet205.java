package com.wld.java.leetcode;

public class leet205 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean isIsomorphic(String s, String t) {
//        int m1[] = new int[256], m2[] = new int[256], n = s.length();
//        for (int i = 0; i < n; ++i) {
//            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
//            m1[s.charAt(i)] = i + 1;
//            m2[t.charAt(i)] = i + 1;
//        }
//        return true;
//    }

//    public boolean isIsomorphic(String s1, String s2) {
//        Map<Character, Integer> m1 = new HashMap<>();
//        Map<Character, Integer> m2 = new HashMap<>();
//        //wld把Integer换成int结果会错。
//        for (Integer i = 0; i < s1.length(); i++) {
//            if (m1.put(s1.charAt(i), i) != m2.put(s2.charAt(i), i)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean isIsomorphic(String sString, String tString) {
//        char[] s = sString.toCharArray();
//        char[] t = tString.toCharArray();
//        int length = s.length;
//        if (length != t.length) return false;
//        char[] sm = new char[256];
//        char[] tm = new char[256];
//        for (int i = 0; i < length; i++) {
//            char sc = s[i];
//            char tc = t[i];
//            if (sm[sc] == 0 && tm[tc] == 0) {
//                sm[sc] = tc;
//                tm[tc] = sc;
//            } else {
//                if (sm[sc] != tc || tm[tc] != sc) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

}