package com.university;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Provides loading and saving of employees from/to a file.
 */
public class CompanyFileManager {

    private CompanyFileManager() {
    }

    /**
     * Loads employees from a file.
     *
     * File format:
     * Employee|name|position|salary|experience|email
     * ContractEmployee|name|position|salary|experience|email|contractMonths
     * FullTimeEmployee|name|position|salary|experience|email|bonus
     * PartTimeEmployee|name|position|salary|experience|email|weeklyHours
     * InternEmployee|name|position|salary|experience|email|institution|internshipMonths
     *
     * @param fileName file name
     * @return company with loaded employees
     */
    public static Company loadFromFile(String fileName) {

        Company company = new Company("University Company");

        Path path = Path.of(fileName);

        if (!Files.exists(path)) {
            System.out.println(
                    "Файл " + fileName
                            + " не знайдено. Створено порожню компанію."
            );

            return company;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {

                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                try {

                    String[] parts = line.split("\\|", -1);

                    Employee employee = parseEmployee(parts);

                    company.addNewEmployee(employee, 1);

                } catch (IllegalArgumentException e) {

                    System.out.println(
                            "Помилка у рядку "
                                    + lineNumber
                                    + ": "
                                    + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Помилка читання файлу: "
                            + e.getMessage()
            );
        }

        return company;
    }

    /**
     * Saves all employees to a file.
     *
     * @param company company
     * @param fileName file name
     */
    public static void saveToFile(
            Company company,
            String fileName) {

        if (company == null) {
            System.out.println(
                    "Помилка: компанія не може бути null."
            );
            return;
        }

        Path path = Path.of(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (Employee employee : company.getEmployees()) {

                writer.write(employeeToLine(employee));
                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Помилка запису у файл: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Creates an Employee object from one file line.
     *
     * @param parts split line
     * @return employee
     */
    private static Employee parseEmployee(String[] parts) {

        String type = parts[0];

        switch (type) {

            case "Employee":
                throw new IllegalArgumentException(
                    "Тип Employee є абстрактним і не може бути створений."
                );

            case "ContractEmployee":

                if (parts.length != 7) {
                    throw new IllegalArgumentException(
                            "Для ContractEmployee потрібно 7 полів."
                    );
                }

                return new ContractEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parseInt(
                                parts[6],
                                "тривалість контракту"
                        )
                );

            case "FullTimeEmployee":

                if (parts.length != 7) {
                    throw new IllegalArgumentException(
                            "Для FullTimeEmployee потрібно 7 полів."
                    );
                }

                return new FullTimeEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parseDouble(parts[6], "бонус")
                );

            case "PartTimeEmployee":

                if (parts.length != 7) {
                    throw new IllegalArgumentException(
                            "Для PartTimeEmployee потрібно 7 полів."
                    );
                }

                return new PartTimeEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parseInt(
                                parts[6],
                                "робочі години"
                        )
                );

            case "InternEmployee":

                if (parts.length != 8) {
                    throw new IllegalArgumentException(
                            "Для InternEmployee потрібно 8 полів."
                    );
                }

                return new InternEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parts[6],
                        parseInt(
                                parts[7],
                                "тривалість стажування"
                        )
                );

            default:

                throw new IllegalArgumentException(
                        "Невідомий тип працівника: "
                                + type
                );
        }
    }

    /**
     * Converts an employee to one line of the file.
     *
     * @param employee employee
     * @return text representation
     */
    private static String employeeToLine(Employee employee) {

        if (employee instanceof InternEmployee) {

            InternEmployee intern =
                    (InternEmployee) employee;

            return "InternEmployee|"
                    + intern.getName() + "|"
                    + intern.getPosition() + "|"
                    + intern.getSalary() + "|"
                    + intern.getExperienceYears() + "|"
                    + intern.getEmail() + "|"
                    + intern.getEducationalInstitution() + "|"
                    + intern.getInternshipMonths();
        }

        if (employee instanceof PartTimeEmployee) {

            PartTimeEmployee partTime =
                    (PartTimeEmployee) employee;

            return "PartTimeEmployee|"
                    + partTime.getName() + "|"
                    + partTime.getPosition() + "|"
                    + partTime.getSalary() + "|"
                    + partTime.getExperienceYears() + "|"
                    + partTime.getEmail() + "|"
                    + partTime.getWeeklyHours();
        }

        if (employee instanceof FullTimeEmployee) {

            FullTimeEmployee fullTime =
                    (FullTimeEmployee) employee;

            return "FullTimeEmployee|"
                    + fullTime.getName() + "|"
                    + fullTime.getPosition() + "|"
                    + fullTime.getSalary() + "|"
                    + fullTime.getExperienceYears() + "|"
                    + fullTime.getEmail() + "|"
                    + fullTime.getBonus();
        }

        if (employee instanceof ContractEmployee) {

            ContractEmployee contract =
                    (ContractEmployee) employee;

            return "ContractEmployee|"
                    + contract.getName() + "|"
                    + contract.getPosition() + "|"
                    + contract.getSalary() + "|"
                    + contract.getExperienceYears() + "|"
                    + contract.getEmail() + "|"
                    + contract.getContractMonths();
        }

        return "Employee|"
                + employee.getName() + "|"
                + employee.getPosition() + "|"
                + employee.getSalary() + "|"
                + employee.getExperienceYears() + "|"
                + employee.getEmail();
    }

    /**
     * Converts text to Position.
     *
     * @param value position text
     * @return position
     */
    private static Position parsePosition(String value) {

        try {
            return Position.valueOf(value);

        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                    "Невідома посада: " + value
            );
        }
    }

    /**
     * Converts text to integer.
     *
     * @param value text
     * @param field field name
     * @return integer
     */
    private static int parseInt(
            String value,
            String field) {

        try {
            return Integer.parseInt(value);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "Некоректне число для поля "
                            + field
                            + ": "
                            + value
            );
        }
    }

    /**
     * Converts text to double.
     *
     * @param value text
     * @param field field name
     * @return double
     */
    private static double parseDouble(
            String value,
            String field) {

        try {
            return Double.parseDouble(value);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "Некоректне число для поля "
                            + field
                            + ": "
                            + value
            );
        }
    }
}