package com.wld.java.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class leet225 {
//232
    public static void main(String args[]) {
        System.out.println();

    }

    class MyStack {
        private Queue<Integer> que;

        public MyStack() {
            que = new LinkedList<>();
        }

        public void push(int x) {
            que.offer(x);//wld add到last中
            for (int i = 0; i < que.size() - 1; ++i) {
                que.offer(que.poll());
            }
        }

        public int pop() {
            return que.poll();
        }

        public int top() {
            return que.peek();
        }

        public boolean empty() {
            return que.isEmpty();
        }
    }
}