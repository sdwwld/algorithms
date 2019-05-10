package com.wld.java.leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class leet056 {

    public static void main(String args[]) {
        System.out.println();

    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

//    public List<Interval> merge(List<Interval> intervals) {
//        if (intervals.size() <= 1)
//            return intervals;
//        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
//        List<Interval> result = new LinkedList<>();
//        int start = intervals.get(0).start;
//        int end = intervals.get(0).end;
//
//        for (Interval interval : intervals) {
//            if (interval.start <= end)
//                end = Math.max(end, interval.end);
//            else {
//                result.add(new Interval(start, end));
//                start = interval.start;
//                end = interval.end;
//            }
//        }
//        result.add(new Interval(start, end));
//        return result;
//    }


//    public List<Interval> merge(List<Interval> intervals) {
//        ArrayDeque<Interval> res = new ArrayDeque<>();
//        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
//        pq.addAll(intervals);
//        while (!pq.isEmpty()) {
//            Interval curr = pq.poll();
//            if (res.size() == 0) {
//                res.add(curr);
//                continue;
//            }
//            Interval last = res.getLast();
//            if (curr.start <= last.end) {
//                last.end = Math.max(curr.end, last.end);
//            } else {
//                res.add(curr);
//            }
//        }
//        List<Interval> resList = new ArrayList<>();
//        for (Interval interval : res) {
//            resList.add(interval);
//        }
//        return resList;
//    }


//    public List<Interval> merge(List<Interval> intervals) {
//        List<Interval> result = new LinkedList<>();
//        if (intervals == null || intervals.size() <= 1) {
//            return intervals;
//        }
//        PriorityQueue<Interval> pq = new PriorityQueue<>(intervals.size(), (a, b) -> (a.start - b.start));
//        for (int i = 0; i < intervals.size(); i++) {
//            pq.offer(intervals.get(i));
//        }
//        while (pq.size() > 1) {
//            Interval i1 = pq.poll();
//            Interval i2 = pq.poll();
//            if (i1.end >= i2.start) {
//                Interval node = new Interval(i1.start, Math.max(i2.end, i1.end));
//                pq.offer(node);
//            } else {
//                result.add(i1);
//                pq.offer(i2);
//            }
//        }
//        result.add(pq.poll());
//        return result;
//    }


//    public List<Interval> merge(List<Interval> intervals) {
//        intervals.sort(Comparator.comparingInt(o -> o.start));
//        LinkedList<Interval> merged = new LinkedList<>();
//        for (Interval interval : intervals)
//            if (merged.isEmpty() || merged.getLast().end < interval.start)
//                merged.add(interval);
//            else merged.getLast().end = Math.max(merged.getLast().end, interval.end);
//        return merged;
//    }

    //最快
//    public List<Interval> merge(List<Interval> intervals) {
//        int n = intervals.size();
//        int[] starts = new int[n];
//        int[] ends = new int[n];
//        for (int i = 0; i < n; i++) {
//            starts[i] = intervals.get(i).start;
//            ends[i] = intervals.get(i).end;
//        }
//        Arrays.sort(starts);
//        Arrays.sort(ends);
//        List<Interval> res = new ArrayList<>();
//        for (int i = 0, j = 0; i < n; i++) {
//            if (i == n - 1 || starts[i + 1] > ends[i]) {
//                res.add(new Interval(starts[j], ends[i]));
//                j = i + 1;
//            }
//        }
//        return res;
//    }


    public List<Interval> merge(List<Interval> intervals) {
        int N = intervals.size();
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i, Interval j) {
                return i.end - j.end;
            }
        });
        for (int i = N - 1; i > 0; i--) {
            Interval inter1 = intervals.get(i - 1);
            Interval inter2 = intervals.get(i);
            if (inter1.end >= inter2.start) {
                inter1.start = Math.min(inter1.start, inter2.start);
                inter1.end = inter2.end;
                intervals.remove(i);
            }
        }
        return intervals;
    }


}