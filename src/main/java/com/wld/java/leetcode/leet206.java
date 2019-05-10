package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet206 {
    public static void main(String args[]) {
        System.out.print(8 + 9 >> 1);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tempList = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return tempList;
    }

//    public ListNode reverseList(ListNode head) {
//        ListNode pre = null;
//        while (head != null) {
//            ListNode next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
//    }
//
//
//    public ListNode reverseList(ListNode head) {
//        return reverseListInt(head, null);
//    }
//
//    private ListNode reverseListInt(ListNode head, ListNode newHead) {
//        if (head == null)
//            return newHead;
//        ListNode next = head.next;
//        head.next = newHead;
//        return reverseListInt(next, head);
//    }


}
