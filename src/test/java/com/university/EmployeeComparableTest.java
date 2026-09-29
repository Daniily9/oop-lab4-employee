package com.university;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests for Employee Comparable implementation.
 */
class EmployeeComparableTest {

    @Test
    void shouldSortEmployeesByName() {
        List<Employee> employees = new ArrayList<>();

        Employee first = new FullTimeEmployee(
                "Max",
                Position.MANAGER,
                45000,
                5,
                "max@example.com",
                0.0
        );

        Employee second = new FullTimeEmployee(
                "Anna",
                Position.DESIGNER,
                30000,
                3,
                "anna@example.com",
                0.0
        );

        Employee third = new FullTimeEmployee(
                "Ivan",
                Position.DEVELOPER,
                40000,
                4,
                "ivan@example.com",
                0.0
        );

        employees.add(first);
        employees.add(second);
        employees.add(third);

        Collections.sort(employees);

        assertSame(second, employees.get(0));
        assertSame(third, employees.get(1));
        assertSame(first, employees.get(2));
    }

    @Test
    void shouldKeepEmptyListEmptyAfterSorting() {
        List<Employee> employees = new ArrayList<>();

        Collections.sort(employees);

        assertEquals(0, employees.size());
    }

    @Test
    void shouldKeepSingleEmployeeAfterSorting() {
        List<Employee> employees = new ArrayList<>();

        Employee employee = new FullTimeEmployee(
                "Ivan",
                Position.DEVELOPER,
                40000,
                4,
                "ivan@example.com",
                0.0
        );

        employees.add(employee);

        Collections.sort(employees);

        assertEquals(1, employees.size());
        assertSame(employee, employees.get(0));
    }

    @Test
    void shouldCompareEmployeesByName() {
        Employee anna = new FullTimeEmployee(
                "Anna",
                Position.DESIGNER,
                30000,
                3,
                "anna@example.com",
                0.0
        );

        Employee max = new FullTimeEmployee(
                "Max",
                Position.MANAGER,
                45000,
                5,
                "max@example.com",
                0.0
        );

        assertEquals(0, anna.compareTo(anna));
        assertEquals(0, max.compareTo(max));
        assertEquals(-1, Integer.signum(anna.compareTo(max)));
        assertEquals(1, Integer.signum(max.compareTo(anna)));
    }
}
