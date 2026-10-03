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
    /**
     * Updates an existing employee with data from another employee.
     * The existing employee keeps its UUID and quantity.
     *
     * @param existingObject employee to update
     * @param newObject new employee data
     * @return true if the employee was updated, false if it was not found
     */
public boolean update(Employee existingObject, Employee newObject) {
    if (existingObject == null || newObject == null) {
        return false;
    }

    int index = employees.indexOf(existingObject);

    if (index == -1) {
        return false;
    }

    Employee employee = employees.get(index);

    employee.setName(newObject.getName());
    employee.setPosition(newObject.getPosition());
    employee.setSalary(newObject.getSalary());
    employee.setExperienceYears(newObject.getExperienceYears());
    employee.setEmail(newObject.getEmail());

    if (employee instanceof ContractEmployee
            && newObject instanceof ContractEmployee) {
        ContractEmployee current = (ContractEmployee) employee;
        ContractEmployee updated = (ContractEmployee) newObject;
        current.setContractMonths(updated.getContractMonths());
    } else if (employee instanceof FullTimeEmployee
            && newObject instanceof FullTimeEmployee) {
        FullTimeEmployee current = (FullTimeEmployee) employee;
        FullTimeEmployee updated = (FullTimeEmployee) newObject;
        current.setBonus(updated.getBonus());
    } else if (employee instanceof PartTimeEmployee
            && newObject instanceof PartTimeEmployee) {
        PartTimeEmployee current = (PartTimeEmployee) employee;
        PartTimeEmployee updated = (PartTimeEmployee) newObject;
        current.setWeeklyHours(updated.getWeeklyHours());
    } else if (employee instanceof InternEmployee
            && newObject instanceof InternEmployee) {
        InternEmployee current = (InternEmployee) employee;
        InternEmployee updated = (InternEmployee) newObject;
        current.setEducationalInstitution(
                updated.getEducationalInstitution()
        );
        current.setInternshipMonths(updated.getInternshipMonths());
    }

    return true;
}

    /**
     * Deletes an existing employee from the company.
     * The corresponding quantity is deleted as well.
     *
     * @param existingObject employee to delete
     * @return true if the employee was deleted, false if it was not found
     */
    public boolean delete(Employee existingObject) {
        if (existingObject == null) {
            return false;
        }

        int index = employees.indexOf(existingObject);

        if (index == -1) {
            return false;
        }

        employees.remove(index);
        quantities.remove(index);

        return true;
    }
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



