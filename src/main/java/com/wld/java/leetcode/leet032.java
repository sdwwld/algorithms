package com.wld.java.leetcode;

import java.util.Stack;

public class leet032 {

    public static void main(String args[]) {
        System.out.println(longestValidParentheses(")()())"));
//        System.out.println(longestValidParentheses(")()())()()()()"));
//        System.out.println(longestValidParentheses("()()(()"));

    }

    public static int longestValidParentheses(String s) {
        int n = s.length(), longest = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                st.push(i);
            else {
                if (!st.empty()) {
                    if (s.charAt(st.peek()) == '(')
                        st.pop();
                    else
                        st.push(i);
                } else {
                    st.push(i);
                }
            }
        }

        if (st.empty())
            longest = n;
        else {
            int a = n, b = 0;
            while (!st.empty()) {
                b = st.pop();
                longest = Math.max(longest, a - b - 1);
                a = b;
            }
            longest = Math.max(longest, a);
        }
        return longest;
    }

}