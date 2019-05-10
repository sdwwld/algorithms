package com.wld.java.leetcode;

public class leet202 {

    public static void main(String args[]) {
        System.out.println(new leet202().isHappy(19));

    }


//    public boolean isHappy(int n) {
//        int slow, fast;
//        slow = fast = n;
//        do {
//            slow = digitSquareSum(slow);
//            fast = digitSquareSum(fast);
//            fast = digitSquareSum(fast);
//        } while (slow != fast);
//
//        if (slow == 1)
//            return true;
//        else
//            return false;
//    }
//
//    private int digitSquareSum(int n) {
//        int sum = 0, tmp;
//        while (n != 0) {
//            tmp = n % 10;
//            sum += tmp * tmp;
//            n /= 10;
//        }
//        return sum;
//    }


//    public boolean isHappy(int n) {
//        Set<Integer> inLoop = new HashSet<>();
//        int squareSum, remain;
//        while (inLoop.add(n)) {
//            squareSum = 0;
//            while (n > 0) {
//                remain = n % 10;
//                squareSum += remain * remain;
//                n /= 10;
//            }
//            if (squareSum == 1)
//                return true;
//            else
//                n = squareSum;
//        }
//        return false;
//    }


//    public boolean isHappy(int n) {
//        int x = n;
//        int y = n;
//        while (x > 1) {
//            x = cal(x);
//            if (x == 1) return true;
//            y = cal(cal(y));
//            if (y == 1) return true;
//            if (x == y) return false;
//        }
//        return true;
//    }
//
//    public int cal(int n) {
//        int x = n;
//        int s = 0;
//        while (x > 0) {
//            s = s + (x % 10) * (x % 10);
//            x = x / 10;
//        }
//        return s;
//    }


    /*
    def isHappy(self, n):
        mem = set()
        while n != 1:
            n = sum([int(i) ** 2 for i in str(n)])
            if n in mem:
                return False
            else:
                mem.add(n)
        else:
            return True
     */
//    public boolean isHappy(int n) {
//        Set<Integer> mem = new HashSet<>();
//        while (n != 1) {
//            int tmp, sum = 0;
//            while (n != 0) {
//                tmp = n % 10;
//                sum += tmp * tmp;
//                n /= 10;
//            }
//            n = sum;
//            if (mem.contains(n))
//                return false;
//            else mem.add(n);
//        }
//        return true;
//    }


//    public boolean isHappy(int n) {
//        while (n > 6) {
//            int next = 0;
//            while (n != 0) {
//                next += (n % 10) * (n % 10);
//                n /= 10;
//            }
//            n = next;
//        }
//        return n == 1;
//    }

//    public boolean isHappy(int n) {
//        if (n <= 0) return false;
//        int magic = 4;
//        while (true) {
//            if (n == 1) return true;
//            if (n == magic) return false;
//            int t = 0;
//            while (n != 0) {
//                t += (n % 10) * (n % 10);
//                n /= 10;
//            }
//            n = t;
//        }
//    }

    /**
     * In order to find a rule to break out the loop, I start calculating 2 and find a loop at 4, then 3,5,6,9
     * will all go into that loop. So in 1-9, only 1 and 7 are happy numbers. Also I find all numbers' calculation
     * will goes into a single digit at some time. So what I did is just calculate happy sum and when it is a single digit, check if it is 1 or 7 ^.^
     *
     * @param n
     * @return
     */
//    public boolean isHappy(int n) {
//        if (n <= 0) return false;
//        while (true) {
//            int sum = 0;
//            while (n != 0) {
//                sum += (n % 10) * (n % 10);
//                n = n / 10;
//            }
//            if (sum / 10 == 0) {
//                if (sum == 1 || sum == 7) return true;
//                else return false;
//            }
//            n = sum;
//        }
//    }


//    public boolean isHappy(int n) {
//        int num = 0;
//        while (n != 1 && n != 4) {
//            while (n != 0) {
//                num += (n % 10) * (n % 10);
//                n /= 10;
//            }
//            n = num;
//            num = 0;
//        }
//        return 1 == n;
//    }
    public boolean isHappy(int n) {
        if (n == 1 || n == 7) return true;
        else if (n < 10) return false;
        int m = 0;
        while (n != 0) {
            int tail = n % 10;
            m += tail * tail;
            n = n / 10;
        }
        return isHappy(m);
    }

}