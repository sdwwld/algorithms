package com.wld.java.leetcode;

import com.wld.java.utils.ListNode;

public class leet061 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public ListNode rotateRight(ListNode head, int k) {
//        if (head == null) return head;
//        int len = 1; // number of nodes
//        ListNode newH, tail;
//        tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//            len++;
//        }
//        tail.next = head; // circle the link
//        if ((k %= len) != 0) {
//            for (int i = 0; i < len - k; i++)
//                tail = tail.next; // the tail node is the (len-k)-th node (1st node is head)
//        }
//        newH = tail.next;
//        tail.next = null;
//        return newH;
//    }


//    public ListNode rotateRight(ListNode head, int k) {
//        if (head == null) return head;
//        ListNode p = head;
//        int length = 1;
//        for (; p.next != null; ++length, p = p.next) ;
//        p.next = head; // circle the list.
//        for (k = length - k % length; k > 0; --k, p = p.next) ;
//        head = p.next;
//        p.next = null;
//        return head;
//    }

//    public ListNode rotateRight(ListNode head, int n) {
//        if (head == null || head.next == null) return head;
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//        ListNode fast = dummy, slow = dummy;
//        int i;
//        for (i = 0; fast.next != null; i++)//Get the total length
//            fast = fast.next;
//        for (int j = i - n % i; j > 0; j--) //Get the i-n%i th node
//            slow = slow.next;
//        fast.next = dummy.next; //Do the rotation
//        dummy.next = slow.next;
//        slow.next = null;
//        return dummy.next;
//    }

    //1.记住head的位置，然后将单向链表搞成环，那么只要将head的位置向右移动(n-k%n)个位置，然后再将环解开成单向链表，最后返回。
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int n = 1;
        ListNode cur = head;
        ListNode res = head;
        while (cur.next != null) {
            n++;
            cur = cur.next;
        }
        cur.next = head; //搞成循环
        k = k % n;
        while ((n - k) > 0) {
            k++;
            res = res.next;
        }
        cur = res;
        while (cur.next != res) {
            cur = cur.next;
        }
        cur.next = null;
        return res;
    }


}