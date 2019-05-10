package com.wld.java.leetcode;

public class leet008 {

    public static void main(String args[]) {
        System.out.println(new leet008().myAtoi(" "));

    }

//    int atoi(const char *str) {
//        int sign = 1, base = 0, i = 0;
//        while (str[i] == ' ') {
//            i++;
//        }
//        if (str[i] == '-' || str[i] == '+') {
//            sign = 1 - 2 * (str[i++] == '-');
//        }
//        while (str[i] >= '0' && str[i] <= '9') {
//            if (base > INT_MAX / 10 || (base == INT_MAX / 10 && str[i] - '0' > 7)) {
//                if (sign == 1) return INT_MAX;
//                else return INT_MIN;
//            }
//            base = 10 * base + (str[i++] - '0');
//        }
//        return base * sign;
//    }

//    public int myAtoi(String str) {
//        int sign = 1, base = 0, i = 0, INT_MAX = Integer.MAX_VALUE, INT_MIN = Integer.MIN_VALUE;
//        while (i < str.length() && str.charAt(i) == ' ') i++;
//        if (i >= str.length()) return 0;
//        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
//            if (str.charAt(i) == '-') sign = -1;
//            i++;
//        }
//        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
//            if (base > INT_MAX / 10 || (base == INT_MAX / 10 && str.charAt(i) - '0' > 7)) {
//                if (sign == -1) return INT_MIN;
//                else return INT_MAX;
//            }
//            base = 10 * base + (str.charAt(i++) - '0');
//        }
//        return base * sign;
//    }

    public int myAtoi(String str) {
        if (str.trim().isEmpty())
            return 0;
        str = str.trim();
        int i = 0, ans = 0, sign = 1, len = str.length();
        if (str.charAt(i) == '-' || str.charAt(i) == '+')
            sign = str.charAt(i++) == '+' ? 1 : -1;
        for (; i < len; ++i) {
            int tmp = str.charAt(i) - '0';
            if (tmp < 0 || tmp > 9)
                break;
            if (ans > Integer.MAX_VALUE / 10
                    || (ans == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < tmp))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            else
                ans = ans * 10 + tmp;
        }
        return sign * ans;
    }

}