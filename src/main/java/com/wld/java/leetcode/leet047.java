package com.wld.java.leetcode;

import com.wld.java.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class leet047 {

    public static void main(String args[]) {
        Util.printList(new leet047().permuteUnique(new int[]{1, 1, 2}));
    }

//    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        if (nums == null || nums.length == 0) return res;
//        boolean[] used = new boolean[nums.length];
//        List<Integer> list = new ArrayList<Integer>();
//        Arrays.sort(nums);
//        dfs(nums, used, list, res);
//        return res;
//    }
//
//    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
//        if (list.size() == nums.length) {
//            res.add(new ArrayList<Integer>(list));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) continue;
//            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
//            used[i] = true;
//            list.add(nums[i]);
//            dfs(nums, used, list, res);
//            used[i] = false;
//            list.remove(list.size() - 1);
//        }
//    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permute(res, nums, 0);
        return res;
    }

    private void permute(List<List<Integer>> res, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(list);
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int i = index; i < nums.length; ++i) {
            if (appeared.add(nums[i])) {
                swap(nums, index, i);
                permute(res, nums, index + 1);
                swap(nums, index, i);
            }
        }
    }

    public void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }

}