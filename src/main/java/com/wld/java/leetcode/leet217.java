package com.wld.java.leetcode;

import java.util.HashSet;
import java.util.Set;

public class leet217 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean containsDuplicate(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] == nums[j]) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


//    public boolean containsDuplicate(int[] nums) {
//        Arrays.sort(nums);
//        for (int ind = 1; ind < nums.length; ind++) {
//            if (nums[ind] == nums[ind - 1]) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean containsDuplicate(int[] nums) {
//        final Set<Integer> distinct = new HashSet<Integer>();
//        for(int num : nums) {
//            if(distinct.contains(num)) {
//                return true;
//            }
//            distinct.add(num);
//        }
//        return false;
//    }

//    public boolean containsDuplicate(int[] nums) {
//        final Set<Integer> distinct = new HashSet<Integer>();
//        for (int num : nums) {
//            distinct.add(num);
//        }
//        return distinct.size() != nums.length;
//    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums)
            if (!set.add(i))
                return true;
        return false;
    }


}