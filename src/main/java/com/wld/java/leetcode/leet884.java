package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet884 {
    public static void main(String args[]) {
        System.out.print(8 + 9 >> 1);
        System.out.println(Arrays.toString(new leet884().uncommonFromSentences("gw pk xy",
                "gw aje zqd")));
    }

//    public String[] uncommonFromSentences(String A, String B) {
//        HashMap<String, Integer> hashMap = new HashMap<>();
//        for (String string : (A + " " + B).split(" ")) {
//            hashMap.put(string, hashMap.getOrDefault(string, 0) + 1);
//        }
//        List<String> mList = new ArrayList();
//        for (String string : hashMap.keySet()) {
//            if (hashMap.get(string) == 1)
//                mList.add(string);
//        }
//        return mList.toArray(new String[mList.size()]);
//    }

    public String[] uncommonFromSentences(String A, String B) {
        String[] split = (A + " " + B).split(" ");
        Arrays.sort(split);
        List<String> mList = new ArrayList();
        int length = split.length - 1;
        while (length >= 0) {
            while (length > 0 && split[length].equals(split[length - 1])) length--;
            if (length == split.length - 1)
                mList.add(split[length]);
            else if (length == 0 && !split[0].equals(split[1]))
                mList.add(split[length]);
            else if (length > 0 && !split[length].equals(split[length + 1]) && !split[length].equals(split[length - 1]))
                mList.add(split[length]);
            length--;
        }
        return mList.toArray(new String[mList.size()]);
    }
}
