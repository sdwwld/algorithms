package com.wld.java.leetcode;

public class leet400 {

    public static void main(String args[]) {
        System.out.println();

    }

    /**
     * find the length of the number where the nth digit is from
     * find the actual number where the nth digit is from
     * find the nth digit and return
     *
     * @param n
     * @return
     */
//    public int findNthDigit(int n) {
//        int len = 1;
//        long count = 9;
//        int start = 1;
//        while (n > len * count) {
//            n -= len * count;
//            len += 1;
//            count *= 10;
//            start *= 10;
//        }
//        start += (n - 1) / len;
//        String s = Integer.toString(start);
//        return Character.getNumericValue(s.charAt((n - 1) % len));
//    }

//    public int findNthDigit(int n) {
//        int len = 1;
//        long count = 9;
//        int start = 1;
//        while (n > len * count) {
//            n -= len * count;
//            len++;
//            count *= 10;
//            start *= 10;
//        }
//        start += (n - 1) / len;
//        int offset = n % len;
//        offset = offset > 0 ? (len - offset) : offset;
//        while (offset-- > 0) {
//            start /= 10;
//        }
//        return start % 10;
//    }


//    private int find(int n, int p) {
//        if (n > 9 * p * Math.pow(10, p - 1)) {
//            // Step 1
//            return find((int) (n - 9 * p * Math.pow(10, p - 1)), p + 1);
//        } else {
//            // Step 2
//            int x = (int) ((n - 1) / p + Math.pow(10, p - 1));
//            int k = (n - 1) % p;
//            // Step 3
//            return (x / (int) Math.pow(10, p - k - 1)) % 10;
//        }
//    }
//
//    public int findNthDigit(int n) {
//        return find(n, 1);
//    }



    public int findNthDigit(int n) {
        if (n <= 9)
            return n;
        int base = 1;
        while (n > 9 * Math.pow(10, base - 1) * base) {
            n = n - 9 * (int) Math.pow(10, base - 1) * base;
            base++;
        }
        int number = (int) Math.pow(10, base - 1) + (n - 1) / base;
        int index = (n - 1) % base;
        return Integer.toString(number).charAt(index) - '0';
    }


}