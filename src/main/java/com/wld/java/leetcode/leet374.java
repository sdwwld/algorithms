package com.wld.java.leetcode;

public class leet374 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int guessNumber(int n) {
//        int maxNumber = n, minNumber = 1;
//        while (true) {
//            int meanNumber = (maxNumber - minNumber) / 2 + minNumber;
//            int res = guess(meanNumber);
//            if (res == 0) {
//                return meanNumber;
//            } else if (res == 1) {
//                minNumber = meanNumber + 1;
//            } else {
//                maxNumber = meanNumber - 1;
//            }
//        }
//    }

//    public int guessNumber(int n) {
//        int i = 1, j = n;
//        while (i < j) {
//            int mid = i + (j - i) / 2;
//            if (guess(mid) == 0) {
//                return mid;
//            } else if (guess(mid) == 1) {
//                i = mid + 1;
//            } else {
//                j = mid;
//            }
//        }
//        return i;
//    }

//    public int guessNumber(int n) {
//        for (int start = 1, end = n; ; ) {
//            int mid = start + ((end - start) >> 1);
//            switch (guess(mid)) {
//                case -1:
//                    end = mid;
//                    break;
//                case 0:
//                    return mid;
//                case +1:
//                    start = mid + 1;
//                    break;
//            }
//        }
//    }

    /**
     * I think some folks may have the same question as I first encountered this unfamiliar divide operation. Let me try to add an explanation here:
     * <p>
     * The result of & indicates every bit that has 1 to carry over to higher bit.
     * The result of ^ indicates every bit that has 1 to stay.
     * & * 2 + ^ gives out the addition of two numbers.
     * & + ^ >> 1 gives out the addition of two numbers / 2.
     * &, ^ and >> operations will not overflow even two operands are MAX_VALUE.
     *
     * @param n
     * @return
     */
//    public int guessNumber(int n) {
//        int l = 0, r = n;
//        while (l < r) {
//            int m = (l & r) + ((l ^ r) >> 1);
//            if (guess(m) == 0)
//                return m;
//            else if (guess(m) == 1)
//                l = m + 1;
//            else
//                r = m - 1;
//        }
//        return l;
//    }


//    public int guessNumber(int n) {
//        return helper(1, n);
//    }
//
//    public int helper(int start, int end) {
//        if (start == end)
//            return start;
//        int mid = Math.toIntExact(((long) start + (long) end) / 2);
//        int r = 0;
//        if (guess(mid) == 0)
//            r = mid;
//        else if (guess(mid) == 1)
//            r = helper(mid + 1, end);
//        else if (guess(mid) == -1)
//            r = helper(start, mid - 1);
//        return r;
//    }


}