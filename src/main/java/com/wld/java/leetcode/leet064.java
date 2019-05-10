package com.wld.java.leetcode;

public class leet064 {

    /*
                           min( grid(i-1,j) , grid(i,j-1) ) + grid(i,j)                 i>0 && j>0
                   /
      grid(i,j)=   |       grid(i,j-1)+grid(i,j)                                        i=0 && j>0
                   |
                   |       grid(i-1,j)+grid(i,j)                                        i>0 && j=0
                   \
                           grid(0,0)                                                    i=0 && j=0
     */

    public static void main(String args[]) {
        System.out.println(new leet064().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));

    }

//    public int minPathSum(int[][] grid) {
//        return minPathSum(grid, 0, 0);
//    }
//
//    public int minPathSum(int[][] grid, int i, int j) {
//        if (i == grid.length - 1 && j != grid[0].length - 1)
//            return grid[i][j] + minPathSum(grid, i, j + 1);
//        if (i != grid.length - 1 && j == grid[0].length - 1)
//            return grid[i][j] + minPathSum(grid, i + 1, j);
//        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
//        return grid[i][j] + Math.min(minPathSum(grid, i + 1, j), minPathSum(grid, i, j + 1));
//    }

//    public int minPathSum(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        int sum[][] = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            Arrays.fill(sum[i], grid[0][0]);
//        }
//        for (int i = 1; i < m; i++)
//            sum[i][0] = sum[i - 1][0] + grid[i][0];
//        for (int j = 1; j < n; j++)
//            sum[0][j] = sum[0][j - 1] + grid[0][j];
//        for (int i = 1; i < m; i++)
//            for (int j = 1; j < n; j++)
//                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
//        return sum[m - 1][n - 1];
//    }


//    public int minPathSum(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        int cur[] = new int[m];
//        Arrays.fill(cur, grid[0][0]);
//        for (int i = 1; i < m; i++)
//            cur[i] = cur[i - 1] + grid[i][0];
//        for (int j = 1; j < n; j++) {
//            cur[0] += grid[0][j];
//            for (int i = 1; i < m; i++)
//                cur[i] = Math.min(cur[i - 1], cur[i]) + grid[i][j];
//        }
//        return cur[m - 1];
//    }


//    public int minPathSum(int[][] grid) {
//        int m = grid.length, n = grid[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == 0 && j == 0)
//                    continue;
//                if (i == 0)
//                    grid[i][j] += grid[i][j - 1];
//                else if (j == 0)
//                    grid[i][j] += grid[i - 1][j];
//                else
//                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
//            }
//        }
//        return grid[m - 1][n - 1];
//    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) grid[i][j] += grid[i][j - 1];
                if (i != 0 && j == 0) grid[i][j] += grid[i - 1][j];
                if (i != 0 && j != 0) grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

}