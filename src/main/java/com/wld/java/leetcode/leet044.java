package com.wld.java.leetcode;

public class leet044 {

    public static void main(String args[]) {
        System.out.println(isMatch("afdsfdf", "d*f"));
        System.out.println(isMatch("afdsfdf", "a?d*f"));
        System.out.println(isMatch("afdsfdf", "a?d*f*****"));
        System.out.println(isMatch("afdsfdf", "*****a?d*f*****"));

    }

    public static boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()) {
            if (p < pattern.length() && (pattern.charAt(p) == '?' ||
                    str.charAt(s) == pattern.charAt(p))) {
                //单个字符匹配成功，s和p都要往后移一位，然后从下一个字符开始匹配
                s++;
                p++;
            } else if (p < pattern.length() && pattern.charAt(p) == '*') {
                /**
                 * pattern当前的字符是*，*可以匹配任意字符，所以这里先不进行字符的比较，
                 * 记录下s和p的值，其实就是str和pttern所匹配成功字符的位置。然后p指针要
                 * 往后移一位，下一步就是p字符串中*的下一个字符和str中的字符匹配，如果成功
                 * 就进入上一个if语句，s和p都要往后挪一步，如果不成功，说明pattern中*字符
                 * 和str中的字符匹配成功，走下一个if语句。
                 */
                starIdx = p;
                match = s;
                p++;
            } else if (starIdx != -1) {
                /**
                 *如果进入到这里，说明之前遇到了pattern的*字符，而*字符可以匹配任意字符，所以
                 *s要往后移一位，用它的下一位继续和pattern匹配，而p继续指向pattern中*的下一位
                 */
                p = starIdx + 1;
                match++;
                s = match;
            } else {
                //如果上面匹配都不成功，并且也没遇到pattern的*，那么表示匹配失败，直接return就行了。
                return false;
            }
        }
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            /**
             *这里表示如果前面都匹配成功，如果pattern在后面有一大串全部都是*的话，也表示匹配成功
             */
            p++;
        }
        //最上面的while循环s已经走到了结尾，但p不一定走到最后，这里判断pattern的每一个是不是都匹配完。
        return p == pattern.length();
    }
}