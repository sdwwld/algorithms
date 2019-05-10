package com.wld.java.leetcode;

public class leet176 {

    public static void main(String args[]) {
        System.out.println();

    }

    /**
     * SELECT max(Salary)
     * FROM Employee
     * WHERE Salary < (SELECT max(Salary) FROM Employee)
     */

    /**
     * select (
     *   select distinct Salary from Employee order by Salary Desc limit 1 offset 1
     * )as second
     */

    /**
     * Select MAX(Salary) from Employee
     * where Salary < (Select MAX(Salary) from Employee)
     */
}