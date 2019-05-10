package com.wld.java.leetcode;

public class leet434 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int countSegments(String s) {
//        int res = 0;
//        for (int i = 0; i < s.length(); i++)
//            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' '))
//                res++;
//        return res;
//    }


//    public int countSegments(String s) {
//        if (s.length() == 0) return 0;
//        int count = 0;
//        String[] str = s.split(" ");
//        for (int i = 0; i < str.length; i++) {
//            if (!str[i].matches("")) {
//                count++;
//            }
//        }
//        return count;
//    }

//    public int countSegments(String s) {
//        if (s == null || s.length() == 0) return 0;
//        int count = 0;
//        for (int i = 1; i < s.length(); i++) {
//            if (s.charAt(i) == ' ' && s.charAt(i - 1) != ' ') count++;
//        }
//        if (s.charAt(s.length() - 1) != ' ') count++;
//        return count;
//    }


//    public int countSegments(String s) {
//        int cnt = 0;
//        s = ' ' + s;
//        for (int i = 1; i < s.length(); i++) {
//            if (s.charAt(i) != ' ' && s.charAt(i - 1) == ' ')
//                cnt++;
//        }
//        return cnt;
//    }

//    public int countSegments(String s) {
//        s = s.trim();
//        if (s.length() == 0) {
//            return 0;
//        }
//        return s.split("\\s+").length;
//    }


    public int countSegments(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++)
            res += (s.charAt(i) != ' ' && (i + 1 == s.length() || s.charAt(i + 1) == ' ')) ? 1 : 0;
        return res;
    }


}