package com.wld.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leet219 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (i > k) set.remove(nums[i - k - 1]);//wld把k以外的移除掉,k以内有重复的就返回true.
//            if (!set.add(nums[i])) return true;
//        }
//        return false;
//    }


    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer ord = map.put(nums[i], i);
            if (ord != null && i - ord <= k) {
                return true;
            }
        }
        return false;
    }


}