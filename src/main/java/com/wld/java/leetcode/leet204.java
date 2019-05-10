package com.wld.java.leetcode;

public class leet204 {

    public static void main(String args[]) {
        System.out.println(new leet204().countPrimes(13));

    }

//    public int countPrimes(int n) {
//        boolean[] notPrime = new boolean[n];
//        int count = 0;
//        for (int i = 2; i < n; i++) {
//            if (notPrime[i] == false) {
//                count++;
//                for (int j = 2; i * j < n; j++) {
//                    notPrime[i * j] = true;
//                }
//            }
//        }
//        return count;
//    }

//    public int countPrimes(int n) {
//        if (n < 3)
//            return 0;
//        boolean[] f = new boolean[n];
//        int count = n / 2;
//        for (int i = 3; i * i < n; i += 2) {
//            if (f[i])
//                continue;
//            for (int j = i * i; j < n; j += 2 * i) {
//                if (!f[j]) {
//                    --count;
//                    f[j] = true;
//                }
//            }
//        }
//        return count;
//    }


    public int countPrimes(int n) {
        boolean[] m = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (m[i])
                continue;
            count++;
            for (int j = i; j < n; j = j + i)
                m[j] = true;
        }
        return count;
    }


//    public int countPrimes(int n) {
//        if (n <= 2)
//            return 0;
//        int c = 1;
//        boolean isNotPrime[] = new boolean[n + 1];
//        for (int i = 3; i * i <= n; i = i + 2) {
//            if (isNotPrime[i])
//                continue;
//            for (int j = i * i; j <= n; j = j + 2 * i) {
//                isNotPrime[j] = true;
//            }
//        }
//        for (int i = 3; i < n; i = i + 2) {
//            if (!isNotPrime[i]) {
//                c++;
//            }
//        }
//        return c;
//    }

}