package com.university;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department that aggregates Employee objects.
 * Employees can exist independently of the department.
 */
public class Department {

    private final String name;
    private final List<Employee> employees;

    /**
     * Creates an empty department.
     *
     * @param name department name, must not be null or blank
     * @throws IllegalArgumentException if the name is invalid
     */
    public Department(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Department name must not be null or blank"
            );
        }

        this.name = name;
        this.employees = new ArrayList<>();
    }

    /**
     * Returns the department name.
     *
     * @return department name
     */
    public String getName() {
        return name;
    }

    /**
     * Adds an existing employee to the department.
     *
     * @param employee employee to add
     * @throws IllegalArgumentException if employee is null
     */
    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee must not be null"
            );
        }

        employees.add(employee);
    }

    /**
     * Returns the number of employees in the department.
     *
     * @return number of employees
     */
    public int getEmployeeCount() {
        return employees.size();
    }

    /**
     * Returns all employees in the department.
     *
     * @return list of employees
     */
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    /**
     * Returns a textual representation of the department.
     *
     * @return department information
     */
    @Override
    public String toString() {
        return "Department{name='" + name +
                "', employeeCount=" + employees.size() + "}";
    }
}