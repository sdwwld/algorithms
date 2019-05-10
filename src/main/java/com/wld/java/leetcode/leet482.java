package com.wld.java.leetcode;

public class leet482 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public String licenseKeyFormatting(String s, int k) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = s.length() - 1; i >= 0; i--)
//            if (s.charAt(i) != '-')
//                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
//        return sb.reverse().toString().toUpperCase();
//    }


//    public String licenseKeyFormatting(String s, int k) {
//        StringBuilder sb = new StringBuilder();
//        int n = s.length();
//        int i = n - 1;
//        int p = k;
//        while (i >= 0) {
//            if (s.charAt(i) == '-') {
//                i--;
//                continue;
//            }
//            if (p == 0) {
//                sb.append("-");
//                p = k;
//            } else {
//                sb.append(s.charAt(i));
//                i--;
//                p--;
//            }
//        }
//        return sb.reverse().toString().toUpperCase();
//    }


//    public String licenseKeyFormatting(String S, int K) {
//        String S1 = S.replace("-", "");
//        S1 = S1.toUpperCase();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < S1.length(); i++) {
//            sb.append(S1.charAt(i));
//        }
//        int len = sb.toString().length();
//        for (int i = K; i < len; i = i + K) {
//            sb.insert(len - i, '-');
//        }
//        return sb.toString();
//    }

//    public String licenseKeyFormatting(String S, int K) {
//        S = S.replaceAll("[-]", "");
//        S = S.toUpperCase();
//        StringBuilder sb = new StringBuilder();
//        sb.append(S);
//
//        int i=sb.length()-K;
//        while(i>0) {
//            sb.insert(i, '-');
//            i = i-K;
//        }
//        return sb.toString();
//    }

    public String licenseKeyFormatting(String S, int K) {
        String[] strs = S.split("-");
        StringBuilder rst = new StringBuilder();
        int gap = 'A' - 'a';
        for (String str : strs) {
            rst.append(str);
        }
        int len = rst.length();
        for (int i = 0; i < len; i++) {
            char c = rst.charAt(i);
            if (c >= 'a' && c <= 'z') {
                rst.setCharAt(i, (char) (c + gap));
            }
        }
        for (int i = len - K; i > 0; i -= K) {
            rst.insert(i, '-');
        }
        return rst.toString();
    }

}