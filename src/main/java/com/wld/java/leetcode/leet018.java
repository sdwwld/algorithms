package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet018 {
    public static void main(String args[]) {
    }

    public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> mList = new ArrayList<>();
        for (int i = 0; i < num.length - 3; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                for (int k = i + 1; k < num.length - 2; k++) {
                    if (k == i + 1 || num[k] != num[k - 1]) {
                        int lo = k + 1, hi = num.length - 1, sum = target - num[i] - num[k];
                        while (lo < hi) {
                            if (num[lo] + num[hi] == sum) {
                                mList.add(Arrays.asList(num[i], num[k], num[lo], num[hi]));
                                while (lo < hi && num[lo] == num[lo + 1]) lo++;
                                while (lo < hi && num[hi] == num[hi - 1]) hi--;
                                lo++;
                                hi--;
                            } else if (num[lo] + num[hi] < sum) lo++;
                            else hi--;
                        }
                    }
                }
            }
        }
        return mList;
    }
}
