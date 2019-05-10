package com.wld.java.leetcode;

public class leet006 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public String convert(String s, int nRows) {
//        char[] c = s.toCharArray();
//        int len = c.length;
//        StringBuffer[] sb = new StringBuffer[nRows];
//        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
//
//        int i = 0;
//        while (i < len) {
//            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
//                sb[idx].append(c[i++]);
//            for (int idx = nRows - 2; idx >= 1 && i < len; idx--) // obliquely up
//                sb[idx].append(c[i++]);
//        }
//        for (int idx = 1; idx < sb.length; idx++)
//            sb[0].append(sb[idx]);
//        return sb[0].toString();
//    }


//    public String convert(String s, int numRows) {
//        String result = "";
//        if (numRows == 1)
//            return s;
//        int step1, step2;
//        int len = s.length();
//        for (int i = 0; i < numRows; ++i) {
//            step1 = (numRows - i - 1) * 2;
//            step2 = (i) * 2;
//            int pos = i;
//            if (pos < len)
//                result += s.charAt(pos);
//            while (true) {
//                pos += step1;
//                if (pos >= len)
//                    break;
//                if (step1 != 0)
//                    result += s.charAt(pos);
//                pos += step2;
//                if (pos >= len)
//                    break;
//                if (step2 != 0)
//                    result += s.charAt(pos);
//            }
//        }
//        return result;
//    }

    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder("");   //init every sb element **important step!!!!
        }
        int incre = 1;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            sb[index].append(s.charAt(i));
            if (index == 0) {
                incre = 1;
            }
            if (index == numRows - 1) {
                incre = -1;
            }
            index += incre;
        }
        String re = "";
        for (int i = 0; i < sb.length; i++) {
            re += sb[i];
        }
        return re.toString();
    }

}