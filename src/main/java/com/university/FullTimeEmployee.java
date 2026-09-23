package com.university;

/**
 * Represents an employee who works full-time.
 * Extends the base Employee class.
 */
public class FullTimeEmployee extends Employee {

    private double bonus;

    /**
     * Creates a full-time employee.
     *
     * @param name employee's full name
     * @param position employee's job position
     * @param salary employee's salary
     * @param experienceYears years of experience
     * @param email employee's email address
     * @param bonus employee's bonus
     * @throws IllegalArgumentException if bonus is negative or not finite
     */
    public FullTimeEmployee(String name, Position position, double salary,
                            int experienceYears, String email,
                            double bonus) {
        super(name, position, salary, experienceYears, email);

        if (bonus < 0 || !Double.isFinite(bonus)) {
            throw new IllegalArgumentException(
                    "Bonus must be non-negative and finite"
            );
        }

        this.bonus = bonus;
    }

    /**
     * Returns the employee's bonus.
     *
     * @return bonus amount
     */
    public double getBonus() {
        return bonus;
    }

    /**
     * Sets the employee's bonus.
     *
     * @param bonus bonus amount
     * @throws IllegalArgumentException if bonus is negative or not finite
     */
    public void setBonus(double bonus) {
        if (bonus < 0 || !Double.isFinite(bonus)) {
            throw new IllegalArgumentException(
                    "Bonus must be non-negative and finite"
            );
        }

        this.bonus = bonus;
    }

    /**
     * Returns a textual representation of the full-time employee.
     *
     * @return full-time employee information
     */
    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "name='" + getName() + '\'' +
                ", position=" + getPosition() +
                ", salary=" + getSalary() +
                ", experienceYears=" + getExperienceYears() +
                ", email='" + getEmail() + '\'' +
                ", bonus=" + bonus +
                '}';
    }
}