package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet448 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public List<Integer> findDisappearedNumbers(int[] nums) {
//        List<Integer> ret = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            int val = Math.abs(nums[i]) - 1;
//            if (nums[val] > 0) {
//                nums[val] = -nums[val];
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] > 0) {
//                ret.add(i + 1);
//            }
//        }
//        return ret;
//    }


//    public List<Integer> findDisappearedNumbers(int[] nums) {
//        List<Integer> res = new ArrayList<>();
//        int n = nums.length;
//        for (int i = 0; i < nums.length; i++) nums[(nums[i] - 1) % n] += n;
//        for (int i = 0; i < nums.length; i++) if (nums[i] <= n) res.add(i + 1);
//        return res;
//    }


//    public List<Integer> findDisappearedNumbers(int[] nums) {
//        Set<Integer> st = new HashSet<Integer>();
//        List<Integer> rt = new ArrayList<Integer>();
//        for (int ci : nums) {
//            st.add(ci);
//        }
//        for (int i = 1; i <= nums.length; i++) {
//            if (!st.contains(i)) {
//                rt.add(i);
//            }
//        }
//        return rt;
//    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }


}