package com.wld.java.leetcode;

public class leet125 {
    public static void main(String args[]) {
        System.out.print(new leet125().isPalindrome(",."));
    }

//    public boolean isPalindrome(String s) {
//        if (s.trim().length() == 0 || s.trim().length() == 1)
//            return true;
//        int start = 0, end = s.length() - 1;
//        while (start < end) {
//            while (start <= end && !Character.isLetterOrDigit(s.charAt(start)))
//                start++;
//            while (start <= end && !Character.isLetterOrDigit(s.charAt(end)))
//                end--;
//            if (start <= end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end)))
//                return false;
//            start++;
//            end--;
//        }
//        return true;
//    }

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }
        return true;
    }

}
