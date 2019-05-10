package com.wld.java.leetcode;

public class leet169 {

    public static void main(String args[]) {
        int array[] = {};
//        System.out.println(majorityElement(array));
    }

    //	5 ms
//    public static int majorityElement(int[] num) {
//        int major = num[0], count = 1;
//        for (int i = 1; i < num.length; i++) {
//            if (count == 0) {
//                count++;
//                major = num[i];
//            } else if (major == num[i]) {
//                count++;
//            } else count--;
//
//        }
//        return major;
//    }

    //4 ms
//    public int majorityElement(int num[]) {
//        int cnt = 0, res = 0;
//        for (int i = 0; i < num.length; ++i) {
//            if (cnt == 0)
//                res = num[i];
//            if (res == num[i])
//                ++cnt;
//            else
//                --cnt;
//        }
//        return res;
//    }

    //5 ms
    public int majorityElement(int num[]) {
        int majorityIndex = 0;
        for (int count = 1, i = 1; i < num.length; i++) {
            if ((num[majorityIndex] == num[i])) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }
        return num[majorityIndex];
    }


    //    //8 ms,如果一个数大于一半，那么二进制各位上的和肯定大于一半
//    public int majorityElement(int[] nums) {
//        int major = 0, n = nums.length;
//        for (int i = 0, mask = 1; i < 32; i++, mask <<= 1) {
//            int bitCounts = 0;
//            for (int j = 0; j < n; j++) {
//                if ((nums[j] & mask) != 0) bitCounts++;
//                if (bitCounts > n / 2) {
//                    major |= mask;
//                    break;
//                }
//            }
//        }
//        return major;
//    }

//    //7 ms
//    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length / 2];
//    }

    //11 ms,比较0和1，哪个多就是哪个
//    public int majorityElement(int[] num) {
//        int ret = 0;
//        for (int i = 0; i < 32; i++) {
//            int ones = 0, zeros = 0;
//            for (int j = 0; j < num.length; j++) {
//                if ((num[j] & (1 << i)) != 0) {
//                    ++ones;
//                } else
//                    ++zeros;
//            }
//            if (ones > zeros)
//                ret |= (1 << i);
//        }
//        return ret;
//    }


    //39 ms
//    public int majorityElement(int[] nums) {
//        HashMap<Integer, Integer> counts = new HashMap<>();
//        int n = nums.length;
//        for (int i = 0; i < n; i++) {
//            int count = counts.getOrDefault(nums[i], 0);
//            if (count >= n / 2) return nums[i];
//            counts.put(nums[i], ++count);
//        }
//        return Integer.MIN_VALUE;
//    }


}