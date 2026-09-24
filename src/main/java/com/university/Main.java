package com.university;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "input.txt";

    public static void main(String[] args) {
        employees.addAll(
                EmployeeFileManager.loadFromFile(FILE_NAME)
        );

        System.out.println(
                "Завантажено об'єктів: " + employees.size()
        );

        while (true) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
            System.out.println("1. Створити новий об'єкт");
            System.out.println("2. Вивести інформацію про всі об'єкти");
            System.out.println("3. Завершити роботу");
            System.out.print("Оберіть пункт: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createObject();
                    break;

                case "2":
                    printAllObjects();
                    break;

                case "3":
                    EmployeeFileManager.saveToFile(employees, FILE_NAME);
                    System.out.println("Дані збережено у файл " + FILE_NAME + ".");
                    System.out.println("Роботу завершено.");
                    return;

                default:
                    System.out.println("Помилка: введіть число від 1 до 3.");
            }
        }
    }

    private static void createObject() {
        while (true) {
            System.out.println("\n=== СТВОРЕННЯ ОБ'ЄКТА ===");
            System.out.println("1. Employee");
            System.out.println("2. ContractEmployee");
            System.out.println("3. FullTimeEmployee");
            System.out.println("4. PartTimeEmployee");
            System.out.println("5. InternEmployee");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Оберіть тип: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    employees.add(createEmployee());
                    System.out.println("Об'єкт Employee успішно створено.");
                    return;

                case "2":
                    employees.add(createContractEmployee());
                    System.out.println("Об'єкт ContractEmployee успішно створено.");
                    return;

                case "3":
                    employees.add(createFullTimeEmployee());
                    System.out.println("Об'єкт FullTimeEmployee успішно створено.");
                    return;

                case "4":
                    employees.add(createPartTimeEmployee());
                    System.out.println("Об'єкт PartTimeEmployee успішно створено.");
                    return;

                case "5":
                    employees.add(createInternEmployee());
                    System.out.println("Об'єкт InternEmployee успішно створено.");
                    return;

                case "0":
                    return;

                default:
                    System.out.println("Помилка: введіть число від 0 до 5.");
            }
        }
    }

    private static Employee createEmployee() {
        System.out.println("\n--- Створення Employee ---");

        String name = readString("Ім'я: ");
        Position position = readPosition();
        double salary = readDouble("Зарплата: ");
        int experienceYears = readInt("Стаж (років): ");
        String email = readString("Email: ");

        return new Employee(
                name,
                position,
                salary,
                experienceYears,
                email
        );
    }

    private static ContractEmployee createContractEmployee() {
        System.out.println("\n--- Створення ContractEmployee ---");

        String name = readString("Ім'я: ");
        Position position = readPosition();
        double salary = readDouble("Зарплата: ");
        int experienceYears = readInt("Стаж (років): ");
        String email = readString("Email: ");
        int contractMonths = readInt("Тривалість контракту (місяців): ");

        return new ContractEmployee(
                name,
                position,
                salary,
                experienceYears,
                email,
                contractMonths
        );
    }

    private static FullTimeEmployee createFullTimeEmployee() {
        System.out.println("\n--- Створення FullTimeEmployee ---");

        String name = readString("Ім'я: ");
        Position position = readPosition();
        double salary = readDouble("Зарплата: ");
        int experienceYears = readInt("Стаж (років): ");
        String email = readString("Email: ");
        double bonus = readDouble("Бонус: ");

        return new FullTimeEmployee(
                name,
                position,
                salary,
                experienceYears,
                email,
                bonus
        );
    }

    private static PartTimeEmployee createPartTimeEmployee() {
        System.out.println("\n--- Створення PartTimeEmployee ---");

        String name = readString("Ім'я: ");
        Position position = readPosition();
        double salary = readDouble("Зарплата: ");
        int experienceYears = readInt("Стаж (років): ");
        String email = readString("Email: ");
        int weeklyHours = readInt("Робочі години на тиждень: ");

        return new PartTimeEmployee(
                name,
                position,
                salary,
                experienceYears,
                email,
                weeklyHours
        );
    }

    private static InternEmployee createInternEmployee() {
        System.out.println("\n--- Створення InternEmployee ---");

        String name = readString("Ім'я: ");
        Position position = readPosition();
        double salary = readDouble("Зарплата: ");
        int experienceYears = readInt("Стаж (років): ");
        String email = readString("Email: ");
        String educationalInstitution =
                readString("Навчальний заклад: ");
        int internshipMonths =
                readInt("Тривалість стажування (місяців): ");

        return new InternEmployee(
                name,
                position,
                salary,
                experienceYears,
                email,
                educationalInstitution,
                internshipMonths
        );
    }

    private static void printAllObjects() {
        System.out.println("\n=== УСІ ОБ'ЄКТИ ===");

        if (employees.isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println("Тип: "
                    + employee.getClass().getSimpleName());
            System.out.println(employee);
            System.out.println();
        }
    }

    private static String readString(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Помилка: поле не може бути порожнім.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть число.");
            }
        }
    }

    private static Position readPosition() {
        while (true) {
            System.out.println("Доступні посади:");

            Position[] positions = Position.values();

            for (int i = 0; i < positions.length; i++) {
                System.out.println(
                        (i + 1) + ". " + positions[i]
                );
            }

            System.out.print("Оберіть посаду: ");

            try {
                int choice =
                        Integer.parseInt(scanner.nextLine().trim());

                if (choice >= 1 && choice <= positions.length) {
                    return positions[choice - 1];
                }
            } catch (NumberFormatException ignored) {
                // Обробка некоректного введення нижче.
            }

            System.out.println("Помилка: оберіть доступну посаду.");
        }
    }
}
