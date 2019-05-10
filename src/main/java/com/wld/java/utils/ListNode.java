package com.wld.java.utils;

public class ListNode {
    public int val;
    public int min;
    public ListNode next;
    public ListNode pre;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, int min) {
        this(val, min, null);
    }

    public ListNode(int val, int min, ListNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}

