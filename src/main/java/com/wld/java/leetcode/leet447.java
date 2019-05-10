package com.wld.java.leetcode;

public class leet447 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public int numberOfBoomerangs(int[][] points) {
//        int res = 0;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < points.length; i++) {
//            for (int j = 0; j < points.length; j++) {
//                if (i == j)
//                    continue;
//                int d = getDistance(points[i], points[j]);
//                map.put(d, map.getOrDefault(d, 0) + 1);
//            }
//            for (int val : map.values()) {
//                res += val * (val - 1);
//            }
//            map.clear();
//        }
//        return res;
//    }
//
//    private int getDistance(int[] a, int[] b) {
//        int dx = a[0] - b[0];
//        int dy = a[1] - b[1];
//        return dx * dx + dy * dy;
//    }

//    public int numberOfBoomerangs(int[][] points) {
//        if (points.length == 0 || points[0].length == 0) return 0;
//        int ret = 0;
//        for (int i = 0; i < points.length; i++) {
//            Map<Integer, Set<int[]>> map = new HashMap<>();
//            int[] p = points[i];
//            for (int j = 0; j < points.length; j++) {
//                if (j == i) continue;
//                int[] q = points[j];
//                int dis = getDis(p, q);
//                if (!map.containsKey(dis)) map.put(dis, new HashSet<int[]>());
//                map.get(dis).add(q);
//            }
//            for (Integer key : map.keySet()) {
//                int size = map.get(key).size();
//                if (size >= 2) ret += (size * (size - 1));
//            }
//        }
//        return ret;
//    }
//
//    public int getDis(int[] p, int[] q) {
//        int a = p[0] - q[0];
//        int b = p[1] - q[1];
//        return a * a + b * b;
//    }


//    public int numberOfBoomerangs(int[][] points) {
//        int result = 0;
//        HashMap<Integer, Integer> distMap = new HashMap<Integer, Integer>();
//        for (int[] i : points) {
//            for (int[] j : points) {
//                if (i == j) continue;
//                int dist = (i[0] - j[0]) * (i[0] - j[0]) + (i[1] - j[1]) * (i[1] - j[1]);
//                int prevDist = distMap.containsKey(dist) ? distMap.get(dist) : 0;
//                result += 2 * prevDist;
//                distMap.put(dist, prevDist + 1);
//            }
//            distMap.clear();
//        }
//        return result;
//    }


//    public int numberOfBoomerangs(int[][] p) {
//        int n = p.length;
//        if (n == 0) return 0;
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            Map<Double, Integer> map = new HashMap<>();
//            for (int j = 0; j < n; j++) {
//                if (map.containsKey(distance(p[i], p[j]))) {
//                    int value = map.get(distance(p[i], p[j]));
//                    count += 2 * value;
//                    map.put(distance(p[i], p[j]), value + 1);
//                } else {
//                    map.put(distance(p[i], p[j]), 1);
//                }
//            }
//        }
//        return count;
//    }
//
//    public Double distance(int[] a, int[] b) {
//        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
//    }


}