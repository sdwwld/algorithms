package com.wld.java.leetcode;

public class leet074 {

    public static void main(String args[]) {
//        System.out.println(3 + ((6 - 2) >> 1));
//        System.out.println(new leet074().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 3));
        System.out.println(new leet074().searchMatrix(new int[][]{{1}}, 0));
    }

//    public boolean searchMatrix(int[][] matrix, int target) {//二分法
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
//        int i = matrix.length, j = matrix[0].length;
//        int low = 0, high = i - 1;
//        while (low <= high) {
//            int leftMidIndex = low + ((high - low) >> 1);
//            if (target == matrix[leftMidIndex][0])
//                return true;
//            else if (target > matrix[leftMidIndex][0])
//                low = leftMidIndex + 1;
//            else if (target < matrix[leftMidIndex][0])
//                high = leftMidIndex - 1;
//        }
//        int row = --low;
//        if (row < 0)
//            row = 0;
//        low = 0;
//        high = j - 1;
//        while (low <= high) {
//            int rowMid = low + ((high - low) >> 1);
//            if (target == matrix[row][rowMid])
//                return true;
//            else if (target > matrix[row][rowMid])
//                low = rowMid + 1;
//            else if (target < matrix[row][rowMid])
//                high = rowMid - 1;
//        }
//        return false;
//    }

    /**
     * n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
     * <p>
     * an array convert to n * m matrix => a[x] =>matrix[x / m][x % m];
     *
     * @param matrix
     * @param target
     * @return
     */
//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
//        int n = matrix.length;
//        int m = matrix[0].length;
//        int l = 0, r = m * n - 1;
//        while (l != r) {
//            int mid = (l + r - 1) >> 1;
//            if (matrix[mid / m][mid % m] < target)
//                l = mid + 1;
//            else
//                r = mid;
//        }
//        return matrix[r / m][r % m] == target;
//    }
//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
//        int i = 0, j = matrix[0].length - 1;
//        while (i < matrix.length && j >= 0) {
//            if (matrix[i][j] == target) {
//                return true;
//            } else if (matrix[i][j] > target) {
//                j--;
//            } else {
//                i++;
//            }
//        }
//        return false;
//    }
//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
//        int n = matrix.length;
//        int m = matrix[0].length;
//        --n;
//        --m;
//        while (n > 0 && matrix[n - 1][m] >= target) --n;
//        while (m > 0 && matrix[n][m - 1] >= target) --m;
//        return (matrix[n][m] == target);
//    }
//    public boolean searchMatrix(int[][] matrix, int target) {
//        for (int[] row : matrix) {
//            for (int item : row)
//                if (item == target) return true;
//        }
//        return false;
//    }
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (r >= l) {
            int mid = l + (r - l) / 2;
            if (matrix[mid / m][mid % m] == target) return true;
            if (matrix[mid / m][mid % m] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return false;
    }

}