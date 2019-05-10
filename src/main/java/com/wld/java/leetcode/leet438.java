package com.wld.java.leetcode;

public class leet438 {

    public static void main(String args[]) {
        System.out.println();
    }

//    public List<Integer> findAnagrams(String s, String p) {
//        List<Integer> list = new ArrayList<>();
//        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
//        int[] hash = new int[256];
//        for (char c : p.toCharArray()) {
//            hash[c]++;
//        }
//        int left = 0, right = 0, count = p.length();
//        while (right < s.length()) {
//            if (hash[s.charAt(right++)]-- >= 1) count--;
//            if (count == 0) list.add(left);
//            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
//        }
//        return list;
//    }


//    public List<Integer> findAnagrams(String s, String p) {
//        char[] ptrn = p.toCharArray();
//        char[] str = s.toCharArray();
//        int[] w = new int[26];
//        for (char c : ptrn) w[c - 'a']++;
//        int start = 0;
//        List<Integer> result = new LinkedList<>();
//        for (int i = 0; i < str.length; i++) {
//            int cIndex = str[i] - 'a';
//            w[cIndex]--;
//            while (w[cIndex] < 0) {
//                w[str[start] - 'a']++;
//                start++;
//            }
//            if (i - start + 1 == ptrn.length) {
//                result.add(start);
//                w[str[start] - 'a']++;
//                start++;
//            }
//        }
//        return result;
//    }


//    public List<Integer> findAnagrams(String s, String p) {
//        int[] chars = new int[26];
//        List<Integer> result = new ArrayList<>();
//
//        if (s == null || p == null || s.length() < p.length())
//            return result;
//        for (char c : p.toCharArray())
//            chars[c - 'a']++;
//
//        int start = 0, end = 0, count = p.length();
//        while (end < s.length()) {
//            if (end - start == p.length() && chars[s.charAt(start++) - 'a']++ >= 0)
//                count++;
//            if (--chars[s.charAt(end++) - 'a'] >= 0)
//                count--;
//            if (count == 0)
//                result.add(start);
//        }
//        return result;
//    }


//    public List<Integer> findAnagrams(String s, String p) {
//        List<Integer> res = new ArrayList<>();
//        if (p == null || s == null || s.length() < p.length()) return res;
//        int m = s.length(), n = p.length();
//        for (int i = 0; i < m - n + 1; i++) {
//            String cur = s.substring(i, i + n);
//            if (helper(cur, p)) res.add(i);
//        }
//        return res;
//    }
//
//    public boolean helper(String a, String b) {
//        if (a == null || b == null || a.length() != b.length()) return false;
//        int[] dict = new int[26];
//        for (int i = 0; i < a.length(); i++) {
//            char ch = a.charAt(i);
//            dict[ch - 'a']++;
//        }
//        for (int i = 0; i < b.length(); i++) {
//            char ch = b.charAt(i);
//            dict[ch - 'a']--;
//            if (dict[ch - 'a'] < 0) return false;
//        }
//        return true;
//    }


//    public List<Integer> findAnagrams(String s, String p) {
//        int n = s.length(), i = 0;
//        List<Integer> result = new LinkedList<>();
//        int[] map = new int[26], pmap = toHash(p);
//        for (int j = 0; j < n; j++) {
//            map[s.charAt(j) - 'a']++;
//            while (j - i >= p.length()) {
//                map[s.charAt(i) - 'a']--;
//                i++;
//            }
//            if (Arrays.equals(map, pmap)) {
//                result.add(i);
//            }
//        }
//        return result;
//    }
//
//    private int[] toHash(String s) {
//        int[] map = new int[26];
//        for (char ch : s.toCharArray())
//            map[ch - 'a']++;
//        return map;
//    }

}