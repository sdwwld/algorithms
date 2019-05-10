package com.wld.java.leetcode;

import java.util.HashMap;

public class leet287 {
    public static void main(String args[]) {
        System.out.print(8 + 9 >> 1);
        System.out.print(new leet287().findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

    public int findDuplicate(int[] nums) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.put("" + nums[i], "" + nums[i]) != null)
                return nums[i];
        }
        return -1;
    }
}
