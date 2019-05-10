package com.wld.java.leetcode;

public class leet485 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int findMaxConsecutiveOnes(int[] nums) {
//        int maxHere = 0, max = 0;
//        for (int n : nums)
//            max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
//        return max;
//    }

//    public int findMaxConsecutiveOnes(int[] nums) {
//        int cnt = 0, ret = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 1) {
//                cnt++;
//                ret = Math.max(ret, cnt);
//            } else {
//                cnt = 0;
//            }
//        }
//        return ret;
//    }


//    public int findMaxConsecutiveOnes(int[] nums) {
//        int max = 0;
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum = (sum + nums[i]) * nums[i];
//            if (max < sum) {
//                max = sum;
//            }
//        }
//        return max;
//    }


    public int findMaxConsecutiveOnes(int[] nums) {
        int maxOnes = 0;
        for (int i = 0; i < nums.length; i++) {
            int newOnes = 0;
            while (i < nums.length && nums[i] == 1) {
                newOnes++;
                i++;
            }
            maxOnes = Math.max(maxOnes, newOnes);
        }
        return maxOnes;
    }

}