package com.university;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void updateExistingEmployeeShouldChangeEmployeeData() {
        Company company = new Company("Test Company");

        Employee employee = new FullTimeEmployee(
                "Old Name",
                Position.DEVELOPER,
                3000.0,
                2,
                "old@example.com",
                500.0
        );

        company.addNewEmployee(employee, 1);

        Employee newData = new FullTimeEmployee(
                "New Name",
                Position.MANAGER,
                4500.0,
                5,
                "new@example.com",
                800.0
        );

        UUID uuidBeforeUpdate = employee.getUuid();

        boolean result = company.update(employee, newData);

        assertTrue(result);
        assertEquals("New Name", employee.getName());
        assertEquals(Position.MANAGER, employee.getPosition());
        assertEquals(4500.0, employee.getSalary());
        assertEquals(5, employee.getExperienceYears());
        assertEquals("new@example.com", employee.getEmail());
        assertEquals(uuidBeforeUpdate, employee.getUuid());
        assertEquals(1, company.getQuantity(employee));
    }

    @Test
    void updateNonExistingEmployeeShouldReturnFalse() {
        Company company = new Company("Test Company");

        Employee existingEmployee = new FullTimeEmployee(
                "Existing",
                Position.DEVELOPER,
                3000.0,
                2,
                "existing@example.com",
                500.0
        );

        Employee employeeToUpdate = new FullTimeEmployee(
                "Missing",
                Position.MANAGER,
                4500.0,
                5,
                "missing@example.com",
                800.0
        );

        company.addNewEmployee(existingEmployee, 1);

        assertThrows(
                ObjectNotFoundException.class,
                () -> company.update(employeeToUpdate, existingEmployee)
        );
    }

    @Test
    void updateWithNullArgumentsShouldReturnFalse() {
        Company company = new Company("Test Company");

        Employee employee = new FullTimeEmployee(
                "Employee",
                Position.DEVELOPER,
                3000.0,
                2,
                "employee@example.com",
                500.0
        );

        company.addNewEmployee(employee, 1);

        assertFalse(company.update(null, employee));
        assertFalse(company.update(employee, null));
    }
    @Test
    void deleteExistingEmployeeShouldRemoveEmployeeAndQuantity() {
        Company company = new Company("Test Company");

        Employee employee = new FullTimeEmployee(
                "Employee",
                Position.DEVELOPER,
                3000.0,
                2,
                "employee@example.com",
                500.0
        );

        company.addNewEmployee(employee, 3);

        boolean result = company.delete(employee);

        assertTrue(result);
        assertTrue(company.getEmployees().isEmpty());
        assertEquals(0, company.getQuantity(employee));
    }

    @Test
    void deleteNonExistingEmployeeShouldReturnFalse() {
        Company company = new Company("Test Company");

        Employee existingEmployee = new FullTimeEmployee(
                "Existing",
                Position.DEVELOPER,
                3000.0,
                2,
                "existing@example.com",
                500.0
        );

        Employee missingEmployee = new FullTimeEmployee(
                "Missing",
                Position.MANAGER,
                4500.0,
                5,
                "missing@example.com",
                800.0
        );

        company.addNewEmployee(existingEmployee, 1);

        assertThrows(
                ObjectNotFoundException.class,
                () -> company.delete(missingEmployee)
        );
        assertEquals(1, company.getEmployees().size());
        assertEquals(1, company.getQuantity(existingEmployee));
    }

    @Test
    void deleteNullShouldReturnFalse() {
        Company company = new Company("Test Company");

        assertFalse(company.delete(null));
    }

}


