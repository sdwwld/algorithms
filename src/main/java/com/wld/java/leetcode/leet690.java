package com.wld.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet690 {
    public static void main(String args[]) {
        leet690 mleet690 = new leet690();
        System.out.println(mleet690.getImportance(mleet690.getEmployees(), 3));
    }

    private List<Employee> getEmployees() {
        /**
         * [[4,99,[]],[3,79,[4,5]],[1,75,[2]],[5,89,[]],[2,99,[3]]]
         3
         */
        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1, 5, Arrays.asList(2, 3)));
//        employees.add(new Employee(2, 3, Arrays.asList(4)));
//        employees.add(new Employee(3, 4, new ArrayList<Integer>()));
//        employees.add(new Employee(4, 1, new ArrayList<Integer>()));
//        employees.add(new Employee(2, 99, Arrays.asList(3)));


        employees.add(new Employee(4, 99, new ArrayList<Integer>()));
        employees.add(new Employee(3, 79, Arrays.asList(4, 5)));
        employees.add(new Employee(1, 75, Arrays.asList(2)));
        employees.add(new Employee(5, 89, new ArrayList<Integer>()));
        employees.add(new Employee(2, 99, Arrays.asList(3)));

        return employees;
    }

//    public int getImportance(List<Employee> employees, int id) {
//        if (employees.isEmpty() || id < 1)
//            return 0;
//        if (employees.size() < id)
//            return employees.get(0).importance;
//        Employee employee = null;
//        for (int j = 0; j < employees.size(); j++) {
//            if (employees.get(j).id == id)
//                employee = employees.get(j);
//        }
//        int sum = employee.importance;
//        for (int i = 0; i < employee.subordinates.size(); i++) {
//            for (int j = 0; j < employees.size(); j++) {
//                if (employees.get(j).id == employee.subordinates.get(i)) {
//                    sum += getImportance(employees, employee.subordinates.get(i));
//                    break;
//                }
//            }
//        }
//        return sum;
//    }

    public int getImportance(List<Employee> employees, int id) {
        int result=0;
        for(int i=0;i<employees.size();i++){
            if(id==employees.get(i).id){
                result+=employees.get(i).importance;
                for(int j=0; j<employees.get(i).subordinates.size();j++){
                    result+=getImportance(employees,employees.get(i).subordinates.get(j));
                }
            }
        }
        return result;
    }

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;

        Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }
}
