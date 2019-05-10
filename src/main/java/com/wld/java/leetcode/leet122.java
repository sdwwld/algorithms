package com.wld.java.leetcode;

public class leet122 {

    public static void main(String args[]) {
        System.out.println(new leet122().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));

    }

    //    public int maxProfit(int[] prices) {
//        int total = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            if (prices[i + 1] > prices[i])
//                total += prices[i + 1] - prices[i];
//        }
//        return total;
//    }

//    public int maxProfit(int[] prices) {
//        int total = 0;
//        for (int i = 0; i < prices.length - 1; i++)
//            total += Math.max(prices[i + 1] - prices[i], 0);
//        return total;
//    }


    public int maxProfit(int[] prices) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) i++;
            int min = prices[i++];
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }

}