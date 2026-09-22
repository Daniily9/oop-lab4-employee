package com.university;

import java.util.Objects;

/**
 * Represents an employee with basic personal and job-related information.
 * All fields are validated on construction and on every setter call.
 */
public class Employee {
    private String name;
    private String position;
    private double salary;
    private int experienceYears;
    private String email;

    /**
     * Creates a new Employee with validated fields.
     *
     * @param name            employee's full name, must not be null or blank
     * @param position        job position, must not be null or blank
     * @param salary          salary, must be positive
     * @param experienceYears years of experience, must not be negative
     * @param email           email address, must contain '@'
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Employee(String name, String position, double salary, int experienceYears, String email) {
        setName(name);
        setPosition(position);
        setSalary(salary);
        setExperienceYears(experienceYears);
        setEmail(email);
    }

    public String getName() { return name; }

    /**
     * @param name employee's full name, must not be null or blank
     * @throws IllegalArgumentException if name is null or blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        this.name = name;
    }

    public String getPosition() { return position; }

    /**
     * @param position job position, must not be null or blank
     * @throws IllegalArgumentException if position is null or blank
     */
    public void setPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position must not be null or blank");
        }
        this.position = position;
    }

    public double getSalary() { return salary; }

    /**
     * @param salary salary amount, must be positive
     * @throws IllegalArgumentException if salary is zero or negative
     */
    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be positive");
        }
        this.salary = salary;
    }

    public int getExperienceYears() { return experienceYears; }

    /**
     * @param experienceYears years of experience, must not be negative
     * @throws IllegalArgumentException if experienceYears is negative
     */
    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience years must not be negative");
        }
        this.experienceYears = experienceYears;
    }

    public String getEmail() { return email; }

    /**
     * @param email email address, must not be null/blank and must contain '@'
     * @throws IllegalArgumentException if email is invalid
     */
    public void setEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email must be a valid, non-blank address containing '@'");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', position='" + position +
                "', salary=" + salary + ", experienceYears=" + experienceYears +
                ", email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 &&
                experienceYears == employee.experienceYears &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, salary, experienceYears, email);
    }
}