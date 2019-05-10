package com.wld.java.leetcode;

public class leet036 {

    public static void main(String args[]) {
//        System.out.println('8'-'0');
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.println('i==' + i + ':j==' + j + ':' + (i / 3 * 3 + j / 3));
//            }
//        }

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char board[][]) {
        int line[][] = new int[9][9];
        int column[][] = new int[9][9];
        int cell[][] = new int[9][9];
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[i].length; ++j)
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1, k = i / 3 * 3 + j / 3;
                    if (line[i][num] != 0 || column[j][num] != 0 || cell[k][num] != 0)
                        return false;
                    line[i][num] = column[j][num] = cell[k][num] = 1;
                }
        return true;
    }

}