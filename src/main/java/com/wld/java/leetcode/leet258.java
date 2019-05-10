package com.wld.java.leetcode;

public class leet258 {

    public static void main(String args[]) {
        System.out.println();

    }

    /**
     * https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
     * ~input: 0 1 2 3 4 ...
     * output: 0 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 ....
     *
     * @param num
     * @return
     */
//    public int addDigits(int num) {
//        return 1 + (num - 1) % 9;
//    }
//    public int addDigits(int num) {
//        while (num >= 10) {
//            int temp = 0;
//            while (num > 0) {
//                temp += num % 10;
//                num /= 10;
//            }
//            num = temp;
//        }
//        return num;
//    }
//    public int addDigits(int num) {
//        int res = num % 9;
//        return (res != 0 || num == 0) ? res : 9;
//    }
    public int addDigits(int num) {
        return num == 0 ? 0 : (num % 9 == 0 ? 9 : (num % 9));
    }

//    public int addDigits(int n) {
//        return n > 9 ? addDigits((n % 10) + addDigits(n / 10)) : n;
//    }
}