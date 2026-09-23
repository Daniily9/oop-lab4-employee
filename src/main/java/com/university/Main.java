package com.university;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console driver for managing Employee objects.
 * Demonstrates enum, copy constructor, static members and aggregation.
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

        System.out.println("Практична робота №6");
        System.out.println("Класи, статичні члени, агрегація, enum");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createEmployee();
                case "2" -> printAllEmployees();
                case "3" -> demonstrateFeatures();
                case "4" -> {
                    System.out.println("Завершення роботи.");
                    running = false;
                }
                default -> System.out.println(
                        "Невірний вибір. Введіть 1, 2, 3 або 4."
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
        System.out.println("1. Створити новий об'єкт");
        System.out.println("2. Вивести інформацію про всі об'єкти");
        System.out.println("3. Продемонструвати можливості №6");
        System.out.println("4. Завершити роботу");
        System.out.print("Ваш вибір: ");
    }

    /**
     * Creates a new Employee from keyboard input.
     */
    private static void createEmployee() {
        try {
            String name = readNonBlankLine("Ім'я: ");
            Position position = readPosition();
            double salary = readPositiveDouble("Зарплата: ");
            int experience = readNonNegativeInt("Стаж (років): ");
            String email = readNonBlankLine("Email: ");

            Employee employee = new Employee(
                    name,
                    position,
                    salary,
                    experience,
                    email
            );

            employees.add(employee);

            System.out.println("Співробітника додано: " + employee);
            System.out.println(
                    "Всього створено Employee: "
                            + Employee.getEmployeeCount()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Помилка: " + e.getMessage() + ". Спробуйте ще раз."
            );
        }
    }

    /**
     * Demonstrates copy constructor, static counter and aggregation.
     */
    private static void demonstrateFeatures() {
        System.out.println("\n--- Демонстрація можливостей №6 ---");

        if (employees.isEmpty()) {
            System.out.println(
                    "Спочатку створіть хоча б одного співробітника."
            );
            return;
        }

        Employee original = employees.get(0);

        Employee copy = new Employee(original);
        employees.add(copy);

        System.out.println("Оригінальний об'єкт:");
        System.out.println(original);

        System.out.println("\nКопія через copy constructor:");
        System.out.println(copy);

        System.out.println(
                "\nКількість створених Employee: "
                        + Employee.getEmployeeCount()
        );

        Department department = new Department("IT Department");
        department.addEmployee(original);
        department.addEmployee(copy);

        System.out.println("\nАгрегація:");
        System.out.println(department);

        System.out.println("Співробітники відділу:");

        for (Employee employee : department.getEmployees()) {
            System.out.println(employee);
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

        if (value == null || value.isBlank()) {
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
                    "Зарплата повинна бути додатною"
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
     * Prints all employees stored in the list.
     */
    private static void printAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }

        System.out.println("\n--- Список співробітників ---");

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}