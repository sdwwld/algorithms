package com.wld.java.leetcode;

public class leet453 {

    public static void main(String args[]) {
        System.out.println();

    }

    /**
     * 已知：数列nums，初始和s0，长度n，最小的数为m
     * 假设移动k步
     * 每移动一步，n-1个数会被＋1，则最终和s = s0 +(n-1) x k
     * 平均数为 s/n
     * 最小数每次移动都被+1，因此有：k =s/n -m
     * 即：（s0 +(n-1) x k）/n -m =k
     * 求得： k = s0 - m x n
     *
     * @param nums
     * @return
     */

//    public int minMoves(int[] nums) {
//        if (nums.length < 2) return 0;
//        int min = Integer.MAX_VALUE;
//        int sum = 0;
//        for (int item : nums) {
//            sum += item;
//            min = Math.min(min, item);
//        }
//        return sum - min * nums.length;
//    }
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int minN = nums[0], res = 0;
        for (int num : nums) minN = Math.min(minN, num);
        for (int num : nums) res += (num - minN);
        return res;
    }

//    public int minMoves(int[] nums) {
//        if (nums.length < 2) return 0;
//        Arrays.sort(nums);
//        int endi = nums.length - 1;
//        int total = 0;
//        while (nums[endi] != nums[0]) {
//            int cur = nums[endi] - nums[0];
//            total += cur;
//            nums[0] += cur;
//            endi--;
//            nums[endi] += total;
//        }
//        return total;
//    }

}