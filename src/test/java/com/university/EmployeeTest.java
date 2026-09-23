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

    @Test
    void shouldCreatePartTimeEmployee() {
        PartTimeEmployee employee = new PartTimeEmployee(
                "Sofia",
                Position.DESIGNER,
                1800,
                2,
                "sofia@example.com",
                20
        );

        assertEquals("Sofia", employee.getName());
        assertEquals(Position.DESIGNER, employee.getPosition());
        assertEquals(1800, employee.getSalary());
        assertEquals(20, employee.getWeeklyHours());
    }

    @Test
    void shouldCreateInternEmployee() {
        InternEmployee employee = new InternEmployee(
                "Alex",
                Position.TESTER,
                1000,
                0,
                "alex@example.com",
                "KPI University",
                6
        );

        assertEquals("Alex", employee.getName());
        assertEquals(Position.TESTER, employee.getPosition());
        assertEquals(1000, employee.getSalary());
        assertEquals("KPI University",
                employee.getEducationalInstitution());
        assertEquals(6, employee.getInternshipMonths());
    }
}