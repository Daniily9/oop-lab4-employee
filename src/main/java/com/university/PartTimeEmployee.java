package com.university;

/**
 * Represents an employee who works part-time.
 * Extends the base Employee class.
 */
public class PartTimeEmployee extends Employee {

    private int weeklyHours;

    /**
     * Creates a part-time employee.
     *
     * @param name employee's full name
     * @param position employee's job position
     * @param salary employee's salary
     * @param experienceYears years of experience
     * @param email employee's email address
     * @param weeklyHours number of working hours per week
     * @throws IllegalArgumentException if weeklyHours is not positive
     */
    public PartTimeEmployee(String name, Position position, double salary,
                            int experienceYears, String email,
                            int weeklyHours) {
        super(name, position, salary, experienceYears, email);

        if (weeklyHours <= 0) {
            throw new IllegalArgumentException(
                    "Weekly hours must be positive"
            );
        }

        this.weeklyHours = weeklyHours;
    }

    /**
     * Returns the number of working hours per week.
     *
     * @return weekly working hours
     */
    public int getWeeklyHours() {
        return weeklyHours;
    }

    /**
     * Sets the number of working hours per week.
     *
     * @param weeklyHours number of working hours per week
     * @throws IllegalArgumentException if weeklyHours is not positive
     */
    public void setWeeklyHours(int weeklyHours) {
        if (weeklyHours <= 0) {
            throw new IllegalArgumentException(
                    "Weekly hours must be positive"
            );
        }

        this.weeklyHours = weeklyHours;
    }

    /**
     * Returns a textual representation of the part-time employee.
     *
     * @return part-time employee information
     */
    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "name='" + getName() + '\'' +
                ", position=" + getPosition() +
                ", salary=" + getSalary() +
                ", experienceYears=" + getExperienceYears() +
                ", email='" + getEmail() + '\'' +
                ", weeklyHours=" + weeklyHours +
                '}';
    }
}