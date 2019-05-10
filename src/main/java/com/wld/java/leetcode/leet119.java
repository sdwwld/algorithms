package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet119 {

    public static void main(String args[]) {
        System.out.println(getRow(10).toString());

    }

//    public List<Integer> getRow(int rowIndex) {
//        Integer array[] = new Integer[rowIndex + 1];
//        Arrays.fill(array, 0);
//        List<Integer> A = Arrays.asList(array);
//        A.set(0, 1);
//        for (int i = 1; i < rowIndex + 1; i++)
//            for (int j = i; j >= 1; j--)
//                A.set(j, A.get(j) + A.get(j - 1));
//        return A;
//    }

    /**
     * @param rowIndex
     * @return 当rowIndex为10的时候结果为
     * [1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1]
     * 其中[1, 10, 45, 120, 210, 252] 前后数的比值为
     * [1/10, 2/9, 3/8, 4/7, 5/6]可以推导公式为
     * (rowIndex - i) / (i + 1);
     */
    public static List<Integer> getRow(int rowIndex) {
        long nCk = 1;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add((int) nCk);
            nCk = nCk * (rowIndex - i) / (i + 1);
        }
        return result;
    }


}