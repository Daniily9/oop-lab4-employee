package com.university;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Введіть кількість співробітників: ");
        int count = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < count; i++) {
            System.out.println("Співробітник #" + (i + 1));

            System.out.print("Ім'я: ");
            String name = scanner.nextLine();

            System.out.print("Посада: ");
            String position = scanner.nextLine();

            System.out.print("Зарплата: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Стаж (років): ");
            int experience = Integer.parseInt(scanner.nextLine().trim());

            employees.add(new Employee(name, position, salary, experience));
        }

        System.out.println("\n--- Список співробітників ---");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}