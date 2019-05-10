package com.wld.java.utils;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }

    public static TreeNode initTreeNode(int[] array) {
        TreeNode root = new TreeNode();
        root.val = array[0];
        for (int i = 1; i < array.length; i++)
            addTreeNode(root, array[i]);
        return root;
    }

    private static void addTreeNode(TreeNode root, int data) {
        TreeNode node = new TreeNode(data);
        TreeNode p = root;
        while (true) {
            if (p.val > data) {
                if (p.left == null) {
                    p.left = node;
                    break;
                } else {
                    p = p.left;
                }

            } else {
                if (p.right == null) {
                    p.right = node;
                    break;
                } else {
                    p = p.right;
                }
            }
        }
    }

    public static ListNode initListNode(Integer[] A) {
        return initListNode(Arrays.asList(A));
    }

    public static ListNode initListNode(List<Integer> mList) {
        ListNode listNode = new ListNode(0);
        ListNode indexListNode = listNode;
        for (int i = 0, length = mList.size(); i < length; i++) {
            ListNode newNode = new ListNode(mList.get(i));
            indexListNode.next = newNode;
            newNode.pre = indexListNode;
            indexListNode = indexListNode.next;
        }
        listNode = listNode.next;
        return listNode;
    }

    public static ListNode indexNode(ListNode mListNode, int index) {
        ListNode tempNode = mListNode;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    public static void swapListNode1(ListNode a, ListNode b) {
        ListNode next = b.next;
        ListNode pre = b.pre;
        a.pre.next = b;
        b.pre = a.pre;
        a.next.pre = b;
        b.next = a.next;
        pre.next = a;
        a.pre = pre;
        next.pre = a;
        a.next = next;
    }

    public static void swapListNode2(ListNode a, ListNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public static void printListNode(ListNode mListNode) {
        ListNode tempListNode = mListNode;
        while (tempListNode != null) {
            System.out.println(tempListNode.val);
            tempListNode = tempListNode.next;
        }
    }

    private static String bitBinary(String binaryString, int bit) {
        String tempString = binaryString;
        StringBuilder stringBuilder = new StringBuilder(bit);
        if (tempString.length() < bit) {
            for (int i = 0, length = bit - tempString.length(); i < length; i++) {
                stringBuilder.append("0");
            }
            stringBuilder.append(tempString);
            tempString = stringBuilder.toString();
        }
        stringBuilder.delete(0, tempString.length());
        for (int i = 0; i < tempString.length(); i++) {
            stringBuilder.append(tempString.charAt(i));
            if ((i + 1) % 8 == 0) stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public static String bitInt32(int num) {
        return bitBinary(Integer.toBinaryString(num), 32);
    }

    public static String bitLong64(long num) {
        return bitBinary(Long.toBinaryString(num), 64);
    }

    public static void printTwoObjectArrays(Object[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.println(arrays[i][j]);
            }
        }
    }

    public static void printTwoCharArrays(char[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printTwoIntArrays(int[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printTwoLongArrays(long[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printList(List<List<Integer>> mList) {
        for (int i = 0; i < mList.size(); i++) {
            System.out.println(Arrays.toString(mList.get(i).toArray()));
        }
    }

    private static String addCharPre(String string, int count) {
        StringBuilder stringBuilder = new StringBuilder(count);
        if (string == null || string.length() == 0) {
            stringBuilder.append(" ", 0, count);
            return stringBuilder.toString();
        }
        for (int i = string.length(); i < count; i++) {
            stringBuilder.insert(0, " ");
        }
        return stringBuilder.toString();
    }

    private static String addCharAfter(String string, int count) {
        StringBuilder stringBuilder = new StringBuilder(count);
        if (string == null || string.length() == 0) {
            stringBuilder.append(" ", 0, count);
            return stringBuilder.toString();
        }
        for (int i = string.length(); i < count; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
