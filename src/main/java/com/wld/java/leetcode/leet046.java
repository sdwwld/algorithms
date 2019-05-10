package com.wld.java.leetcode;

import com.wld.java.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class leet046 {

    public static void main(String args[]) {
        Util.printList(new leet046().permute(new int[]{1, 2, 3}));
    }

    //4 ms
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        backtrack(list, new ArrayList<>(), nums);
//        return list;
//    }
//
//    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
//        if (tempList.size() == nums.length) {
//            list.add(new ArrayList<>(tempList));
//        } else {
//            for (int i = 0; i < nums.length; i++) {
//                if (tempList.contains(nums[i])) continue;
//                tempList.add(nums[i]);
//                backtrack(list, tempList, nums);
//                tempList.remove(tempList.size() - 1);
//            }
//        }
//    }


    //64 ms
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteRecursive(nums, 0, result);
        return result;
    }

    public void permuteRecursive(int[] nums, int begin, List<List<Integer>> result) {
        if (begin == nums.length - 1) {
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            result.add(list);
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            swap(nums, begin, i);
            permuteRecursive(nums, begin + 1, result);
            swap(nums, begin, i);
        }
    }

    public void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        backtrack(str.toCharArray(), 0, list);
        return list;
    }

    public void backtrack(char[] str, int begin, ArrayList<String> result) {
        if (begin == str.length - 1) {
            result.add(new String(str));
            return;
        }
        for (int i = begin; i < str.length; i++) {
            if (i > begin && str[i] == str[i - 1]) continue;
            swap(str, begin, i);
            backtrack(str, begin + 1, result);
            swap(str, begin, i);
        }
    }

    public void swap(char[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }
}