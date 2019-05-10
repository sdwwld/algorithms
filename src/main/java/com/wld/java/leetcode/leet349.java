package com.wld.java.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class leet349 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int[] intersection(int[] nums1, int[] nums2) {
//        Set<Integer> set = new HashSet<>();
//        Set<Integer> intersect = new HashSet<>();
//        for (int i = 0; i < nums1.length; i++) {
//            set.add(nums1[i]);
//        }
//        for (int i = 0; i < nums2.length; i++) {
//            if (set.contains(nums2[i])) {
//                intersect.add(nums2[i]);
//            }
//        }
//        int[] result = new int[intersect.size()];
//        int i = 0;
//        for (Integer num : intersect) {
//            result[i++] = num;
//        }
//        return result;
//    }


    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }


}