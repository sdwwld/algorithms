package com.wld.java.leetcode;

public class leet479 {

    public static void main(String args[]) {

    }

//    public int largestPalindrome(int n) {
//        long max = (long) Math.pow(10, n) - 1;
//        long min = max / 10;
//        for (long h = max; h > min; h--) {
//            long left = h, right = 0;
//            for (long i = h; i != 0; right = right * 10 + i % 10, i /= 10, left *= 10) ;
//            long palindrom = left + right;
//            for (long i = max; i > min; i--) {
//                long j = palindrom / i;
//                if (j > i)
//                    break;
//                if (palindrom % i == 0)
//                    return (int) (palindrom % 1337);
//            }
//        }
//        return 9;
//    }


//    public int largestPalindrome(int n) {
//        int[] x = {9, 99, 993, 9999, 99979, 999999, 9998017, 99999999};
//        int[] y = {1, 91, 913, 9901, 99681, 999001, 9997647, 99990001};
//        return ((x[n - 1] % 1337) * (y[n - 1] % 1337)) % 1337;
//    }


    /**
     * Seems that if n is an even number, you can guess out the largest palindromic number.
     *
     * n=2:
     * 101*99=99,99; take out the middle 99, which is 990/99=10, then it is (101-10)*99=91*99=9009
     *
     * n=4:
     * 10001*9999=9999,9999, take out the middle 9999, which is 999900/9999=100, then it is (10001-100)*9999=9901*9999=99000099
     *
     * and so on.
     *
     */


}