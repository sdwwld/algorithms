package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet190 {

    public static void main(String args[]) {
        System.out.println(new leet190().reverseBits(0b00000010100101000001111010011100));
        System.out.println(Util.bitInt32(1 << 31));
        System.out.println(Util.bitInt32(1 << 32));
        System.out.println(Util.bitInt32(1 << 33));
        System.out.println(Util.bitInt32(1 << 63));
        System.out.println(Util.bitInt32(1 << 64));
        System.out.println(Util.bitInt32(1 << 65));
    }

//    public int reverseBits(int n) {
//        n = (n >>> 16) | (n << 16);
//        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
//        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
//        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
//        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
//        return n;
//    }

//    public int reverseBits(int n) {
//        int result = 0;
//        for (int i = 0; i < 32; i++) {
//            result += n & 1;
//            n >>>= 1;
//            if (i < 31)
//                result <<= 1;
//        }
//        return result;
//    }


//    public int reverseBits(int n) {
//        int result = 0;
//        for (int i = 0; i < 16; i++) {
//            result |= (n & (1 << i)) << (31 - i * 2);
//        }
//        for (int i = 16; i < 32; i++) {
//            result |= (n & (1 << i)) >>> (i * 2 - 31);
//        }
//        return result;
//    }

//    public int reverseBits(int n) {
//        if (n == 0) return 0;
//        int result = 0;
//        for (int i = 0; i < 32; i++) {
//            result <<= 1;
//            result += (n & 1);
//            n >>= 1;
//        }
//        return result;
//    }


//    public int reverseBits(int n) {
//        if (n == 0) return 0;
//        int result = 0;
//        for (int i = 0; i < 32; i++) {
//            result <<= 1;
//            if ((n & (1 << i)) != 0) result++;
//        }
//        return result;
//    }

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++)
            result = (result << 1) + (n >> i & 1);
        return result;
    }
}