package com.wld.java.leetcode;

public class leet175 {

    public static void main(String args[]) {
        System.out.println();

    }

    /**
     * SELECT Person.FirstName, Person.LastName, Address.City, Address.State from Person
     * LEFT JOIN Address on Person.PersonId = Address.PersonId;
     */

    /**
     * SELECT FirstName, LastName, City, State
     * FROM Person
     * LEFT JOIN Address
     * ON Person.PersonId = Address.PersonId;
     */

    /**
     * SELECT FirstName, LastName, City, State
     * FROM Person
     * LEFT JOIN Address
     * USING(PersonId);
     */

    /**
     *SELECT FirstName, LastName, City, State
     * FROM Person
     * NATURAL LEFT JOIN Address;
     */

    /**
     * select p.FirstName, p.LastName, a.City, a. State
     * from Person p, Address a
     * where p.PersonId = a.PersonId;
     */

}