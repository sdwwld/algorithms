package com.wld.java.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeFile {
    private static final int LEETCODESTART = 1;
    private static final int LEETCODECOUNT = 936;
    private static final int EULERSTARTT = 1;
    private static final int EULERCOUNT = 616;
    private static final String dir = "C:\\Users\\Administrator\\Desktop\\layoutroot";

    public static void main(String[] args) {

        for (int i = LEETCODESTART; i <= LEETCODECOUNT; i++) {
            makeLeetCode(i);
        }
        for (int i = EULERSTARTT; i <= EULERCOUNT; i++) {
            makeEuler(i);
        }
    }

    private static void makeLeetCode(int i) {
        StringBuffer sb = new StringBuffer();
        String nameSub;
        if (i < 10) {
            nameSub = "00" + i;
        } else if (i < 100) {
            nameSub = "0" + i;
        } else {
            nameSub = "" + i;
        }
        sb.append("package com.wld.java.leetcode;\n" +
                "\n" +
                "public class leet" + nameSub + " {\n" +
                "\n" +
                "    public static void main(String args[]) {\n" +
                "        System.out.println();\n" +
                "        \n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}");
        File fileDir = new File(dir);
        if (!fileDir.exists())
            fileDir.mkdir();
        String fileName = "leet" + nameSub + ".java";
        File layxFile = new File(dir, fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void makeEuler(int i) {
        StringBuffer sb = new StringBuffer();
        String nameSub;
        if (i < 10) {
            nameSub = "00" + i;
        } else if (i < 100) {
            nameSub = "0" + i;
        } else {
            nameSub = "" + i;
        }

        sb.append("package com.wld.java.euler;\n" +
                "\n" +
                "public class euler" + nameSub + " {\n" +
                "\n" +
                "    public static void main(String args[]) {\n" +
                "        System.out.println(method" + nameSub + "(111));\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    private static int method" + nameSub + "(int arrays) {\n" +
                "\n" +
                "        return 111;\n" +
                "    }\n" +
                "\n" +
                "}");

        File fileDir = new File(dir);
        if (!fileDir.exists())
            fileDir.mkdir();
        String fileName = "euler" + nameSub + ".java";
        File layxFile = new File(dir, fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
