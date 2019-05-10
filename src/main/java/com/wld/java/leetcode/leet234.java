package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet234 {

    public static void main(String args[]) {
        System.out.println();

    }

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

//    private ListNode temp;
//
//    public boolean isPalindrome(ListNode head) {
//        temp = head;
//        return check(head);
//    }
//
//    private boolean check(ListNode p) {
//        if (p == null) return true;
//        boolean isPal = check(p.next) & (temp.val == p.val);
//        temp = temp.next;
//        return isPal;
//    }

//    public boolean isPalindrome(ListNode head) {
//        ListNode temp = head;
//        Stack<Integer> stack = new Stack();
//        while (temp != null) {
//            stack.push(temp.val);
//            temp = temp.next;
//        }
//
//        while (head != null) {
//            if (head.val != stack.pop()) {
//                return false;
//            }
//            head = head.next;
//        }
//        return true;
//    }
}