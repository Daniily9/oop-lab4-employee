package com.university;

import java.util.Objects;

public class Employee {
    private String name;
    private String position;
    private double salary;
    private int experienceYears;

    public Employee(String name, String position, double salary, int experienceYears) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.experienceYears = experienceYears;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', position='" + position +
                "', salary=" + salary + ", experienceYears=" + experienceYears + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 &&
                experienceYears == employee.experienceYears &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, salary, experienceYears);
    }
}