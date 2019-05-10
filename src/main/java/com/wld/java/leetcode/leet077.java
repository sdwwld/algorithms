package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class leet077 {
    //TODO没有去重
    public static void main(String args[]) {
        System.out.println(combine(4, 2));

    }

//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> list = new LinkedList<>();
//        list.add(new LinkedList<Integer>());
//        for (; k > 0; k--) {
//            List<List<Integer>> next = new LinkedList<>();
//            for (List<Integer> l : list) {
//                int start = l.isEmpty() ? 1 : l.get(l.size() - 1) + 1;
//                for (int j = start; j <= n - k + 1; j++) {
//                    l.add(j);
//                    next.add(new LinkedList<Integer>(l));
//                    l.remove(l.size() - 1);
//                }
//            }
//            list = next;
//        }
//        return list;
//    }

//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> list = new LinkedList<>();
//        backtrack(list,n,k,1,new ArrayList<Integer>());
//        return list;
//    }
//    private void backtrack(List<List<Integer>> list, int n, int k, int start, List<Integer> tempList){
//        if(k==0) {
//            list.add(new LinkedList<>(tempList));
//            return;
//        }
//        for(int i = start;i<=n-k+1;i++){
//            tempList.add(i);
//            backtrack(list,n,k-1,i+1,tempList);
//            tempList.remove(tempList.size()-1);
//        }
//    }

//    public List<List<Integer>> combine(int n, int k) {
//        if (n == 0 || k == 0 || k > n) return Collections.emptyList();
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        for (int i = 1; i <= n + 1 - k; i++) res.add(Arrays.asList(i));
//        for (int i = 2; i <= k; i++) {
//            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
//            for (List<Integer> list : res) {
//                for (int m = list.get(list.size() - 1) + 1; m <= n - (k - i); m++) {
//                    List<Integer> newList = new ArrayList<Integer>(list);
//                    newList.add(m);
//                    tmp.add(newList);
//                }
//            }
//            res = tmp;
//        }
//        return res;
//    }

//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        if (k > n || k < 0) {
//            return result;
//        }
//        if (k == 0) {
//            result.add(new ArrayList<Integer>());
//            return result;
//        }
//        result = combine(n - 1, k - 1);
//        for (List<Integer> list : result) {
//            list.add(n);
//        }
//        result.addAll(combine(n - 1, k));
//        return result;
//    }

//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> ans = new LinkedList();
//        if (n < k || k == 0) return ans;
//        ans = combine(n-1, k-1);
//        //if at this point ans is empty, it can only be that k-1 == 0,
//        //n-1<k-1 is not possible since n>=k (if n<k, the function would have already returned at an early point)
//        if (ans.isEmpty()) ans.add(new LinkedList<Integer>());
//        for (List<Integer> list:ans) list.add(n);
//        ans.addAll(combine(n-1, k));
//        return ans;
//    }

//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> answer = new ArrayList<List<Integer>>();
//        helper(n, 1, k, new ArrayList<Integer>(), answer);
//        return answer;
//    }
//
//    private void helper(int n, int x, int k, List<Integer> prev, List<List<Integer>> answer) {
//        if (k == 0) answer.add(prev);
//        else for (List<Integer> L; x <= n - k + 1; L.add(x), helper(n, ++x, k - 1, L, answer))
//            L = new ArrayList<Integer>(prev);
//    }


//    public List<List<Integer>> combine(int n, int k) {
//        if (k == n || k == 0) {
//            List<Integer> row = new LinkedList<>();
//            for (int i = 1; i <= k; ++i) {
//                row.add(i);
//            }
//            return new LinkedList<>(Arrays.asList(row));
//        }
//        List<List<Integer>> result = this.combine(n - 1, k - 1);
//        result.forEach(e -> e.add(n));
//        result.addAll(this.combine(n - 1, k));
//        return result;
//    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        List<Integer> p = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            p.add(0);
        }
        while (i >= 0) {
            p.set(i, p.get(i) + 1);
            if (p.get(i) > n)
                --i;
            else if (i == k - 1) {
                result.add(new ArrayList<>(p));
            } else {
                ++i;
                p.set(i, p.get(i - 1));
            }
        }
        return result;
    }


}