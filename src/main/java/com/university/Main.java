package com.university;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console driver for managing a list of {@link Employee} objects.
 * Provides a menu to create employees, list them, and exit.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createEmployee();
                case "2" -> printAllEmployees();
                case "3" -> {
                    System.out.println("Завершення роботи.");
                    running = false;
                }
                default -> System.out.println("Невірний вибір. Введіть 1, 2 або 3.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Створити новий об'єкт");
        System.out.println("2. Вивести інформацію про всі об'єкти");
        System.out.println("3. Завершити роботу");
        System.out.print("Ваш вибір: ");
    }

    /**
     * Reads employee data from console with validation,
     * retrying on invalid input instead of crashing.
     */
    private static void createEmployee() {
        try {
            String name = readNonBlankLine("Ім'я: ");
            String position = readNonBlankLine("Посада: ");
            double salary = readPositiveDouble("Зарплата: ");
            int experience = readNonNegativeInt("Стаж (років): ");
            String email = readNonBlankLine("Email: ");

            Employee employee = new Employee(name, position, salary, experience, email);
            employees.add(employee);
            System.out.println("Співробітника додано: " + employee);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage() + ". Спробуйте ще раз.");
        }
    }

    private static String readNonBlankLine(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Поле не може бути порожнім");
        }
        return value;
    }

    private static double readPositiveDouble(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некоректне числове значення: " + input);
        }
        return value;
    }

    private static int readNonNegativeInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        int value;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некоректне ціле значення: " + input);
        }
        return value;
    }

    private static void printAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        System.out.println("\n--- Список співробітників ---");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}