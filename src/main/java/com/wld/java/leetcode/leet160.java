package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet160 {

    public static void main(String args[]) {
        System.out.println();

    }

    //画个图才更好理解
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }


}