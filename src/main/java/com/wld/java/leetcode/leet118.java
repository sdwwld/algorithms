package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet118 {

    public static void main(String args[]) {
        System.out.println();
        generate(5);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allrows = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            allrows.add(new ArrayList<>(row));
        }
        return allrows;
    }


//    public List<List<Integer>> generate(int numRows) {
//        List<List<Integer>> res = new ArrayList<>();
//        for (int i = 0; i < numRows; ++i) {
//            Integer array[] = new Integer[i + 1];
//            Arrays.fill(array, 1);
//            res.add(Arrays.asList(array));
//            for (int j = 1; j < i; ++j)
//                res.get(i).set(j, res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
//        }
//        return res;
//    }

}