package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;
import com.wld.java.utils.Util;

public class leet083 {

    public static void main(String args[]) {
        System.out.println();
        ListNode head = Util.initListNode(new Integer[]{1, 1, 2});
        Util.printListNode(new leet083().deleteDuplicates(head));
    }

//    public ListNode deleteDuplicates(ListNode head) {
//        if (head == null) return null;
//        ListNode FakeHead = new ListNode(0);
//        FakeHead.next = head;
//        ListNode pre = FakeHead;
//        ListNode cur = head;
//        while (cur != null) {
//            while (cur.next != null && cur.val == cur.next.val) {
//                cur = cur.next;
//            }
//            if (pre.next == cur) {
//                pre = pre.next;
//            } else {
//                pre.next = cur;
//                pre = pre.next;
//            }
//            cur = cur.next;
//        }
//        return FakeHead.next;
//    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }
}