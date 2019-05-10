package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class leet500 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public String[] findWords(String[] words) {
//        return Stream.of(words).filter(s -> s.toLowerCase()
//                .matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
//    }


//    public String[] findWords(String[] words) {
//        String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < strs.length; i++) {
//            for (char c : strs[i].toCharArray()) {
//                map.put(c, i);
//            }
//        }
//        List<String> res = new LinkedList<>();
//        for (String w : words) {
//            if (w.equals("")) continue;
//            int index = map.get(w.toUpperCase().charAt(0));
//            for (char c : w.toUpperCase().toCharArray()) {
//                if (map.get(c) != index) {
//                    index = -1;
//                    break;
//                }
//            }
//            if (index != -1) res.add(w);
//        }
//        return res.toArray(new String[0]);
//    }


//    public String[] findWords(String[] words) {
//        String[] base = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
//        List<String> list = new ArrayList<String>();
//        for (String string : words) {
//            for (String basStr : base) {
//                boolean find = true;
//                for (char c : string.toCharArray()) {
//                    String low = String.valueOf(c).toLowerCase();
//                    if (!basStr.contains(low)) {
//                        find = false;
//                        break;
//                    }
//                }
//                if (find) list.add(string);
//            }
//        }
//        String[] res = new String[list.size()];
//        for (int i = 0; i < res.length; i++) res[i] = list.get(i);
//        return res;
//    }


//    public String[] findWords(String[] words) {
//        List<String> a = new ArrayList<>();
//        for (int i = 0; i < words.length; i++) {
//            boolean flag = false;
//            if (words[i].toLowerCase().matches("[qwertyuiop]+")) {
//                flag = true;
//            }
//            if (words[i].toLowerCase().matches("[asdfghjkl]+")) {
//                flag = true;
//            }
//            if (words[i].toLowerCase().matches("[zxcvbnm]+")) {
//                flag = true;
//            }
//            if (flag) a.add(words[i]);
//        }
//        return a.toArray(new String[a.size()]);
//    }


    // zyxwvutsrqponmlkjihgfedcba
//    private static final int ROW1 = 0b00000001010110111100000100010000;
//    private static final int ROW2 = 0b00000000000001000000111011101001;
//    private static final int ROW3 = 0b00000010101000000011000000000110;
//
//    public String[] findWords(String[] words) {
//        String[] validWords = new String[words.length];
//        int validWordsFound = 0;
//        int wordLetterIndexes = 0;
//        for (String word : words) {
//            wordLetterIndexes = getLetterIndexes(word);
//            if ((wordLetterIndexes & ROW1) == wordLetterIndexes
//                    || (wordLetterIndexes & ROW2) == wordLetterIndexes
//                    || (wordLetterIndexes & ROW3) == wordLetterIndexes) {
//                validWords[validWordsFound++] = word;
//            }
//        }
//        return Arrays.copyOf(validWords, validWordsFound);
//    }
//
//    private static final int getLetterIndexes(String word) {
//        int letters = 0;
//        char c;
//        for (int i = 0; i < word.length(); i++) {
//            c = word.charAt(i);
//            letters |= 1 << ((c < 'a') ? c - 'A' : c - 'a');
//        }
//        return letters;
//    }


    public String[] findWords(String[] words) {
        List<String> result = new ArrayList(words.length);
        Map<Character, Integer> map = new HashMap<>();
        String level1 = "qwertyuiopQWERTYUIOP";
        String level2 = "asdfghjklASDFGHJKL";
        String level3 = "zxcvbnmZXCVBNM";
        for (char c : level1.toCharArray()) {
            map.put(c, 1);
        }
        for (char c : level2.toCharArray()) {
            map.put(c, 2);
        }
        for (char c : level3.toCharArray()) {
            map.put(c, 3);
        }
        for (String word : words) {
            boolean valid = true;
            int row = map.get(word.charAt(0));
            for (int i = 1; i < word.length(); i++) {
                if (row != map.get(word.charAt(i))) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                result.add(word);
        }
        return result.toArray(new String[]{});
    }


}