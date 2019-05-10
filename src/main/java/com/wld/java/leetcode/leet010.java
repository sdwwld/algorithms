package com.wld.java.leetcode;

public class leet010 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public boolean isMatch(String s, String p) {
//        if (s == null || p == null) {
//            return false;
//        }
//        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
//        dp[0][0] = true;
//        for (int i = 0; i < p.length(); i++) {
//            if (p.charAt(i) == '*' && dp[0][i - 1]) {
//                dp[0][i + 1] = true;
//            }
//        }
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = 0; j < p.length(); j++) {
//                if (p.charAt(j) == '.') {
//                    dp[i + 1][j + 1] = dp[i][j];
//                }
//                if (p.charAt(j) == s.charAt(i)) {
//                    dp[i + 1][j + 1] = dp[i][j];
//                }
//                if (p.charAt(j) == '*') {
//                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
//                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
//                    } else {
//                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
//                    }
//                }
//            }
//        }
//        return dp[s.length()][p.length()];
//    }

//    public boolean isMatch(String s, String p) {
//        int m = s.length(), n = p.length();
//        boolean[][] dp = new boolean[m + 1][n + 1];
//        dp[0][0] = true;
//        for (int i = 0; i <= m; i++)
//            for (int j = 1; j <= n; j++)
//                if (p.charAt(j - 1) == '*')
//                    dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) =='.') && dp[i - 1][j]);
//                else
//                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) =='.');
//        return dp[m][n];
//    }

//    public boolean isMatch(String s, String p) {
//        //p必须包含.和*才能进行匹配，否则只能通过equals比较，这个很好理解
//        if (p.contains(".") || p.contains("*")) {
//            /**
//             * 先看前半部分，如果p的长度是1，那么p肯定是.或者*，如果是.那么下面的comp方法就会匹配成功，因为.
//             * 表示匹配任意字符，如果匹配成功那么都分别截取一位字符串从下一位继续开始匹配，因为
//             * 如果这里comp匹配成功只有在p等于.的时候，因为*不参与匹配，而.只能匹配一位，不能匹配多位，
//             * 所以下一步要分别往后移一位，如果匹配失败，只能返回false里，就不在匹配了。其实也可以分开写
//             * if(p.length() == 1) return comp(s, p, 0)&&s.length()==1;
//             * if (p.charAt(1) != '*')
//             *    return comp(s, p, 0) && isMatch(s.substring(1), p.substring(1));
//             *只不过这里把它合并了，我们再看后半部分p.charAt(1) != '*'，
//             * 这里p的长度肯定是大于等于2了，这里为什么要判断p的第二位不能等于*，因为如果等于*，那么就表示匹配前面的
//             * 0个或多个字符。既然第二位不是*，那么就先匹配第一位，如果失败那么整个匹配就失败了，直接返回
//             * false，如果成功再从下一位开始。如果p的第二位是*那么就稍微麻烦一点，可以看一下下面for循环的解析
//             */
//            if (p.length() == 1 || p.charAt(1) != '*')
//                return comp(s, p.charAt(0), 0) && isMatch(s.substring(1), p.substring(1));
//            /**
//             * 如果执行到这一步就表示p的长度肯定是大于或等于2的，并且p的第二位一定是*，这里
//             * 首先让p截取两位之后的字符和s匹配，如果成功，直接返回true，因为如果后面匹配都成功了，那么前面两个无影响，
//             * 因为之前p的第二位是*。注意下面的comp方法和isMatch方法，当i不等于0的时候comp只要返回false，for循环
//             * 就停止了，就表示匹配失败，另外isMatch只要返回true，就表示匹配成功，也会停止循环。
//             * 可以这样简单理解，comp表示前面匹配的是成功的，isMatch表示截取之后的匹配是成功的，只有在前面和后面都
//             * 匹配成功的情况下那么整个匹配才算成功，注意这里的递归调用，isMatch调用他自己，明白这点就很好理解了。
//             * 注意这里的p的第二个字符是*，他是不能匹配的，因为s中是没有*字符的，他只是表示匹配前面的0个或多个字符，所以
//             * 他不参加字符匹配，只能用*前面的字符匹配，如果前面的字符匹配失败，那么就彻底失败。下面的isMatch方法如果
//             * 匹配失败，那么没关系，可以继续for循环，因为p的第二位是*，只要comp匹配成功就行，下面的isMatch匹配失败也
//             * 可能是截取字符串截取少了，因为*可以匹配多个，截取一个字符isMatch匹配失败，那么我可以截取两个字符继续匹配，
//             * 直到comp匹配失败或者isMatch匹配成功为止。
//             */
//            for (int i = 0; i == 0 || comp(s, p.charAt(0), i - 1); i++) {
//                if (isMatch(s.substring(i), p.substring(2)))
//                    return true;
//            }
//        }
//        return s.equals(p);//如果没有可匹配的.和*那么只能通过equals比较了
//    }
//
//    private boolean comp(String s, char pFirst, int i) {
//        //单个字符匹配，这里表示p的第一个字符和s的第i个字符是否匹配,一定要理解.和*的含义，.是表示
//        //匹配任意单个字符，*是表示匹配0个或多个前面的字符，注意是前面的,*不参与匹配
//        return s.length() > i && (pFirst == s.charAt(i) || pFirst == '.');
//    }


//    public boolean isMatch(String s, String p) {
//        if (p.contains(".") || p.contains("*")) {
//            if (p.length() == 1 || p.charAt(1) != '*')
//                return comp(s, p.charAt(0), 0) && isMatch(s.substring(1), p.substring(1));
//            for (int i = 0; i == 0 || comp(s, p.charAt(0), i - 1); i++) {
//                if (isMatch(s.substring(i), p.substring(2)))
//                    return true;
//            }
//        }
//        return s.equals(p);
//    }
//
//    private boolean comp(String s, char pFirst, int i) {
//        return s.length() > i && (pFirst == s.charAt(i) || pFirst == '.');
//    }


}