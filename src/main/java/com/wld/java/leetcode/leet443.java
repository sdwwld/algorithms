package com.wld.java.leetcode;

public class leet443 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int compress(char[] chars) {
//        int indexAns = 0, index = 0;
//        while (index < chars.length) {
//            char currentChar = chars[index];
//            int count = 0;
//            while (index < chars.length && chars[index] == currentChar) {
//                index++;
//                count++;
//            }
//            chars[indexAns++] = currentChar;
//            if (count != 1)
//                for (char c : Integer.toString(count).toCharArray())
//                    chars[indexAns++] = c;
//        }
//        return indexAns;
//    }

//    public int compress(char[] chars) {
//        int start = 0;
//        for (int end = 0, count = 0; end < chars.length; end++) {
//            count++;
//            if (end == chars.length - 1 || chars[end] != chars[end + 1]) {
//                chars[start] = chars[end];
//                start++;
//                if (count != 1) {
//                    char[] arr = String.valueOf(count).toCharArray();
//                    for (int i = 0; i < arr.length; i++, start++)
//                        chars[start] = arr[i];
//                }
//                count = 0;
//            }
//        }
//        return start;
//    }


//    public int compress(char[] chars) {
//        int l = 0, i = 0;
//        for (int j = 0; j < chars.length; j++) {
//            if (j + 1 == chars.length || chars[j + 1] != chars[j]) {
//                chars[l++] = chars[j];
//                if (i < j) {
//                    for (char c : ((j - i + 1) + "").toCharArray())
//                        chars[l++] = c;
//                }
//                i = j + 1;
//            }
//        }
//        return l;
//    }


    public int compress(char[] chars) {
        int count = 1;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            if (count == 1) {
                chars[index++] = chars[i];
            } else {
                chars[index++] = chars[i];
                String num = String.valueOf(count);
                for (int j = 0; j < num.length(); j++) {
                    chars[index++] = num.charAt(j);
                }
            }
            count = 1;
        }
        return index;
    }


}