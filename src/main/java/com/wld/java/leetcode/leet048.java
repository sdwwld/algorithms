package com.wld.java.leetcode;

import com.wld.java.utils.Util;

public class leet048 {

    public static void main(String args[]) {
        System.out.println();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);

    }

    /*
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */
    private static void rotate(int[][] matrix) {
        Util.printTwoIntArrays(matrix);
        System.out.println();
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            int temp[] = matrix[i];
            matrix[i] = matrix[length - i - 1];
            matrix[length - i - 1] = temp;
        }
        Util.printTwoIntArrays(matrix);
        System.out.println();
        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < matrix[i].length; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        Util.printTwoIntArrays(matrix);
    }

}