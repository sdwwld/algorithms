package com.wld.java;

import java.util.ArrayList;

import com.wld.java.utils.ListNode;
import com.wld.java.utils.Util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class MyMain {

    public static void main(String args[]) {
//        int[] array = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
//        System.out.println("Before: " + Arrays.toString(array));
//        bogoSort(array);
//        System.out.println("After:  " + Arrays.toString(array));
        short s = 23;
        System.out.println(s);
        System.out.println(reverseBytesShort(s));
        System.out.println(coinSums(200, 0));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(722275200000l));
        swapNode();
        method(new int[]{1, 8, 0, 1, 4, 6, 6, 7, 4, 3, 7, 3, 0, 11});
    }

    //    private static int coinSums(int totalMoney, int index) {
//        int money[] = {1, 2, 5, 10, 20, 50, 100, 200};
//        int sum = 0;
//        for (int i = index; i < money.length; i++) {
//            if (totalMoney - money[i] == 0) {
//                sum++;
//                break;
//            }
//            if (totalMoney - money[i] > 0)
//                sum += coinSums(totalMoney - money[i], i);
//        }
//        return sum;
//    }
    private static int coinSums(int totalMoney, int index) {
        int money[] = {1, 2, 5, 10, 20, 50, 100, 200};
        int sum = 0;
        for (int i = index; i < money.length && totalMoney >= money[i]; i++) {
            if (totalMoney - money[i] == 0) {
                sum++;
                break;
            }
            sum += coinSums(totalMoney - money[i], i);
        }
        return sum;
    }

    private static int coinSums1(int totalMoney) {
        int money[] = {1, 2, 5, 10, 20, 50, 100, 200};
        int[][] dp = new int[money.length + 1][totalMoney + 1];
        for (int i = 0; i <= money.length; ++i) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= money.length; ++i) {
            for (int j = 1; j <= totalMoney; ++j) {
                dp[i][j] = 0;
                for (int k = 0; k <= j / money[i - 1]; ++k) {
                    dp[i][j] += dp[i - 1][j - k * money[i - 1]];
                }
            }
        }
        return dp[money.length][totalMoney];
    }

    private static final Random generator = new Random();

    public static void bogoSort(int[] array) {
        while (!isSorted(array)) {
            for (int i = 0; i < array.length; i++) {
                int randomPosition = generator.nextInt(array.length);
                int temp = array[i];
                array[i] = array[randomPosition];
                array[randomPosition] = temp;
            }
        }
    }

    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static short reverseBytesShort(short s) {
        int i = s & 0xffff;
        int reversed = (i & 0xff00) >>> 8
                | (i & 0x00ff) << 8;
        return (short) reversed;
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<Integer>(list));
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }


    private static void swapNode() {
        ListNode listNode = Util.initListNode(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        for (ListNode node = listNode; node != null; node = node.next) {
            System.out.println(node.val);
        }
        System.out.println();
        Util.swapListNode1(Util.indexNode(listNode, 0), Util.indexNode(listNode, 4));
        for (ListNode node = listNode; node != null; node = node.next) {
            System.out.println(node.val);
        }
    }

    public boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    private static void method(int[] array) {
        int i = 0, j = 1, temp;
        for (int k = 0; k < array.length; k++) {
            while (i < array.length) {
                if ((array[i] & 1) == 0) {
                    i += 2;
                } else break;
            }
            while (j < array.length) {
                if ((array[j] & 1) == 1) {
                    j += 2;
                } else break;
            }
            if (i >= array.length || j >= array.length)
                break;

//            if ((k & 1) == 1) {
//                if ((array[j] & 1) == 1)
//                    continue;
//                j = k;
//            } else {
//                if ((array[i] & 1) == 0)
//                    continue;
//                i = k;
//            }
//            if ((array[i] & 1) == 1 && (array[j] & 1) == 0) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
//            }
        }
        for (int k = 0; k < array.length; k++) {
            System.out.println(k + "----------" + array[k]);
        }
    }
}
