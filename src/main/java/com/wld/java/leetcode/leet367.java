package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet367 {

    public static void main(String args[]) {
        System.out.println(Util.bitLong64(Long.MAX_VALUE));
        System.out.println(new leet367().isPerfectSquare(16));

    }

//    //A square number is 1+3+5+7+...
//    public boolean isPerfectSquare(int num) {
//        int i = 1;
//        while (num > 0) {
//            num -= i;
//            i += 2;
//        }
//        return num == 0;
//    }


//    public boolean isPerfectSquare(int num) {
//        int low = 1, high = num;
//        while (low <= high) {
//            long mid = (low + high) >>> 1;
//            long sqrt = mid * mid;
//            if (sqrt == num) {
//                return true;
//            } else if (sqrt < num) {
//                low = (int) mid + 1;
//            } else {
//                high = (int) mid - 1;
//            }
//        }
//        return false;
//    }

//    public boolean isPerfectSquare(int num) {
//        int left = 1, right = num;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            double res = num / 1.0 / mid;
//            if (res == mid)
//                return true;
//            else if (res > mid)
//                left = mid + 1;
//            else
//                right = mid;
//        }
//        return left * left == num;
//    }


    //69. x 的平方根  https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
//    public boolean isPerfectSquare(int num) {
//        long x = num;
//        while (x * x > num) {
//            x = (x + num / x) >> 1;
//        }
//        return x * x == num;
//    }


//    public boolean isPerfectSquare(int num) {
//        if (num == Integer.MAX_VALUE)
//            return false;
//        for (int i = 2; num > 1 && i * i <= num; ) {
//            if (num % i == 0) {
//                num /= i;
//                if (num % i == 0) {
//                    num /= i;
//                    i = 2;
//                } else {
//                    return false;
//                }
//            } else {
//                i++;
//            }
//        }
//        return num == 1;
//    }


    public boolean isPerfectSquare(int num) {
        int root = 0, bit = 1 << 15;
        while (bit > 0) {
            root |= bit;
            if (root > num / root) {    // if root * root > num
                root ^= bit;    // set the bit back to 0
            }
            bit >>= 1;
        }
        return root * root == num;
    }


}