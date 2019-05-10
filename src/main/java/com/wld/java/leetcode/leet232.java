package com.wld.java.leetcode;

import java.util.Stack;

public class leet232 {
    //225
    public static void main(String args[]) {
        System.out.println();

    }

//    class MyQueue {
//        Stack<Integer> input = new Stack();
//        Stack<Integer> output = new Stack();
//
//        public MyQueue() {
//
//        }
//
//        public void push(int x) {
//            input.push(x);
//        }
//
//        public int pop() {
//            int pop = peek();
//            output.pop();
//            return pop;
//        }
//
//        public int peek() {
//            if (output.empty())
//                while (!input.empty())
//                    output.push(input.pop());
//            return output.peek();
//        }
//
//        public boolean empty() {
//            return input.empty() && output.empty();
//        }
//    }


//    class MyQueue {
//        Stack<Integer> queue = new Stack<>();
//
//        public void push(int x) {
//            Stack<Integer> temp = new Stack<>();
//            while (!queue.empty()) {
//                temp.push(queue.pop());
//            }
//            queue.push(x);
//            while (!temp.empty()) {
//                queue.push(temp.pop());
//            }
//        }
//
//        public int pop() {
//            return queue.pop();
//        }
//
//        public int peek() {
//            return queue.peek();
//        }
//
//        public boolean empty() {
//            return queue.empty();
//        }
//    }


//    public class MyQueue {
//        Stack<Integer> s = new Stack<>();
//
//        public void push(int x) {
//            pushHelper(x);
//        }
//
//        public void pushHelper(int x) {
//            if (s.size() == 0) {
//                s.push(x);
//                return;
//            }
//            int data;
//            data = s.pop();
//            pushHelper(x);
//            s.push(data);
//            return;
//        }
//
//        public int pop() {
//            return s.pop();
//        }
//
//        public int peek() {
//            return s.peek();
//        }
//
//        public boolean empty() {
//            return s.size() == 0;
//        }
//    }

//
    public class MyQueue {
        Stack<Integer> s1 = new Stack();
        Stack<Integer> s2 = new Stack();

        public void push(int x) {
            while (!s2.isEmpty())
                s1.push(s2.pop());
            s1.push(x);
        }

        public int pop() {
            while (!s1.isEmpty())
                s2.push(s1.pop());
            return s2.pop();
        }

        public int peek() {
            while (!s1.isEmpty())
                s2.push(s1.pop());

            return s2.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }


}