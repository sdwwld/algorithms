package com.wld.java.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class leet414 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int thirdMax(int[] nums) {//堆
//        Integer max1 = null;
//        Integer max2 = null;
//        Integer max3 = null;
//        for (Integer n : nums) {
//            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
//            if (max1 == null || n > max1) {
//                max3 = max2;
//                max2 = max1;
//                max1 = n;
//            } else if (max2 == null || n > max2) {
//                max3 = max2;
//                max2 = n;
//            } else if (max3 == null || n > max3) {
//                max3 = n;
//            }
//        }
//        return max3 == null ? max1 : max3;
//    }

//    public int thirdMax(int[] nums) {
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        Set<Integer> set = new HashSet<>();
//        for (int i : nums) {
//            if (!set.contains(i)) {
//                pq.offer(i);
//                set.add(i);
//                if (pq.size() > 3) {
//                    set.remove(pq.poll());
//                }
//            }
//        }
//        if (pq.size() < 3) {
//            while (pq.size() > 1) {
//                pq.poll();
//            }
//        }
//        return pq.peek();
//    }

    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3)
                set.pollFirst();
        }
        return set.size() > 2 ? set.pollFirst() : set.pollLast();
    }

}