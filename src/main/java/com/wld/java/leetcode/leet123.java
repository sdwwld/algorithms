package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet123 {
    public static void main(String args[]) {
    }

    int maxProfit(List<Integer> prices) {
        // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions.
        // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
        //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
        // f[0, ii] = 0; 0 times transation makes 0 profit
        // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
        if (prices.size() <= 1) return 0;
        else {
            int K = 2; // number of max transation allowed
            int maxProf = 0;
            List<List<Integer>> f = new ArrayList<>();
            for (int i = 0; i < K + 1; i++) {
                List<Integer> integers = new ArrayList<>();
                for (int j = 0; j < prices.size(); j++) {
                    integers.add(0);
                }
                f.add(integers);
            }
            for (int kk = 1; kk <= K; kk++) {
                int tmpMax = f.get(kk - 1).get(0) - prices.get(0);
                for (int ii = 1; ii < prices.size(); ii++) {
                    f.get(kk).set(ii, Math.max(f.get(kk).get(ii - 1), prices.get(ii) + tmpMax));
                    tmpMax = Math.max(tmpMax, f.get(kk - 1).get(ii) - prices.get(ii));
                    maxProf = Math.max(f.get(kk).get(ii), maxProf);
                }
            }
            return maxProf;
        }
    }
}
