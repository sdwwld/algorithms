package com.wld.java.leetcode;

public class leet492 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int[] constructRectangle(int area) {
//        int w = (int) Math.sqrt(area);
//        while (area % w != 0) w--;
//        return new int[]{area / w, w};
//    }


//    public int[] constructRectangle(int area) {
//        int[] result = new int[2];
//        if (area == 0) {
//            return result;
//        }
//        int a = (int) Math.sqrt(area);
//        while (area % a != 0) {
//            a--;
//        }
//        int b = area / a;
//        result[0] = b;
//        result[1] = a;
//        return result;
//    }


//    public int[] constructRectangle(int area) {
//        for (int mid = (int) Math.sqrt(area); mid > 0; mid--)
//            if ((area % mid) == 0)
//                return new int[]{area / mid, mid};
//        return new int[2];
//    }


//    public int[] constructRectangle(int area) {
//        int i = 0, j = area;
//        int[] result = new int[2];
//        while (i <= j) {
//            long product = i * j;
//            if (product == area) {
//                result[0] = j--;
//                result[1] = i++;
//            } else if (product > area) {
//                j--;
//            } else {
//                i++;
//            }
//        }
//        return result;
//    }


    public static int[] constructRectangle(int area) {
        int low = 1;
        int high = area;
        int left, right;
        left = right = (int) Math.sqrt(low + high);
        while (left >= 1 && right <= area) {
            int tmp_area = left * right;
            if (tmp_area == area) {
                return new int[]{right, left};
            }
            if (tmp_area > area)
                left--;
            else
                right++;
        }
        return new int[0];
    }


}