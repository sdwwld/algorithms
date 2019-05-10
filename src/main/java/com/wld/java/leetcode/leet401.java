package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet401 {

    public static void main(String args[]) {
        System.out.println(new leet401().readBinaryWatch(0));

    }

//    public List<String> readBinaryWatch(int num) {
//        List<String> times = new ArrayList<>();
//        for (int h = 0; h < 12; h++)
//            for (int m = 0; m < 60; m++)
//                if (Integer.bitCount(h * 64 + m) == num)
//                    times.add(String.format("%d:%02d", h, m));
//        return times;
//    }


    final int[] watch = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};

    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        if (num >= 0)
            read_recursion(num, 0, list, 0, 0);
        return list;
    }

    private void read_recursion(int num, int start, List<String> list, int hour, int minute) {
        if (num <= 0) {
            if (hour < 12 && minute < 60) {
                if (minute < 10)
                    list.add(hour + ":0" + minute);
                else
                    list.add(hour + ":" + minute);
            }
        } else {
            for (int i = start; i < watch.length; i++) {
                if (i < 4)
                    read_recursion(num - 1, i + 1, list, hour + watch[i], minute);
                else
                    read_recursion(num - 1, i + 1, list, hour, minute + watch[i]);
            }
        }
    }

//    public List<String> readBinaryWatch(int num) {
//        List<String> res = new ArrayList<>();
//        dfs(res, num, 0, 0, 0);
//        return res;
//    }
//
//    private void dfs(List<String> res, int n, int hours, int mins, int idx) {
//        if (hours >= 12 || mins > 59) return;
//        if (n == 0) {
//            res.add(hours + ":" + (mins < 10 ? "0" + mins : mins));
//            return;
//        }
//        for (int i = idx; i < 10; i++) {
//            if (i < 4) {
//                dfs(res, n - 1, hours | (1 << i), mins, i + 1);
//            } else {
//                int k = i - 4;
//                dfs(res, n - 1, hours, mins | (1 << k), i + 1);
//            }
//        }
//    }


}