package com.university;

import java.util.ArrayList;

/**
 * Represents a company that stores employees and their quantities.
 */
public class Company {

    private String name;
    private ArrayList<Employee> employees;
    private ArrayList<Integer> quantities;

    /**
     * Creates a new company.
     *
     * @param name company name
     */
    public Company(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Company name must not be null or blank"
            );
        }

        this.name = name;
        this.employees = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    /**
     * Returns company name.
     *
     * @return company name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of employees.
     *
     * @return employees
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Returns the quantity of a specific employee.
     *
     * @param employee employee
     * @return quantity
     */
    public int getQuantity(Employee employee) {
        int index = employees.indexOf(employee);

        if (index == -1) {
            return 0;
        }

        return quantities.get(index);
    }

    /**
     * Adds an employee to the company.
     * If the employee already exists, its quantity is increased.
     *
     * @param employee employee to add
     * @param quantity quantity to add
     */
    public void addNewEmployee(Employee employee, int quantity) {
        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee must not be null"
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be positive"
            );
        }

        int index = employees.indexOf(employee);

        if (index >= 0) {
            int currentQuantity = quantities.get(index);
            quantities.set(index, currentQuantity + quantity);
        } else {
            employees.add(employee);
            quantities.add(quantity);
        }
    }

    /**
     * Searches employees by position.
     *
     * @param position employee position
     * @return list of matching employees
     */
    public ArrayList<Employee> findByPosition(Position position) {
        return EmployeeSearcher.findByPosition(employees, position);
    }

    /**
     * Searches employees by minimum experience.
     *
     * @param minExperience minimum experience
     * @return list of matching employees
     */
    public ArrayList<Employee> findByMinExperience(int minExperience) {
        return EmployeeSearcher.findByMinExperience(
                employees,
                minExperience
        );
    }

    /**
     * Searches employees by salary range.
     *
     * @param minSalary minimum salary
     * @param maxSalary maximum salary
     * @return list of matching employees
     */
    public ArrayList<Employee> findBySalaryRange(
            double minSalary, double maxSalary) {

        return EmployeeSearcher.findBySalaryRange(
                employees,
                minSalary,
                maxSalary
        );
    }

    /**
     * Searches employees by name fragment.
     *
     * @param fragment part of employee name
     * @return list of matching employees
     */
    public ArrayList<Employee> findByName(String fragment) {
        return EmployeeSearcher.findByName(employees, fragment);
    }

    /**
     * Returns information about the company.
     *
     * @return company information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Company{name='")
                .append(name)
                .append("', employees=")
                .append(employees.size())
                .append("}\n");

        for (int i = 0; i < employees.size(); i++) {
            result.append("Employee: ")
                    .append(employees.get(i))
                    .append(", quantity=")
                    .append(quantities.get(i))
                    .append("\n");
        }

        return result.toString();
    }
}