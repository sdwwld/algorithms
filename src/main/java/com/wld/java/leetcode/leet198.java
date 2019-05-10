package com.wld.java.leetcode;

public class leet198 {

    public static void main(String args[]) {
        System.out.println(new leet198().rob(new int[]{1, 8, 1, 1, 1, 1, 1, 1, 9}));
    }

//    public int rob(int[] nums) {
//        return rob(nums, nums.length - 1);
//    }
//
//    private int rob(int[] nums, int i) {
//        if (i < 0)
//            return 0;
//        return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
//    }


//    public int rob(int[] nums) {
//        return rob(nums, nums.length - 1, new HashMap<>());
//    }
//
//    private int rob(int[] nums, int i, HashMap<Integer, Integer> hashMap) {
//        if (i < 0)
//            return 0;
//        int first = 0;
//        int second = 0;
//        if (hashMap.containsKey(i - 2))
//            first = hashMap.get(i - 2);
//        else {
//            first = rob(nums, i - 2, hashMap);
//            hashMap.put(i - 2, first);
//        }
//
//        if (hashMap.containsKey(i - 1))
//            second = hashMap.get(i - 1);
//        else {
//            second = rob(nums, i - 1, hashMap);
//            hashMap.put(i - 1, second);
//        }
//        return Math.max(first + nums[i], second);
//    }


//    public int rob(int[] nums) {
//        if (nums.length == 0) return 0;
//        int[] memo = new int[nums.length + 1];
//        memo[0] = 0;
//        memo[1] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            int val = nums[i];
//            memo[i + 1] = Math.max(memo[i], memo[i - 1] + val);
//        }
//        return memo[nums.length];
//    }


//    public int rob(int[] num) {
//        int prevNo = 0;
//        int prevYes = 0;
//        for (int n : num) {
//            int temp = prevNo;
//            prevNo = Math.max(prevNo, prevYes);
//            prevYes = n + temp;
//        }
//        return Math.max(prevNo, prevYes);
//    }

//    public int rob(int[] num) {
//        int rob = 0; //max monney can get if rob current house
//        int notrob = 0; //max money can get if not rob current house
//        for (int i = 0; i < num.length; i++) {
//            int currob = notrob + num[i]; //if rob current value, previous house must not be robbed
//            notrob = Math.max(notrob, rob); //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
//            rob = currob;
//        }
//        return Math.max(rob, notrob);
//    }

//    public int rob(int num[]) {
//        int a = 0;
//        int b = 0;
//        for (int i = 0; i < num.length; i++) {
//            if ((i & 1) == 0) {
//                a = Math.max(a + num[i], b);
//            } else {
//                b = Math.max(a, b + num[i]);
//            }
//        }
//        return Math.max(a, b);
//    }

//    public int rob(int[] num) {
//        int[][] dp = new int[num.length + 1][2];
//        for (int i = 1; i <= num.length; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
//            dp[i][1] = num[i - 1] + dp[i - 1][0];
//        }
//        return Math.max(dp[num.length][0], dp[num.length][1]);
//    }


//    public int rob(int[] nums) {
//        if (nums.length == 0) return 0;
//        if (nums.length == 1) return nums[0];
//
//        //Initialize an arrays to store the money
//        int[] mark = new int[nums.length];
//
//        //We can infer the formula from problem:mark[i]=max(num[i]+mark[i-2],mark[i-1])
//        //so initialize two nums at first.
//        mark[0] = nums[0];
//        mark[1] = Math.max(nums[0], nums[1]);
//
//        //Using Dynamic Programming to mark the max money in loop.
//        for (int i = 2; i < nums.length; i++) {
//            mark[i] = Math.max(nums[i] + mark[i - 2], mark[i - 1]);
//        }
//        return mark[nums.length - 1];
//    }


    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int f[] = new int[n];
        f[0] = nums[0];
        f[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; ++i)
            f[i] = Math.max(f[i - 2] + nums[i], f[i - 1]);
        return f[n - 1];
    }

    /**
     * f(0) = nums[0]
     * f(1) = max(num[0], num[1])
     * f(k) = max( f(k-2) + nums[k], f(k-1) )
     * @param nums
     * @return
     */
    /**
     * class Solution:
     * <p>
     * def rob(self, nums):
     * <p>
     * last, now = 0, 0
     * <p>
     * for i in nums: last, now = now, max(last + i, now)
     * <p>
     * return now
     *
     * @param nums
     * @return
     */
//    public int rob(int[] nums) {
//        int last = 0, now = 0;
//        for (int i : nums) {
//            int temp = now;
//            now = Math.max(last + i, now);
//            last = temp;
//        }
//        return now;
//    }

}