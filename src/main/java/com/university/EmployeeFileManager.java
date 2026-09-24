package com.university;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class EmployeeFileManager {

    public static ArrayList<Employee> loadFromFile(String fileName) {
        ArrayList<Employee> employees = new ArrayList<>();
        Path path = Path.of(fileName);

        if (!Files.exists(path)) {
            System.out.println("Файл " + fileName
                    + " не знайдено. Колекція буде порожньою.");
            return employees;
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
                    Employee employee = parseEmployee(line);
                    employees.add(employee);
                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Помилка у рядку " + lineNumber
                                    + ": " + e.getMessage()
                                    + ". Рядок пропущено."
                    );
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Помилка читання файлу: " + e.getMessage()
            );
        }

        return employees;
    }

    public static void saveToFile(
            ArrayList<Employee> employees,
            String fileName) {

        Path path = Path.of(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (Employee employee : employees) {
                writer.write(employeeToLine(employee));
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println(
                    "Помилка запису у файл: " + e.getMessage()
            );
        }
    }

    private static Employee parseEmployee(String line) {
        String[] parts = line.split("\\|", -1);

        if (parts.length == 0) {
            throw new IllegalArgumentException("порожній запис");
        }

        String type = parts[0];

        switch (type) {
            case "Employee":
                if (parts.length != 6) {
                    throw new IllegalArgumentException(
                            "для Employee потрібно 6 полів"
                    );
                }

                return new Employee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5]
                );

            case "ContractEmployee":
                if (parts.length != 7) {
                    throw new IllegalArgumentException(
                            "для ContractEmployee потрібно 7 полів"
                    );
                }

                return new ContractEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parseInt(parts[6], "тривалість контракту")
                );

            case "FullTimeEmployee":
                if (parts.length != 7) {
                    throw new IllegalArgumentException(
                            "для FullTimeEmployee потрібно 7 полів"
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
                            "для PartTimeEmployee потрібно 7 полів"
                    );
                }

                return new PartTimeEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parseInt(parts[6], "робочі години")
                );

            case "InternEmployee":
                if (parts.length != 8) {
                    throw new IllegalArgumentException(
                            "для InternEmployee потрібно 8 полів"
                    );
                }

                return new InternEmployee(
                        parts[1],
                        parsePosition(parts[2]),
                        parseDouble(parts[3], "зарплата"),
                        parseInt(parts[4], "стаж"),
                        parts[5],
                        parts[6],
                        parseInt(parts[7], "тривалість стажування")
                );

            default:
                throw new IllegalArgumentException(
                        "невідомий тип об'єкта: " + type
                );
        }
    }

    private static String employeeToLine(Employee employee) {

        if (employee instanceof InternEmployee) {
            InternEmployee intern = (InternEmployee) employee;

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
            PartTimeEmployee partTime = (PartTimeEmployee) employee;

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

    private static Position parsePosition(String value) {
        try {
            return Position.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "невідома посада: " + value
            );
        }
    }

    private static int parseInt(String value, String field) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "некоректне числове значення для поля "
                            + field + ": " + value
            );
        }
    }

    private static double parseDouble(String value, String field) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "некоректне числове значення для поля "
                            + field + ": " + value
            );
        }
    }
}