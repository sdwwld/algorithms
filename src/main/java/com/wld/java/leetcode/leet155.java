package com.wld.java.leetcode;

import java.util.Stack;

public class leet155 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public class MinStack {
//        long min;
//        Stack<Long> stack;
//
//        public MinStack() {
//            stack = new Stack<>();
//        }
//
//        public void push(int x) {
//            if (stack.isEmpty()) {
//                stack.push(0L);
//                min = x;
//            } else {
//                stack.push(x - min);//Could be negative if min value needs to change
//                if (x < min) min = x;
//            }
//        }
//
//        public void pop() {
//            if (stack.isEmpty()) return;
//            long pop = stack.pop();
//            if (pop < 0) min = min - pop;//If negative, increase the min value
//        }
//
//        public int top() {
//            long top = stack.peek();
//            if (top > 0) {
//                return (int) (top + min);
//            } else {
//                return (int) (min);
//            }
//        }
//
//        public int getMin() {
//            return (int) min;
//        }
//    }


//    class MinStack {//wld push方法会加入很多min
//        int min = Integer.MAX_VALUE;
//        Stack<Integer> stack = new Stack<>();
//
//        public void push(int x) {
//            if (x <= min) {
//                stack.push(min);
//                min = x;
//            }
//            stack.push(x);
//        }
//
//        public void pop() {
//            if (stack.pop() == min) min = stack.pop();
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int getMin() {
//            return min;
//        }
//    }


    class MinStack {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        public void push(int x) {
            s1.push(x);
            if (s2.empty() || x <= getMin())
                s2.push(x);
        }

        public void pop() {
            if (s1.pop() == getMin()) s2.pop();
        }

        public int top() {
            return s1.peek();
        }

        public int getMin() {
            return s2.peek();
        }
    }


//    class MinStack {
//        private ListNode head;
//
//        public void push(int x) {
//            if (head == null)
//                head = new ListNode(x, x);
//            else
//                head = new ListNode(x, Math.min(x, head.min), head);
//        }
//
//        public void pop() {
//            head = head.next;
//        }
//
//        public int top() {
//            return head.val;
//        }
//
//        public int getMin() {
//            return head.min;
//        }
//    }


}