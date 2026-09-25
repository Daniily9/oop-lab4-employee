package com.university;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeSearcherTest {

    private ArrayList<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employees.add(new Employee(
                "Іван Петров", Position.DEVELOPER, 30000, 5, "ivan@test.com"));
        employees.add(new Employee(
                "Олена Коваль", Position.MANAGER, 45000, 8, "olena@test.com"));
        employees.add(new Employee(
                "Іван Сидоренко", Position.DEVELOPER, 25000, 2, "ivan2@test.com"));
    }

    @Test
    void findByPosition_returnsMatchingEmployees() {
        ArrayList<Employee> result =
                EmployeeSearcher.findByPosition(employees, Position.DEVELOPER);

        assertEquals(2, result.size());
    }

    @Test
    void findByPosition_returnsEmptyListWhenNoMatch() {
        ArrayList<Employee> result =
                EmployeeSearcher.findByPosition(employees, Position.ANALYST);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByMinExperience_filtersCorrectly() {
        ArrayList<Employee> result =
                EmployeeSearcher.findByMinExperience(employees, 5);

        assertEquals(2, result.size());
    }

    @Test
    void findBySalaryRange_includesBoundaries() {
        ArrayList<Employee> result =
                EmployeeSearcher.findBySalaryRange(employees, 25000, 30000);

        assertEquals(2, result.size());
    }

    @Test
    void findBySalaryRange_returnsEmptyWhenOutOfRange() {
        ArrayList<Employee> result =
                EmployeeSearcher.findBySalaryRange(employees, 100000, 200000);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByName_isCaseInsensitiveAndPartial() {
        ArrayList<Employee> result =
                EmployeeSearcher.findByName(employees, "іван");

        assertEquals(2, result.size());
    }

    @Test
    void findByName_returnsEmptyWhenNoMatch() {
        ArrayList<Employee> result =
                EmployeeSearcher.findByName(employees, "Xenon");

        assertTrue(result.isEmpty());
    }

    @Test
    void originalCollectionIsNotModified() {
        int sizeBefore = employees.size();

        EmployeeSearcher.findByPosition(employees, Position.DEVELOPER);

        assertEquals(sizeBefore, employees.size());
    }
}
