package com.university;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console driver for managing different types of employees.
 * Demonstrates inheritance, polymorphism, enum and ArrayList.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Employee> employees = new ArrayList<>();

    /**
     * Starts the console application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Практична робота №7");
        System.out.println("Наслідування, поліморфізм, колекції ArrayList");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createEmployee();
                case "2" -> createContractEmployee();
                case "3" -> createFullTimeEmployee();
                case "4" -> printAllEmployees();
                case "5" -> demonstratePolymorphism();
                case "6" -> {
                    System.out.println("Завершення роботи.");
                    running = false;
                }
                default -> System.out.println(
                        "Невірний вибір. Введіть число від 1 до 6."
                );
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu.
     */
    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Створити Employee");
        System.out.println("2. Створити ContractEmployee");
        System.out.println("3. Створити FullTimeEmployee");
        System.out.println("4. Вивести всі об'єкти");
        System.out.println("5. Продемонструвати поліморфізм");
        System.out.println("6. Завершити роботу");
        System.out.print("Ваш вибір: ");
    }

    /**
     * Creates a base Employee object from keyboard input.
     */
    private static void createEmployee() {
        try {
            Employee employee = new Employee(
                    readNonBlankLine("Ім'я: "),
                    readPosition(),
                    readPositiveDouble("Зарплата: "),
                    readNonNegativeInt("Стаж (років): "),
                    readNonBlankLine("Email: ")
            );

            employees.add(employee);

            System.out.println("Employee додано:");
            System.out.println(employee);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Помилка: " + e.getMessage()
            );
        }
    }

    /**
     * Creates a ContractEmployee object from keyboard input.
     */
    private static void createContractEmployee() {
        try {
            ContractEmployee employee = new ContractEmployee(
                    readNonBlankLine("Ім'я: "),
                    readPosition(),
                    readPositiveDouble("Зарплата: "),
                    readNonNegativeInt("Стаж (років): "),
                    readNonBlankLine("Email: "),
                    readPositiveInt("Тривалість контракту (місяців): ")
            );

            employees.add(employee);

            System.out.println("ContractEmployee додано:");
            System.out.println(employee);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Помилка: " + e.getMessage()
            );
        }
    }

    /**
     * Creates a FullTimeEmployee object from keyboard input.
     */
    private static void createFullTimeEmployee() {
        try {
            FullTimeEmployee employee = new FullTimeEmployee(
                    readNonBlankLine("Ім'я: "),
                    readPosition(),
                    readPositiveDouble("Зарплата: "),
                    readNonNegativeInt("Стаж (років): "),
                    readNonBlankLine("Email: "),
                    readNonNegativeDouble("Бонус: ")
            );

            employees.add(employee);

            System.out.println("FullTimeEmployee додано:");
            System.out.println(employee);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Помилка: " + e.getMessage()
            );
        }
    }

    /**
     * Demonstrates polymorphism by processing different employee
     * objects through the base Employee type.
     */
    private static void demonstratePolymorphism() {
        if (employees.isEmpty()) {
            System.out.println(
                    "Спочатку створіть хоча б одного співробітника."
            );
            return;
        }

        System.out.println("\n--- Демонстрація поліморфізму ---");

        for (Employee employee : employees) {
            System.out.println(
                    "Тип: " + employee.getClass().getSimpleName()
            );
            System.out.println(
                    "Об'єкт зберігається як Employee: "
                            + employee
            );
            System.out.println();
        }
    }

    /**
     * Reads a non-blank string from the keyboard.
     *
     * @param prompt text displayed before input
     * @return entered string
     * @throws IllegalArgumentException if input is blank
     */
    private static String readNonBlankLine(String prompt) {
        System.out.print(prompt);

        String value = scanner.nextLine();

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    "Поле не може бути порожнім"
            );
        }

        return value;
    }

    /**
     * Reads an employee position from the keyboard.
     *
     * @return selected Position value
     * @throws IllegalArgumentException if the entered position is invalid
     */
    private static Position readPosition() {
        System.out.println("Доступні посади:");

        Position[] positions = Position.values();

        for (int i = 0; i < positions.length; i++) {
            System.out.println(
                    (i + 1) + ". " + positions[i]
            );
        }

        System.out.print("Оберіть посаду: ");

        String input = scanner.nextLine().trim();

        int number;

        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Потрібно ввести номер посади"
            );
        }

        if (number < 1 || number > positions.length) {
            throw new IllegalArgumentException(
                    "Такої посади немає"
            );
        }

        return positions[number - 1];
    }

    /**
     * Reads a positive double value.
     *
     * @param prompt text displayed before input
     * @return positive double
     * @throws IllegalArgumentException if input is invalid
     */
    private static double readPositiveDouble(String prompt) {
        System.out.print(prompt);

        String input = scanner.nextLine().trim();

        double value;

        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Некоректне числове значення: " + input
            );
        }

        if (value <= 0 || !Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Значення повинно бути додатним"
            );
        }

        return value;
    }

    /**
     * Reads a non-negative double value.
     *
     * @param prompt text displayed before input
     * @return non-negative double
     * @throws IllegalArgumentException if input is invalid
     */
    private static double readNonNegativeDouble(String prompt) {
        System.out.print(prompt);

        String input = scanner.nextLine().trim();

        double value;

        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Некоректне числове значення: " + input
            );
        }

        if (value < 0 || !Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Значення не може бути від'ємним"
            );
        }

        return value;
    }

    /**
     * Reads a non-negative integer.
     *
     * @param prompt text displayed before input
     * @return non-negative integer
     * @throws IllegalArgumentException if input is invalid
     */
    private static int readNonNegativeInt(String prompt) {
        System.out.print(prompt);

        String input = scanner.nextLine().trim();

        int value;

        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Некоректне ціле значення: " + input
            );
        }

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Стаж не може бути від'ємним"
            );
        }

        return value;
    }

    /**
     * Reads a positive integer.
     *
     * @param prompt text displayed before input
     * @return positive integer
     * @throws IllegalArgumentException if input is invalid
     */
    private static int readPositiveInt(String prompt) {
        System.out.print(prompt);

        String input = scanner.nextLine().trim();

        int value;

        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Некоректне ціле значення: " + input
            );
        }

        if (value <= 0) {
            throw new IllegalArgumentException(
                    "Значення повинно бути додатним"
            );
        }

        return value;
    }

    /**
     * Prints all employees stored in the ArrayList.
     */
    private static void printAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }

        System.out.println("\n--- Список співробітників ---");

        for (Employee employee : employees) {
            System.out.println(
                    "Тип: " + employee.getClass().getSimpleName()
            );
            System.out.println(employee);
            System.out.println();
        }
    }
}