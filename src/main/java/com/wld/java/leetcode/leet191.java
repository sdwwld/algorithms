package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet191 {
    public static void main(String args[]) {
        System.out.println(13 & 14 >> 1);
        System.out.println(13 & 14);
        System.out.println(Util.bitInt32(0xaaaaaaaa));
        System.out.println(Util.bitInt32(0x55555555));
        System.out.println(Util.bitInt32(0xcccccccc));
        System.out.println(Util.bitInt32(0x33333333));
    }

//    public int hammingWeight(int n) {
//        int total = 0;
//        while (n != 0) {
//            n = n & (n - 1);
//            total++;
//        }
//        return total;
//    }

//     public int hammingWeight(int n) {
//         int total=0;
//         while(n!=0){
//             total+=n&1;
//             n=n>>>1;
//         }
//         return total;
//     }

//     public int hammingWeight(int n) {
//         n = ((n & 0xaaaaaaaa) >>> 1) + (n & 0x55555555);
//         n = ((n & 0xcccccccc) >>> 2) + (n & 0x33333333);
//         n = (((n & 0xf0f0f0f0) >>> 4) + (n & 0x0f0f0f0f)) & 0x0f0f0f0f;
//         n = (((n & 0xff00ff00) >>> 8) + (n & 0x00ff00ff)) & 0x00ff00ff;
//         n = n + (n >>> 16);
//         return n & 63;
//     }

    //     public static int hammingWeight(int i) {
    //     i = i - ((i >>> 1) & 0x55555555);
    //     i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    //     i = (i + (i >>> 4)) & 0x0f0f0f0f;
    //     i = i + (i >>> 8);
    //     i = i + (i >>> 16);
    //     return i & 0x3f;
    // }

    public int hammingWeight(int n) {
        return n == 0 ? 0 : 1 + hammingWeight(n & (n - 1));
    }

}
