package com.university;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeComparatorTest {

    @Test
    void shouldSortEmployeesByName() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "Charlie",
                Position.DEVELOPER,
                30000,
                5,
                "charlie@example.com",
                1000
        ));

        employees.add(new FullTimeEmployee(
                "Alice",
                Position.TESTER,
                25000,
                3,
                "alice@example.com",
                800
        ));

        employees.add(new FullTimeEmployee(
                "Bob",
                Position.MANAGER,
                35000,
                7,
                "bob@example.com",
                1500
        ));

        Comparator<Employee> comparator = (first, second) -> first.getName().compareTo(second.getName());

        Collections.sort(employees, comparator);

        assertEquals("Alice", employees.get(0).getName());
        assertEquals("Bob", employees.get(1).getName());
        assertEquals("Charlie", employees.get(2).getName());
    }

    @Test
    void shouldSortEmployeesBySalary() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "Alice",
                Position.TESTER,
                35000,
                3,
                "alice@example.com",
                800
        ));

        employees.add(new FullTimeEmployee(
                "Bob",
                Position.MANAGER,
                25000,
                7,
                "bob@example.com",
                1500
        ));

        employees.add(new FullTimeEmployee(
                "Charlie",
                Position.DEVELOPER,
                30000,
                5,
                "charlie@example.com",
                1000
        ));

        Comparator<Employee> comparator = (first, second) -> Double.compare(first.getSalary(), second.getSalary());

        Collections.sort(employees, comparator);

        assertEquals(25000, employees.get(0).getSalary());
        assertEquals(30000, employees.get(1).getSalary());
        assertEquals(35000, employees.get(2).getSalary());
    }

    @Test
    void shouldSortEmployeesByExperience() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "Alice",
                Position.TESTER,
                30000,
                10,
                "alice@example.com",
                800
        ));

        employees.add(new FullTimeEmployee(
                "Bob",
                Position.MANAGER,
                35000,
                2,
                "bob@example.com",
                1500
        ));

        employees.add(new FullTimeEmployee(
                "Charlie",
                Position.DEVELOPER,
                28000,
                5,
                "charlie@example.com",
                1000
        ));

        Comparator<Employee> comparator = (first, second) -> Integer.compare(first.getExperienceYears(), second.getExperienceYears());

        Collections.sort(employees, comparator);

        assertEquals(2, employees.get(0).getExperienceYears());
        assertEquals(5, employees.get(1).getExperienceYears());
        assertEquals(10, employees.get(2).getExperienceYears());
    }

    @Test
    void shouldHandleEmptyList() {
        List<Employee> employees = new ArrayList<>();

        Comparator<Employee> comparator = (first, second) -> first.getName().compareTo(second.getName());

        Collections.sort(employees, comparator);

        assertTrue(employees.isEmpty());
    }

    @Test
    void shouldHandleSingleEmployee() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "Alice",
                Position.TESTER,
                25000,
                3,
                "alice@example.com",
                800
        ));

        Comparator<Employee> comparator = (first, second) -> first.getName().compareTo(second.getName());

        Collections.sort(employees, comparator);

        assertEquals(1, employees.size());
        assertEquals("Alice", employees.get(0).getName());
    }
    @Test
    void shouldHandleEqualValues() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "Alice",
                Position.TESTER,
                25000,
                5,
                "alice@example.com",
                800
        ));

        employees.add(new FullTimeEmployee(
                "Alice",
                Position.DEVELOPER,
                25000,
                5,
                "alice2@example.com",
                900
        ));

        Comparator<Employee> nameComparator =
                (first, second) -> first.getName().compareTo(second.getName());

        Comparator<Employee> salaryComparator =
                (first, second) -> Double.compare(
                        first.getSalary(),
                        second.getSalary()
                );

        Comparator<Employee> experienceComparator =
                (first, second) -> Integer.compare(
                        first.getExperienceYears(),
                        second.getExperienceYears()
                );

        Collections.sort(employees, nameComparator);
        assertEquals(0, nameComparator.compare(employees.get(0), employees.get(1)));

        Collections.sort(employees, salaryComparator);
        assertEquals(0, salaryComparator.compare(employees.get(0), employees.get(1)));

        Collections.sort(employees, experienceComparator);
        assertEquals(0, experienceComparator.compare(employees.get(0), employees.get(1)));
    }
}
