package com.wld.java.leetcode;

public class leet009 {
    public static void main(String args[]) {
        System.out.println(new leet009().isPalindrome(123454321));
    }

    public boolean isPalindrome1(int x) {
        String digit = x + "";
        for (int i = 0, length = digit.length(); i < length / 2; i++) {
            if (digit.charAt(i) != digit.charAt(length - i - 1))
                return false;
        }
        return true;
    }

//    public  boolean isPalindrome(int x) {
//        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
//        int rev = 0;
//        while (x > rev) {
//            rev = rev * 10 + x % 10;
//            x = x / 10;
//        }
//        return (x == rev || x == rev / 10);
//    }

//    public static boolean isPalindrome(int x) {
//        if (x < 0)
//            return false;
//        int rev = 0, xx = x;
//        while (x != 0) {
//            rev = rev * 10 + x % 10;
//            x /= 10;
//        }
//        return (xx == rev);
//    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int revhalf = 0, slow = x, fast = x;
        while (fast != 0) {
            revhalf = revhalf * 10 + slow % 10;
            slow /= 10;
            fast /= 100;
        }
        return slow == revhalf || slow == revhalf / 10;
    }
}
