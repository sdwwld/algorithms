package com.wld.java.leetcode;

public class leet167 {

    public static void main(String args[]) {
        System.out.println();

    }

    // public int[] twoSum(int[] num, int target) {
//     int[] indice = new int[2];
//     if (num == null || num.length < 2) return indice;
//     int left = 0, right = num.length - 1;
//     while (left < right) {
//         int v = num[left] + num[right];
//         if (v == target) {
//             indice[0] = left + 1;
//             indice[1] = right + 1;
//             break;
//         } else if (v > target) {
//             right --;
//         } else {
//             left ++;
//         }
//     }
//     return indice;
// }

    public int[] twoSum(int[] num, int target) {
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                return new int[]{left + 1, right + 1};
            } else if (v > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{0, 0};
    }
}