package com.wld.java.leetcode;

public class leet059 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int[][] generateMatrix(int n) {
//        int nums[][] = new int[n][n];
//        int dr = 0, dc = 1, r = 0, c = 0;
//        for (int k = 1; k <= n * n; k++) {
//            nums[r][c] = k;
//            if (nums[(r + dr + n) % n][(c + dc + n) % n] != 0) {
//                int tempdc = dc;
//                dc = -dr;
//                dr = tempdc;
//            }
//            r += dr;
//            c += dc;
//        }
//        return nums;
//    }

//    public int[][] generateMatrix(int n) {
//        int nums[][] = new int[n][n];
//        int k = 1, i = 0;
//        while (k <= n * n) {
//            int j = i;
//            while (j < n - i)             // 1. horizonal, left to right
//                nums[i][j++] = k++;
//            j = i + 1;
//            while (j < n - i)             // 2. vertical, top to bottom
//                nums[j++][n - i - 1] = k++;
//            j = n - i - 2;
//            while (j > i)                  // 3. horizonal, right to left
//                nums[n - i - 1][j--] = k++;
//            j = n - i - 1;
//            while (j > i)                  // 4. vertical, bottom to  top
//                nums[j--][i] = k++;
//            i++;      // next loop
//        }
//        return nums;
//    }


//    public static int[][] generateMatrix(int n) {
//        int[][] res = new int[n][n];
//        int num = 1;
//        int level = (int) Math.ceil(n / 2.);
//        for (int i = 0; i < level; i++) {
//            // top left -> right, shown as #
//            for (int j = i; j < n - i; j++)
//                res[i][j] = num++;
//            // top right + 1 -> bot, shown as $
//            for (int j = i + 1; j < n - i; j++)
//                res[j][n - i - 1] = num++;
//            // bot right - 1 -> left, shown as &
//            for (int j = n - i - 2; j >= i; j--)
//                res[n - i - 1][j] = num++;
//            // bot left -1 -> top + 1, shown as %
//            for (int j = n - i - 2; j > i; j--)
//                res[j][i] = num++;
//        }
//        return res;
//    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        vortex(res, n, n, 0, -1, 0);
        return res;
    }

    int[][] step = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int cnt = 1;

    void vortex(int[][] res, int len, int wid, int x, int y, int sg) {
        if (len == 0) return;
        for (int i = 0; i < len; ++i) {
            x += step[sg][0];
            y += step[sg][1];
            res[x][y] = cnt++;
        }
        sg = (sg + 1) % 4;
        vortex(res, --wid, len, x, y, sg);
    }


}