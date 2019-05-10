package com.wld.java.leetcode;

public class leet121 {

    public static void main(String args[]) {
        System.out.println();
        int prices[] = {7, 1, 5, 3, 6, 4};
//        maxProfit(prices);
    }

//    public int maxProfit(int[] prices) {
//        int maxCur = 0, maxSoFar = 0;
//        for (int i = 1; i < prices.length; i++) {
//            maxCur = Math.max(0, maxCur + prices[i] - prices[i - 1]);
//            maxSoFar = Math.max(maxCur, maxSoFar);
//        }
//        return maxSoFar;
//    }


//    public static int maxProfit(int[] prices) {
//        int maxPro = 0, minPrice = Integer.MAX_VALUE;
//        for (int i = 0; i < prices.length; i++) {
//            minPrice = Math.min(minPrice, prices[i]);
//            maxPro = Math.max(prices[i] - minPrice, maxPro);
//        }
//        return maxPro;
//    }


    public int maxProfit(int[] prices) {
        int max = 0;
        if (prices.length == 0)
            return max;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                if (max < (prices[i] - min))
                    max = prices[i] - min;
            } else {
                min = prices[i];
            }
        }
        return max;
    }
}