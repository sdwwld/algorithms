package com.wld.java.leetcode;

public class leet704 {

    public static void main(String args[]) {
        System.out.println();

    }


    //    public static int search(int[] array, int key) {
//        if (array == null || array.length == 0)
//            return -1;
//        int length = array.length;
//        int k = 0;
//        while (length > fibonacci(k) - 1 || fibonacci(k) - 1 < 5) {
//            k++;
//        }
//        int[] fb = makeFbArray(fibonacci(k));
//        int[] temp = Arrays.copyOf(array, fb[k] - 1);
//        for (int i = length; i < temp.length; i++) {
//            temp[i] = array[length - 1];//用原数组最后的值填充
//        }
//        int low = 0;
//        int hight = length - 1;
//        while (low <= hight) {
//            int middle = low + fb[k - 1] - 1;
//            if (temp[middle] > key) {//要查找的值在前半部分
//                hight = middle - 1;
//                k = k - 1;
//            } else if (temp[middle] < key) {//要查找的值在后半部分
//                low = middle + 1;
//                k = k - 2;
//            } else {
//                if (middle <= hight) {
//                    return middle;
//                } else {
//                    return hight;
//                }
//            }
//        }
//        return -1;
//    }
//
//    private static int fibonacci(int n) {
//        if (n == 0 || n == 1)
//            return n;
//        return fibonacci(n - 1) + fibonacci(n - 2);
//    }
//
//    public static int[] makeFbArray(int length) {
//        int[] array = new int[length];
//        array[0] = 0;
//        array[1] = 1;
//        for (int i = 2; i < length; i++)
//            array[i] = array[i - 1] + array[i - 2];
//        return array;
//    }
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;

            if (nums[mid] < target)
                lo = mid + 1;
            else if (nums[mid] > target)
                hi = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}