package com.wld.java.leetcode;

public class leet060 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public String getPermutation(int n, int k) {
//        List<Integer> numbers = new ArrayList<>();
//        int[] factorial = new int[n + 1];
//        StringBuilder sb = new StringBuilder();
//        int sum = 1;
//        factorial[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            sum *= i;
//            factorial[i] = sum;
//        }
//        for (int i = 1; i <= n; i++) {
//            numbers.add(i);
//        }
//        k--;
//        for (int i = 1; i <= n; i++) {
//            int index = k / factorial[n - i];
//            sb.append(String.valueOf(numbers.get(index)));
//            numbers.remove(index);
//            k -= index * factorial[n - i];
//        }
//        return String.valueOf(sb);
//    }

//    public String getPermutation(int n, int k) {
//        List<Integer> num = new LinkedList<Integer>();
//        for (int i = 1; i <= n; i++) num.add(i);
//        int[] fact = new int[n];  // factorial
//        fact[0] = 1;
//        for (int i = 1; i < n; i++) fact[i] = i * fact[i - 1];
//        k = k - 1;
//        StringBuilder sb = new StringBuilder();
//        for (int i = n; i > 0; i--) {
//            int ind = k / fact[i - 1];
//            k = k % fact[i - 1];
//            sb.append(num.get(ind));
//            num.remove(ind);
//        }
//        return sb.toString();
//    }


    public String getPermutation(int n, int k) {
        k--;
        int fact = 1;
        char[] result = new char[n];
        result[n - 1] = '1';
        for (int i = 2; i <= n; i++) {
            fact *= i;
            result[n - i] = (char) (k % fact * i / fact + '1');
            for (int j = n - i + 1; j < n; j++) {
                result[j] += result[j] >= result[n - i] ? 1 : 0;
            }
            k -= k % fact;
        }
        return new String(result);
    }

}