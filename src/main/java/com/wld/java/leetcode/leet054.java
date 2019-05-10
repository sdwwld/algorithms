package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leet054 {

    public static void main(String args[]) {
        System.out.println();

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return spiral;
        int m = matrix.length, n = matrix[0].length;
        int u = 0, d = m - 1, l = 0, r = n - 1, k = 0;
        while (true) {
            // up
            for (int col = l; col <= r; col++) spiral.add(k++, matrix[u][col]);
            if (++u > d) break;
            // right
            for (int row = u; row <= d; row++) spiral.add(k++, matrix[row][r]);
            if (--r < l) break;
            // down
            for (int col = r; col >= l; col--) spiral.add(k++, matrix[d][col]);
            if (--d < u) break;
            // left
            for (int row = d; row >= u; row--) spiral.add(k++, matrix[row][l]);
            if (++l > r) break;
        }
        return spiral;
    }


    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) return res;
        int n = matrix.length, m = matrix[0].length;
        int up = 0, down = n - 1;
        int left = 0, right = m - 1;
        while (res.size() < n * m) {
            for (int j = left; j <= right && res.size() < n * m; j++)
                res.add(matrix[up][j]);
            for (int i = up + 1; i <= down - 1 && res.size() < n * m; i++)
                res.add(matrix[i][right]);
            for (int j = right; j >= left && res.size() < n * m; j--)
                res.add(matrix[down][j]);
            for (int i = down - 1; i >= up + 1 && res.size() < n * m; i--)
                res.add(matrix[i][left]);
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}