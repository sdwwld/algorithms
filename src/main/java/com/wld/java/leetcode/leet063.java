package com.wld.java.leetcode;

public class leet063 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        int width = obstacleGrid[0].length;
//        int[] dp = new int[width];
//        dp[0] = 1;
//        for (int[] row : obstacleGrid) {
//            for (int j = 0; j < width; j++) {
//                if (row[j] == 1)
//                    dp[j] = 0;
//                else if (j > 0)
//                    dp[j] += dp[j - 1];
//            }
//        }
//        return dp[width - 1];
//    }


//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        int m = obstacleGrid.length;
//        int n = obstacleGrid[0].length;
//        int[] dp = new int[n + 1];
//        dp[1] = 1;
//        for (int i = 0; i < m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (obstacleGrid[i][j - 1] == 1) {
//                    dp[j] = 0;
//                } else {
//                    dp[j] += dp[j - 1];
//                }
//            }
//        }
//        return dp[n];
//    }


//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        int m = obstacleGrid.length;
//        int n = obstacleGrid[0].length;
//        int dp[][] = new int[m + 1][n + 1];
//        dp[0][1] = 1;
//        for (int i = 1; i <= m; ++i)
//            for (int j = 1; j <= n; ++j)
//                if (obstacleGrid[i - 1][j - 1] == 0)
//                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//        return dp[m][n];
//    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (obstacleGrid[r][c] == 1) obstacleGrid[r][c] = 0;
                else {
                    if (r == m - 1 && c == n - 1) obstacleGrid[r][c] = 1;
                    else if (r == m - 1) obstacleGrid[r][c] = obstacleGrid[r][c + 1];
                    else if (c == n - 1) obstacleGrid[r][c] = obstacleGrid[r + 1][c];
                    else obstacleGrid[r][c] = obstacleGrid[r][c + 1] + obstacleGrid[r + 1][c];
                }
            }
        }
        return obstacleGrid[0][0];
    }


}