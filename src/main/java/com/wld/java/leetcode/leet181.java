package com.wld.java.leetcode;

public class leet181 {

    public static void main(String args[]) {
        System.out.println();

    }

    /**
     * select E1.Name
     * from Employee as E1, Employee as E2
     * where E1.ManagerId = E2.Id and E1.Salary > E2.Salary
     */

    /**
     * Select emp.Name from
     * Employee emp inner join Employee manager
     * on emp.ManagerId = manager.Id
     * where emp.Salary > manager.Salary
     */
}