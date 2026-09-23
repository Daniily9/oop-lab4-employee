package com.university;

/**
 * Represents an employee who works under a contract.
 * Extends the base Employee class.
 */
public class ContractEmployee extends Employee {

    private int contractMonths;

    /**
     * Creates a contract employee.
     *
     * @param name employee's full name
     * @param position employee's job position
     * @param salary employee's salary
     * @param experienceYears years of experience
     * @param email employee's email address
     * @param contractMonths contract duration in months
     * @throws IllegalArgumentException if contractMonths is not positive
     */
    public ContractEmployee(String name, Position position, double salary,
                            int experienceYears, String email,
                            int contractMonths) {
        super(name, position, salary, experienceYears, email);

        if (contractMonths <= 0) {
            throw new IllegalArgumentException(
                    "Contract duration must be positive"
            );
        }

        this.contractMonths = contractMonths;
    }

    /**
     * Returns the contract duration.
     *
     * @return contract duration in months
     */
    public int getContractMonths() {
        return contractMonths;
    }

    /**
     * Sets the contract duration.
     *
     * @param contractMonths contract duration in months
     * @throws IllegalArgumentException if contractMonths is not positive
     */
    public void setContractMonths(int contractMonths) {
        if (contractMonths <= 0) {
            throw new IllegalArgumentException(
                    "Contract duration must be positive"
            );
        }

        this.contractMonths = contractMonths;
    }

    /**
     * Returns a textual representation of the contract employee.
     *
     * @return contract employee information
     */
    @Override
    public String toString() {
        return "ContractEmployee{" +
                "name='" + getName() + '\'' +
                ", position=" + getPosition() +
                ", salary=" + getSalary() +
                ", experienceYears=" + getExperienceYears() +
                ", email='" + getEmail() + '\'' +
                ", contractMonths=" + contractMonths +
                '}';
    }
}