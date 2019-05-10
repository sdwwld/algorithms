package com.wld.java.leetcode;

public class leet496 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int[] nextGreaterElement(int[] findNums, int[] nums) {
//        Map<Integer, Integer> map = new HashMap<>();
//        Stack<Integer> stack = new Stack<>();
//        for (int num : nums) {
//            while (!stack.isEmpty() && stack.peek() < num)
//                map.put(stack.pop(), num);
//            stack.push(num);
//        }
//        for (int i = 0; i < findNums.length; i++)
//            findNums[i] = map.getOrDefault(findNums[i], -1);
//        return findNums;
//    }


//    public int[] nextGreaterElement(int[] findNums, int[] nums) {
//        int[] ret = new int[findNums.length];
//        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = nums.length - 1; i >= 0; i--) {
//            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
//                stack.pop();
//            }
//            if (stack.isEmpty()) map.put(nums[i], -1);
//            else map.put(nums[i], stack.peek());
//            stack.push(nums[i]);
//        }
//        for (int i = 0; i < findNums.length; i++) {
//            ret[i] = map.get(findNums[i]);
//        }
//        return ret;
//    }


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = -1;
            boolean found = false;
            for (int j = 0; j < nums2.length; j++) {
                if (found) {
                    if (nums2[j] > nums1[i]) {
                        arr[i] = nums2[j];
                        break;
                    }
                } else {
                    if (nums1[i] == nums2[j]) {
                        found = true;
                    }
                }
            }
        }
        return arr;
    }

}