package com.wld.java.leetcode;

import java.util.LinkedList;
import java.util.List;

public class leet017 {

    public static void main(String args[]) {
//        System.out.println(Arrays.toString(letterCombinations("23").toArray()));
//        System.out.println(~4);
//        System.out.println(Integer.toBinaryString(~4));
//        System.out.println(Integer.toBinaryString(8));
//        System.out.println(8 & ~4);
    }



    public static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.length() == 0) return ans;
        String[] ref = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length() != digits.length()) {
            String remove = ans.poll();
            String map = ref[digits.charAt(remove.length()) - '0'];
            for (int i = 0; i < map.length(); i++)
                ans.addLast(remove + map.charAt(i));
        }
        return ans;
    }

}