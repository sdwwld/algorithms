package com.wld.java.leetcode;

import com.wld.java.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet078 {

    public static void main(String args[]) {
//        Util.printList(subsets(new int[]{1, 2, 3}));

    }

//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
//        backtrack(list, new ArrayList<>(), nums, 0);
//        return list;
//    }
//
//    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
//        list.add(new ArrayList<>(tempList));
//        for (int i = start; i < nums.length; i++) {
//            tempList.add(nums[i]);
//            backtrack(list, tempList, nums, i + 1);
//            tempList.remove(tempList.size() - 1);
//        }
//    }


//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
//        backtrack(list, new ArrayList<>(), nums, 0);
//        return list;
//    }
//
//    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
//        list.add(new ArrayList<>(tempList));
//        for (int i = start; i < nums.length; i++) {
//            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
////            if (tempList.contains(nums[i])) continue; // element already exists, skip
//            tempList.add(nums[i]);
//            backtrack(list, tempList, nums, i + 1);
//            tempList.remove(tempList.size() - 1);
//        }
//    }


//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> allSubsets = new ArrayList<>();
//        int max = 1 << nums.length;
//        for (int i = 0; i < max; i++) {
//            ArrayList subSets = new ArrayList();
//            int k = i;
//            int index = 0;
//            while (k > 0) {
//                if ((k & 1) > 0) {
//                    subSets.add(nums[index]);
//                }
//                k >>= 1;
//                ++index;
//            }
//            allSubsets.add(subSets);
//        }
//        return allSubsets;
//    }

//    public static List<List<Integer>> subsets(int[] nums) {
//        int length = 1 << nums.length;
//        List<List<Integer>> ret = new ArrayList<>(length);
//        ret.add(new ArrayList<>());
//        for (int i = 1; i < length; ++i) {
//            ret.add(new ArrayList<>());
//            for (int j = 0; (1 << j) <= i; ++j)
//                if ((i & (1 << j)) != 0) {
//                    List<Integer> list = ret.get(i - 1);
//                    list.add(nums[j]);
//                    ret.set(i - 1, list);
//                }
//        }
//        return ret;
//    }

//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        result.add(new ArrayList<>());
//        for (int n : nums) {
//            int size = result.size();
//            for (int i = 0; i < size; i++) {
//                List<Integer> subset = new ArrayList<>(result.get(i));
//                subset.add(n);
//                result.add(subset);
//            }
//        }
//        return result;
//    }

//    public List<List<Integer>> subsets(int[] nums) {
//        Arrays.sort(nums); // make sure subsets are ordered
//        List<List<Integer>> result = new ArrayList<>();
//        result.add(new ArrayList<>()); // start with empty set
//        for (int i = 0; i < nums.length; ++i) {
//            for (int j = 0, size = result.size(); j < size; ++j) { // remember
//                List<Integer> subset = new ArrayList<>(result.get(j)); // copy a new one
//                subset.add(nums[i]); // expand
//                result.add(subset); // collect
//            }
//        }
//        return result;
//    }

//    public List<List<Integer>> subsets(int[] S) {
//        List<List<Integer>> res = new ArrayList<>();
//        res.add(new ArrayList<Integer>());
//        Arrays.sort(S);
//        for (int i : S) {
//            List<List<Integer>> tmp = new ArrayList<>();
//            for (List<Integer> sub : res) {
//                List<Integer> a = new ArrayList<>(sub);
//                a.add(i);
//                tmp.add(a);
//            }
//            res.addAll(tmp);
//        }
//        return res;
//    }


//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> ans = new ArrayList<>();
//        if (nums == null) { return ans; }
//        Arrays.sort(nums);  // non-descending order
//        dfs(ans, nums, new ArrayList<Integer>(), 0);
//        return ans;
//    }
//
//    private void dfs(List<List<Integer>> ans, int[] nums, List<Integer> list, int index) {
//        if (index == nums.length) { ans.add(new ArrayList<Integer>(list)); return; }
//        dfs(ans, nums, list, index+1);  // not pick the number at this index
//        list.add(nums[index]);
//        dfs(ans, nums, list, index+1);  // pick the number at this index
//        list.remove(list.size()-1);
//    }

//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        List<Integer> subset = new ArrayList<Integer>();
//        res.add(subset);
//
//        Arrays.sort(nums);
//        doSubsets(nums,res,subset,0);
//        return res;
//    }
//
//    private void doSubsets(int[] nums, List res,List subset,int start){
//        if(start != nums.length){
//            for(int i=start; i<nums.length; i++){
//                subset.add(nums[i]);
//                res.add(new ArrayList(subset));
//                doSubsets(nums,res,subset,i+1);
//                subset.remove(subset.get(subset.size()-1));
//            }
//        }
//    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<List<Integer>>(1 << nums.length);
        powerSet.add(new ArrayList<Integer>());

        for (int num : nums)
            for (int i = 0, j = powerSet.size(); i < j; i++) {
                List<Integer> withNum = new ArrayList<Integer>(powerSet.get(i));
                withNum.add(num);
                powerSet.add(withNum);
            }

        return powerSet;
    }


}