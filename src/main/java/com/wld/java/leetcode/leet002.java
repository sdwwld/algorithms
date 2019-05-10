package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet002 {

    public static void main(String args[]) {
        System.out.println();

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode prev = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            prev.next = new ListNode(sum % 10);
            carry = sum / 10;
            prev = prev.next;
        }
        return head.next;
    }

}