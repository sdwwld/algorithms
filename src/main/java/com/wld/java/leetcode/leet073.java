package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet073 {

    public static void main(String args[]) {
        int[][] matrix = {
                {0, 1, 2, 4},
                {3, 5, 0, 2},
                {1, 3, 1, 5}
        };
        Util.printTwoIntArrays(matrix);
        System.out.println();
        new leet073().setZeroes(matrix);
        Util.printTwoIntArrays(matrix);
    }

    public void setZeroes(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }
        Util.printTwoIntArrays(matrix);
        System.out.println();
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }


//    public void setZeroes(int[][] matrix) {
//        boolean row = false, col = false;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] == 0) {
//                    if (i == 0) row = true;
//                    if (j == 0) col = true;
//                    matrix[0][j] = matrix[i][0] = 0;
//                }
//            }
//        }
//        for (int i = 1; i < matrix.length; i++) {
//            for (int j = 1; j < matrix[0].length; j++) {
//                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
//            }
//        }
//        if (col) {
//            for (int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
//        }
//        if (row) {
//            for (int j = 0; j < matrix[0].length; j++) matrix[0][j] = 0;
//        }
//    }


//    public void setZeroes(int[][] matrix) {
//        int m = matrix.length, n = matrix[0].length, k = 0;
//        while (k < n && matrix[0][k] != 0) ++k;
//        for (int i = 1; i < m; ++i)
//            for (int j = 0; j < n; ++j)
//                if (matrix[i][j] == 0)
//                    matrix[0][j] = matrix[i][0] = 0;
//        for (int i = 1; i < m; ++i)
//            for (int j = n - 1; j >= 0; --j)
//                if (matrix[0][j] == 0 || matrix[i][0] == 0)
//                    matrix[i][j] = 0;
//        if (k < n) Arrays.fill(matrix[0], 0);
//    }


//    public void setZeroes(int[][] matrix) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//        int[] row = new int[m];
//        int[] col = new int[n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (matrix[i][j] == 0) {
//                    row[i] = 1;
//                    col[j] = 1;
//                }
//            }
//        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (row[i] == 1 || col[j] == 1) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//    }

}