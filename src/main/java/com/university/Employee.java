package com.university;

import java.util.Objects;

/**
 * Represents an employee with personal and job-related information.
 * All fields are validated on construction and on every setter call.
 */
public class Employee {

    private static int employeeCount = 0;

    private String name;
    private Position position;
    private double salary;
    private int experienceYears;
    private String email;

    /**
     * Creates a new Employee with validated fields.
     *
     * @param name employee's full name, must not be null or blank
     * @param position employee's job position, must not be null
     * @param salary salary, must be positive
     * @param experienceYears years of experience, must not be negative
     * @param email email address, must contain '@'
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Employee(String name, Position position, double salary,
                    int experienceYears, String email) {
        setName(name);
        setPosition(position);
        setSalary(salary);
        setExperienceYears(experienceYears);
        setEmail(email);

        employeeCount++;
    }

    /**
     * Creates a copy of an existing Employee.
     *
     * @param other employee to copy
     * @throws IllegalArgumentException if the employee is null
     */
    public Employee(Employee other) {
        if (other == null) {
            throw new IllegalArgumentException("Employee to copy must not be null");
        }

        this.name = other.name;
        this.position = other.position;
        this.salary = other.salary;
        this.experienceYears = other.experienceYears;
        this.email = other.email;

        employeeCount++;
    }

    /**
     * Returns the total number of created Employee objects.
     *
     * @return number of created Employee objects
     */
    public static int getEmployeeCount() {
        return employeeCount;
    }

    /**
     * Returns the employee's full name.
     *
     * @return employee's full name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets employee's name.
     *
     * @param name employee's full name, must not be null or blank
     * @throws IllegalArgumentException if name is null or blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        this.name = name;
    }

    /**
     * Returns the employee's job position.
     *
     * @return employee's job position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets employee's position.
     *
     * @param position job position, must not be null
     * @throws IllegalArgumentException if position is null
     */
    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position must not be null");
        }
        this.position = position;
    }

    /**
     * Returns the employee's salary.
     *
     * @return employee's salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets employee's salary.
     *
     * @param salary salary amount, must be positive
     * @throws IllegalArgumentException if salary is zero, negative or not finite
     */
    public void setSalary(double salary) {
        if (salary <= 0 || !Double.isFinite(salary)) {
            throw new IllegalArgumentException("Salary must be positive and finite");
        }
        this.salary = salary;
    }

    /**
     * Returns the employee's years of experience.
     *
     * @return years of experience
     */
    public int getExperienceYears() {
        return experienceYears;
    }

    /**
     * Sets employee's years of experience.
     *
     * @param experienceYears years of experience, must not be negative
     * @throws IllegalArgumentException if experienceYears is negative
     */
    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException(
                    "Experience years must not be negative"
            );
        }
        this.experienceYears = experienceYears;
    }

    /**
     * Returns the employee's email address.
     *
     * @return employee's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets employee's email address.
     *
     * @param email email address, must not be null or blank and must contain '@'
     * @throws IllegalArgumentException if email is invalid
     */
    public void setEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(
                    "Email must be a valid, non-blank address containing '@'"
            );
        }
        this.email = email;
    }

    /**
     * Returns a textual representation of the employee.
     *
     * @return employee information
     */
    @Override
    public String toString() {
        return "Employee{name='" + name +
                "', position=" + position +
                ", salary=" + salary +
                ", experienceYears=" + experienceYears +
                ", email='" + email + "'}";
    }

    /**
     * Compares this employee with another object.
     *
     * @param o object to compare with
     * @return true if objects contain the same employee data
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }

        Employee employee = (Employee) o;

        return Double.compare(salary, employee.salary) == 0
                && experienceYears == employee.experienceYears
                && Objects.equals(name, employee.name)
                && position == employee.position
                && Objects.equals(email, employee.email);
    }

    /**
     * Returns hash code based on employee fields.
     *
     * @return hash code of the employee
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                position,
                salary,
                experienceYears,
                email
        );
    }
}