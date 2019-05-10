package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leet057 {
    public static void main(String args[]) {
        leet057 mleet057 = new leet057();

        List<Interval> intervals = new ArrayList<>();
        Interval mInterval1 = mleet057.new Interval(23, 25);
        Interval mInterval2 = mleet057.new Interval(34, 39);
        Interval mInterval3 = mleet057.new Interval(41, 43);
        Interval mInterval4 = mleet057.new Interval(49, 51);
        Interval mInterval5 = mleet057.new Interval(27, 27);
        intervals.add(mInterval1);
        intervals.add(mInterval2);
        intervals.add(mInterval3);
        intervals.add(mInterval4);
        System.out.println(mleet057.insert(intervals, mInterval5));
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++));
        return result;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return start + ":" + end;
        }
    }
}
