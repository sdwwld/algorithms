package com.wld.java.leetcode;

public class leet071 {

    public static void main(String args[]) {
        System.out.println();

    }

//    public String simplifyPath(String path) {
//        if (path == null || path.length() == 0)
//            return path;
//        int upCount = 0;
//        String[] str = path.split("/");
//        StringBuilder result = new StringBuilder();
//        for (int i = str.length - 1; i >= 0; --i) {
//            if (str[i].isEmpty() || str[i].equals("."))
//                continue;
//            if (str[i].equals("..")) {
//                ++upCount;
//                continue;
//            }
//            if (upCount > 0) {
//                --upCount;
//                continue;
//            }
//            result.insert(0, str[i]);
//            result.insert(0, "/");
//        }
//        return result.length() == 0 ? "/" : result.toString();
//    }


//    public String simplifyPath(String path) {
//        Deque<String> stack = new LinkedList<>();
//        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
//        for (String dir : path.split("/")) {
//            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
//            else if (!skip.contains(dir)) stack.push(dir);
//        }
//        String res = "";
//        for (String dir : stack) res = "/" + dir + res;
//        return res.isEmpty() ? "/" : res;
//    }

//    public String simplifyPath(String path) {
//        Stack<String> stack = new Stack<>();
//        String[] p = path.split("/");
//        for (int i = 0; i < p.length; i++) {
//            if (!stack.empty() && p[i].equals(".."))
//                stack.pop();
//            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
//                stack.push(p[i]);
//        }
//        List<String> list = new ArrayList(stack);
//        return "/" + String.join("/", list);
//    }

}