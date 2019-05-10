package com.wld.java.leetcode;

public class leet463 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int islandPerimeter(int[][] grid) {
//        int islands = 0, neighbours = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                if (grid[i][j] == 1) {
//                    islands++;
//                    if (i < grid.length - 1 && grid[i + 1][j] == 1)
//                        neighbours++;
//                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1)
//                        neighbours++;
//                }
//            }
//        }
//        return islands * 4 - neighbours * 2;
//    }

//    public int islandPerimeter(int[][] g) {
//        final int[][]dir={{1,0},{-1,0},{0,1},{0,-1}};
//        int m=g.length;
//        int res=0;
//        if(m==0) return res;
//        int n=g[0].length;
//        for(int i=0;i<m;++i){
//            for(int j=0;j<n;++j){
//                if(g[i][j]==1){
//                    for(int[]d:dir){
//                        int h=d[0]+i;
//                        int k=d[1]+j;
//                        if((h<0||h>=m)||(k<0||k>=n)||(g[h][k]==0)){
//                            res++;
//                        }
//                    }
//                }
//            }
//        }
//        return res;
//    }


//    public int islandPerimeter(int[][] grid) {
//        int m = grid.length;
//        if (m == 0) return 0;
//        int n = grid[0].length;
//        int per = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1) {
//                    if (i == 0 || grid[i - 1][j] == 0) per++;
//                    if (i == m - 1 || grid[i + 1][j] == 0) per++;
//                    if (j == 0 || grid[i][j - 1] == 0) per++;
//                    if (j == n - 1 || grid[i][j + 1] == 0) per++;
//                }
//            }
//        }
//        return per;
//    }

//    public static int islandPerimeter(int[][] grid) {
//        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
//        int result = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 1) {
//                    result += 4;
//                    if (i > 0 && grid[i-1][j] == 1) result -= 2;
//                    if (j > 0 && grid[i][j-1] == 1) result -= 2;
//                }
//            }
//        }
//        return result;
//    }

//    public int islandPerimeter(int[][] grid) {
//        int[][] d = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
//        int perimeter = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 0)
//                    continue;
//                for (int k = 0; k < d.length; k++) {
//                    int x = i + d[k][0], y = j + d[k][1];
//                    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
//                        perimeter++;
//                }
//            }
//        }
//        return perimeter;
//    }

//    public int islandPerimeter(int[][] grid) {
//        int n = grid.length;
//        int m = grid[0].length;
//        int sum = 0, i, j;
//        for (i = 0; i < n; i++) {
//            for (j = 0; j < m - 1; j++) {
//                sum += Math.abs(grid[i][j + 1] - grid[i][j]);
//            }
//            sum = sum + grid[i][j] + grid[i][0];
//        }
//        for (j = 0; j < m; j++) {
//            for (i = 0; i < n - 1; i++) {
//                sum += Math.abs(grid[i + 1][j] - grid[i][j]);
//            }
//            sum = sum + grid[i][j] + grid[0][j];
//        }
//        return sum;
//    }


    public int islandPerimeter(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int countV = 0;
        int countH = 0;
        for (int i = 0; i <= rowLen; ++i) {
            for (int j = 0; j <= colLen; ++j) {
                int left = (j > 0 && i < rowLen) ? grid[i][j - 1] : 0;
                int top = (i > 0 && j < colLen) ? grid[i - 1][j] : 0;
                int cur = (i < rowLen && j < colLen) ? grid[i][j] : 0;
                if (left != cur) countV++;
                if (top != cur) countH++;
            }
        }
        return countV + countH;
    }

}