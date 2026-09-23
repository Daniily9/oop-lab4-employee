package com.university;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Employee} validation logic.
 */
class EmployeeTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Employee employee = new Employee(
            "Ivan Petrenko",
            Position.DEVELOPER,
            1000.0,
            3,
            "ivan@example.com"
        );

        assertThrows(IllegalArgumentException.class, () -> {
            employee.setSalary(-500.0);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee("", Position.DEVELOPER, 0.0, -1, "");
        });
    }

    @Test
    void shouldThrowExceptionWhenExperienceIsNegative() {
        Employee employee = new Employee(
            "Ivan Petrenko",
            Position.DEVELOPER,
            1000.0,
            3,
            "ivan@example.com"
        );

        assertThrows(IllegalArgumentException.class, () -> {
            employee.setExperienceYears(-1);
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        Employee employee = new Employee(
            "Ivan Petrenko",
            Position.DEVELOPER,
            1000.0,
            3,
            "ivan@example.com"
        );

        assertThrows(IllegalArgumentException.class, () -> {
            employee.setEmail("invalid-email");
        });
    }

    @Test
    void shouldCreateValidEmployeeWithoutException() {
        Employee employee = new Employee(
            "Ivan Petrenko",
            Position.DEVELOPER,
            1000.0,
            3,
            "ivan@example.com"
        );

        assertEquals("Ivan Petrenko", employee.getName());
        assertEquals(1000.0, employee.getSalary());
    }
}