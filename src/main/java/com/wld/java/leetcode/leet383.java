package com.wld.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leet383 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean canConstruct(String ransomNote, String magazine) {
//        int[] arr = new int[26];
//        for (int i = 0; i < magazine.length(); i++) {
//            arr[magazine.charAt(i) - 'a']++;
//        }
//        for (int i = 0; i < ransomNote.length(); i++) {
//            if (--arr[ransomNote.charAt(i) - 'a'] < 0) {
//                return false;
//            }
//        }
//        return true;
//    }

//    public boolean canConstruct(String ransomNote, String magazine) {
//        Map<Character, Integer> magM = new HashMap<>();
//        for (char c : magazine.toCharArray()) {
//            magM.put(c, magM.getOrDefault(c, 0) + 1);
//        }
//        for (char c : ransomNote.toCharArray()) {
//            int newCount = magM.getOrDefault(c, 0) - 1;
//            if (newCount < 0)
//                return false;
//            magM.put(c, newCount);
//        }
//        return true;
//    }

}