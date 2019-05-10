package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet412 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public List<String> fizzBuzz(int n) {
//        List<String> ret = new ArrayList<>(n);
//        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
//            fizz++;
//            buzz++;
//            if (fizz == 3 && buzz == 5) {
//                ret.add("FizzBuzz");
//                fizz = 0;
//                buzz = 0;
//            } else if (fizz == 3) {
//                ret.add("Fizz");
//                fizz = 0;
//            } else if (buzz == 5) {
//                ret.add("Buzz");
//                buzz = 0;
//            } else {
//                ret.add(String.valueOf(i));
//            }
//        }
//        return ret;
//    }

//    public List<String> fizzBuzz(int n) {
//        List<String> output = new ArrayList<String>();
//        for (int i = 1; i <= n; i++) {
//            if (i % 15 == 0) {
//                output.add("FizzBuzz");
//            } else {
//                if (i % 3 == 0)
//                    output.add("Fizz");
//                else if (i % 5 == 0)
//                    output.add("Buzz");
//                else
//                    output.add(Integer.toString(i));
//            }
//        }
//        return output;
//    }


    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            res.add(i + "");
        }
        for (int i = 2; i < n; i += 3) {
            res.set(i, "Fizz");
        }
        for (int i = 4; i < n; i += 5) {
            res.set(i, "Buzz");
        }
        for (int i = 14; i < n; i += 15) {
            res.set(i, "FizzBuzz");
        }
        return res;
    }


}