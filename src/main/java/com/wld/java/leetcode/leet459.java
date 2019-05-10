package com.wld.java.leetcode;

public class leet459 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean repeatedSubstringPattern(String s) {
//        if (s == null || s.length() == 0) return true;
//        int n = s.length();
//        for (int len = 1; len <= n / 2; len++) {
//            if (n % len != 0) continue;
//            String pattern = s.substring(0, len);
//            int i = len;
//            int j = i + len;
//            while (j <= s.length()) {
//                String substr = s.substring(i, j);
//                if (!pattern.equals(substr)) break;
//                i += len;
//                j += len;
//            }
//            if (j == s.length() + len) return true;
//        }
//        return false;
//    }

//    public boolean repeatedSubstringPattern(String str) {
//        String s = str + str;
//        return s.substring(1, s.length() - 1).contains(str);
//    }

//    public boolean repeatedSubstringPattern(String str) {
//        int l = str.length();
//        for (int i = l / 2; i >= 1; i--) {
//            if (l % i == 0) {
//                int m = l / i;
//                String subS = str.substring(0, i);
//                StringBuilder sb = new StringBuilder();
//                for (int j = 0; j < m; j++) {
//                    sb.append(subS);
//                }
//                if (sb.toString().equals(str)) return true;
//            }
//        }
//        return false;
//    }


//    public boolean repeatedSubstringPattern(String str) {
//        int i = 1, j = 0, n = str.length();
//        int dp[] = new int[n + 1];
//        while (i < str.length()) {
//            if (str.charAt(i) == str.charAt(j)) dp[++i] = ++j;
//            else if (j == 0) i++;
//            else j = dp[j];
//        }
//        return dp[n] != 0 && dp[n] % (n - dp[n]) == 0;
//    }


    //效率最高
//    public boolean repeatedSubstringPattern(String str) {
//        int[] prefix = kmp(str);
//        int len = prefix[str.length() - 1];
//        int n = str.length();
//        return (len > 0 && n % (n - len) == 0);
//    }
//
//    private int[] kmp(String s) {
//        int len = s.length();
//        int[] res = new int[len];
//        char[] ch = s.toCharArray();
//        int i = 0, j = 1;
//        res[0] = 0;
//        while (i < ch.length && j < ch.length) {
//            if (ch[j] == ch[i]) {
//                res[j] = i + 1;
//                i++;
//                j++;
//            } else {
//                if (i == 0) {
//                    res[j] = 0;
//                    j++;
//                } else {
//                    i = res[i - 1];
//                }
//            }
//        }
//        return res;
//    }


//    public boolean repeatedSubstringPattern(String str) {
//        int n = str.length();
//        for (int i = 1; i <= n / 2; i++)
//            if (n % i == 0 && str.startsWith(str.substring(i)))
//                return true;
//        return false;
//    }

    public boolean repeatedSubstringPattern(String str) {
        String nextStr;
        int len = str.length();
        if (len < 1) return false;
        for (int i = 1; i <= len / 2; i++) {
            if (len % i == 0) {
                nextStr = str.substring(i) + str.substring(0, i);
                if (nextStr.equals(str)) return true;
            }
        }
        return false;
    }

}