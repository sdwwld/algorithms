package com.wld.java.leetcode;

import java.util.Arrays;

public class leet475 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int findRadius(int[] houses, int[] heaters) {
//        Arrays.sort(heaters);
//        int result = Integer.MIN_VALUE;
//        for (int house : houses) {
//            int index = Arrays.binarySearch(heaters, house);
//            if (index < 0) {
//                index = -(index + 1);
//            }
//            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
//            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
//
//            result = Math.max(result, Math.min(dist1, dist2));
//        }
//        return result;
//    }


//    public int findRadius(int[] houses, int[] heaters) {
//        TreeSet<Integer> heatersSet = new TreeSet<>();
//        for(int h : heaters) heatersSet.add(h);
//        int min = 0;
//        for(int house : houses) {
//            Integer floor = heatersSet.floor(house);
//            Integer ceiling = heatersSet.ceiling(house);
//            if(floor == null) min = Math.max(min, ceiling - house);
//            else if(ceiling == null) min = Math.max(min, house - floor);
//            else min = Math.max(min, Math.min(ceiling - house, house - floor));
//        }
//        return min;
//    }


//    public int findRadius(int[] houses, int[] heaters) {
//        TreeSet<Integer> set = new TreeSet<>();
//        for (int heater : heaters)
//            set.add(heater);
//        int res = 0;
//        for (int house : houses) {
//            int dist1 = set.ceiling(house) == null ? Integer.MAX_VALUE : set.ceiling(house) - house;
//            int dist2 = set.floor(house) == null ? Integer.MAX_VALUE : house - set.floor(house);
//            res = Math.max(res, Math.min(dist1, dist2));
//        }
//        return res;
//    }

//    public int findRadius(int[] houses, int[] heaters) {
//        Arrays.sort(houses);
//        Arrays.sort(heaters);
//        int i = 0, j = 0, res = 0;
//        while (i < houses.length) {
//            while (j < heaters.length - 1
//                    && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
//                j++;
//            }
//            res = Math.max(res, Math.abs(heaters[j] - houses[i]));
//            i++;
//        }
//        return res;
//    }


//    public int findRadius(int[] houses, int[] heaters) {
//        Arrays.sort(houses);
//        Arrays.sort(heaters);
//        int i = 0, res = 0;
//        for (int house : houses) {
//            while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
//                i++;
//            }
//            res = Math.max(res, Math.abs(heaters[i] - house));
//        }
//        return res;
//    }


    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        for (int house : houses) {
            while (i < heaters.length && heaters[i] < house) {
                i++;
            }
            if (i == 0)
                radius = Math.max(radius, heaters[i] - house);
            else if (i == heaters.length)
                return Math.max(radius, houses[houses.length-1] - heaters[heaters.length-1]);
            else
                radius = Math.max(radius, Math.min(heaters[i] - house, house - heaters[i - 1]));
        }
        return radius;
    }


}