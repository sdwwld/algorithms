package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet039 {
    public static void main(String args[]) {
        int[] aa = {2, 3, 6, 7,1};
        System.out.println(combinationSum(aa, 6));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    private static int count = 0;

    private static void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start) {
        if (target > 0) {
            for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
                cur.add(candidates[i]);
//                System.out.println("第" + ++count + "次调用：start==" + start + cur);
                getResult(result, cur, candidates, target - candidates[i], i);
//                System.out.println("第" + count-- + "次回退：start==" + start + cur);
                cur.remove(cur.size() - 1);
            }
        } else if (target == 0) {
            result.add(new ArrayList<Integer>(cur));
        }
    }

    private static void getResultTest(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start) {
        if (target > 0) {
            int i = start;
            cur.add(candidates[i]);
            System.out.println("第" + ++count + "次调用：start==" + start + cur);
            getResultTest(result, cur, candidates, target - candidates[i], i);
            System.out.println("第" + count-- + "次回退：start==" + start + cur);
            cur.remove(cur.size() - 1);
        } else if (target == 0) {
            result.add(new ArrayList<Integer>(cur));
        }
    }
}