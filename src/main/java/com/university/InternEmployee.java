package com.university;

/**
 * Represents an intern employee.
 * Extends the base Employee class.
 */
public class InternEmployee extends Employee {

    private String educationalInstitution;
    private int internshipMonths;

    /**
     * Creates an intern employee.
     *
     * @param name employee's full name
     * @param position employee's job position
     * @param salary employee's salary
     * @param experienceYears years of experience
     * @param email employee's email address
     * @param educationalInstitution educational institution
     * @param internshipMonths internship duration in months
     * @throws IllegalArgumentException if institution is blank or
     * internshipMonths is not positive
     */
    public InternEmployee(String name, Position position, double salary,
                          int experienceYears, String email,
                          String educationalInstitution,
                          int internshipMonths) {
        super(name, position, salary, experienceYears, email);

        if (educationalInstitution == null
                || educationalInstitution.isBlank()) {
            throw new IllegalArgumentException(
                    "Educational institution must not be blank"
            );
        }

        if (internshipMonths <= 0) {
            throw new IllegalArgumentException(
                    "Internship duration must be positive"
            );
        }

        this.educationalInstitution = educationalInstitution;
        this.internshipMonths = internshipMonths;
    }

    /**
     * Returns the educational institution.
     *
     * @return educational institution
     */
    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    /**
     * Sets the educational institution.
     *
     * @param educationalInstitution educational institution
     * @throws IllegalArgumentException if institution is blank
     */
    public void setEducationalInstitution(String educationalInstitution) {
        if (educationalInstitution == null
                || educationalInstitution.isBlank()) {
            throw new IllegalArgumentException(
                    "Educational institution must not be blank"
            );
        }

        this.educationalInstitution = educationalInstitution;
    }

    /**
     * Returns the internship duration.
     *
     * @return internship duration in months
     */
    public int getInternshipMonths() {
        return internshipMonths;
    }

    /**
     * Sets the internship duration.
     *
     * @param internshipMonths internship duration in months
     * @throws IllegalArgumentException if internshipMonths is not positive
     */
    public void setInternshipMonths(int internshipMonths) {
        if (internshipMonths <= 0) {
            throw new IllegalArgumentException(
                    "Internship duration must be positive"
            );
        }

        this.internshipMonths = internshipMonths;
    }

    /**
     * Returns a textual representation of the intern employee.
     *
     * @return intern employee information
     */
    @Override
    public String toString() {
        return "InternEmployee{" +
                "name='" + getName() + '\'' +
                ", position=" + getPosition() +
                ", salary=" + getSalary() +
                ", experienceYears=" + getExperienceYears() +
                ", email='" + getEmail() + '\'' +
                ", educationalInstitution='" +
                educationalInstitution + '\'' +
                ", internshipMonths=" + internshipMonths +
                '}';
    }
}