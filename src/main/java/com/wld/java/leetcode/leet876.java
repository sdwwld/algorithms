package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet876 {
    public static void main(String args[]) {
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
