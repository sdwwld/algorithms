package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;
import com.wld.java.utils.Util;

public class leet203 {

    public static void main(String args[]) {
        System.out.println();
        ListNode mListNode = Util.initListNode(new Integer[]{1});
//        Util.printListNode(new leet203().removeElements(mListNode, 1));
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }


//    public ListNode removeElements(ListNode head, int val) {
//        for (ListNode pre = head, x = head; x != null; x = x.next) {
//            if (x.val == val) {
//                if (x == head) {
//                    head = head.next;
//                } else {
//                    pre.next = x.next;
//                }
//            } else {
//                pre = x;
//            }
//        }
//        return head;
//    }


//    public ListNode removeElements(ListNode head, int val) {
//        ListNode fakeHead = new ListNode(-1);
//        fakeHead.next = head;
//        ListNode curr = head, prev = fakeHead;
//        while (curr != null) {
//            if (curr.val == val) {
//                prev.next = curr.next;
//            } else {
//                prev = prev.next;
//            }
//            curr = curr.next;
//        }
//        return fakeHead.next;
//    }


//    public ListNode removeElements(ListNode head, int val) {
//        while (head != null && head.val == val) head = head.next;
//        ListNode curr = head;
//        while (curr != null && curr.next != null)
//            if (curr.next.val == val)
//                curr.next = curr.next.next;
//            else
//                curr = curr.next;
//        return head;
//    }


//    public ListNode removeElements(ListNode head, int val) {
//        if (head == null) return null;
//        ListNode pointer = head;
//        while (pointer.next != null) {
//            if (pointer.next.val == val)
//                pointer.next = pointer.next.next;
//            else
//                pointer = pointer.next;
//        }
//        return head.val == val ? head.next : head;
//    }


//    public ListNode removeElements(ListNode head, int val) {
//        ListNode fakeNode = new ListNode(0);
//        fakeNode.next = head;
//        ListNode res = fakeNode;
//        while (fakeNode.next != null) {
//            if (fakeNode.next.val == val)
//                fakeNode.next = fakeNode.next.next;
//            else fakeNode = fakeNode.next;
//        }
//        return res.next;
//    }

}