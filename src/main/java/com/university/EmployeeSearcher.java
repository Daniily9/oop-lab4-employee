package com.university;

import java.util.ArrayList;

public class EmployeeSearcher {

    private EmployeeSearcher() {
    }

    public static ArrayList<Employee> findByPosition(
            ArrayList<Employee> employees, Position position) {
        ArrayList<Employee> result = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getPosition() == position) {
                result.add(employee);
            }
        }

        return result;
    }

    public static ArrayList<Employee> findByMinExperience(
            ArrayList<Employee> employees, int minExperience) {
        ArrayList<Employee> result = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getExperienceYears() >= minExperience) {
                result.add(employee);
            }
        }

        return result;
    }

    public static ArrayList<Employee> findBySalaryRange(
            ArrayList<Employee> employees, double minSalary, double maxSalary) {
        ArrayList<Employee> result = new ArrayList<>();

        for (Employee employee : employees) {
            double salary = employee.getSalary();
            if (salary >= minSalary && salary <= maxSalary) {
                result.add(employee);
            }
        }

        return result;
    }

    public static ArrayList<Employee> findByName(
            ArrayList<Employee> employees, String fragment) {
        ArrayList<Employee> result = new ArrayList<>();
        String lowerFragment = fragment.toLowerCase();

        for (Employee employee : employees) {
            if (employee.getName().toLowerCase().contains(lowerFragment)) {
                result.add(employee);
            }
        }

        return result;
    }
}
