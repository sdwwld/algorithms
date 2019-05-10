package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet476 {

    public static void main(String args[]) {
        System.out.println(Util.bitInt32(~0));

    }

//    public int findComplement(int num) {
//        int result = 0;
//        int count = 31;
//        while ((num & (1 << count--)) == 0) ;
//        for (int i = count + 1; i >= 0; i--) {
//            if ((num & (1 << i)) == 0)
//                result |= (1 << i);
//        }
//        return result;
//    }


//    public int findComplement(int num) {
//        int mask = ~0;
//        while ((num & mask) != 0) mask <<= 1;
//        return ~mask & ~num;
//    }


//    public int findComplement(int num) {
//        int mask = ~0;
//        int bit = 0;
//        while ((mask & num) != 0) mask &= ~(1 << bit++);
//        return ~(mask ^ num);
//    }


//    public int findComplement(int num) {
//        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
//    }

//    public int findComplement(int num) {
//        int i = 0;
//        int j = 0;
//        while (i < num) {
//            i += Math.pow(2, j);
//            j++;
//        }
//        return i - num;
//    }


//    public int findComplement(int num) {
//        int n = 0;
//        while (n < num) {
//            n = (n << 1) | 1;
//        }
//        return n - num;
//    }

//    public int findComplement(int num) {
//        int mask = num;
//        mask |= mask >> 1;
//        mask |= mask >> 2;
//        mask |= mask >> 4;
//        mask |= mask >> 8;
//        mask |= mask >> 16;
//        return num ^ mask;
//    }

//    public int findComplement(long num) {
//        long i;
//        for (i = 1; i <= num; i *= 2) num ^= i;
//        return (int) num;
//    }


//    public int findComplement(int num) {
//        int id = 31, mask = 1<<id;
//        while ((num & mask)==0) mask = 1<<--id;
//        mask = (mask<<1) - 1;
//        return (~num) & mask;
//    }


//    public int findComplement(int num) {
//        int x = 1, i = 1;
//        while (x <= num && i < 32) {
//            num = num ^ x;
//            x = x << 1;
//            i++;
//        }
//        return num;
//    }


//    public int findComplement(int num) {
//        int sum = 1, pow = 1;
//        while (sum < num) {
//            pow *= 2;
//            sum += pow;
//        }
//        return sum - num;
//    }


}