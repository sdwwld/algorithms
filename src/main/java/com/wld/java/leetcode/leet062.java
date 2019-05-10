package com.wld.java.leetcode;

public class leet062 {

    public static void main(String args[]) {
        System.out.println(new leet062().uniquePaths(2, 12));
        System.out.println(new leet062().uniquePaths1(2, 12));

    }

//    public int uniquePaths(int m, int n) {
//        int[][] path = new int[m][n];
//        for (int i = 0; i < path.length; i++) {
//            Arrays.fill(path[i], 1);
//        }
//        for (int i = 1; i < m; i++)
//            for (int j = 1; j < n; j++)
//                path[i][j] = path[i - 1][j] + path[i][j - 1];
//        return path[m - 1][n - 1];
//    }


//    public int uniquePaths(int m, int n) {
//        if (m > n) return uniquePaths(n, m);
//        int[] cur = new int[m];
//        Arrays.fill(cur, 1);
//        for (int j = 1; j < n; j++)
//            for (int i = 1; i < m; i++)
//                cur[i] += cur[i - 1];
//        return cur[m - 1];
//    }


//    public int uniquePaths(int m, int n) {
//        int N = n + m - 2;
//        int k = m - 1;
//        double res = 1;
//        for (int i = 1; i <= k; i++)
//            res = res * (N - k + i) / i;
//        return (int) res;
//    }

//    public int uniquePaths(int m, int n) {
//        long result = 1;
//        for (int i = 0; i < Math.min(m - 1, n - 1); i++)
//            result = result * (m + n - 2 - i) / (i + 1);
//        return (int) result;
//    }


//    public int uniquePaths(int m, int n) {
//        double result = 1;
//        for (int i = 0; i < (m - 1); i++)
//            result = result * (n + i) / (m - i - 1);
//        return (int) (result + 0.5);
//    }

    public int uniquePaths1(int m, int n) {
        int smaller = m > n ? n - 1 : m - 1;
        int totalsteps = m + n - 2;
        long result = 1;
        for (int counter = 1; counter <= smaller; counter++) {
            result *= totalsteps--;
            result /= counter;
        }
        return (int) result;
    }

    //最容易理解，但最耗时
    public int uniquePaths(int m, int n) {
        return uniquePaths(1, 1, m, n);
    }

    public int uniquePaths(int i, int j, int m, int n) {
        if (i > m || j > n) return 0;
        if ((i == m && j == n)) return 1;
        int right = uniquePaths(i + 1, j, m, n);
        int down = uniquePaths(i, j + 1, m, n);
        return right + down;
    }

}