package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet141 {

    public static void main(String args[]) {
        System.out.println();

    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }

//    public boolean hasCycle(ListNode head) {
//        if (head == null || head.next == null) return false;
//        if (head.next == head) return true;
//        ListNode nextNode = head.next;
//        head.next = head;
//        return hasCycle(nextNode);
//    }
}