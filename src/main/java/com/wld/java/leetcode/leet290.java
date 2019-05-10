package com.wld.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leet290 {

    public static void main(String args[]) {
        System.out.println();

    }

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map<Object, Integer> index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

}