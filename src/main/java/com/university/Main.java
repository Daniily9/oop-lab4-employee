package com.university;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static Company company;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "input.txt";

    public static void main(String[] args) {
        company = CompanyFileManager.loadFromFile(FILE_NAME);

        System.out.println(
            "Завантажено працівників: "
                + company.getEmployees().size()
        );

        while (true) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
            System.out.println("1. Пошук об'єкта");
            System.out.println("2. Створити новий об'єкт");
            System.out.println("3. Модифікувати працівника");
            System.out.println("4. Видалити працівника");
            System.out.println("5. Вивести інформацію про всі об'єкти");
            System.out.println("6. Вивести відсортовану інформацію про всіх працівників");
            System.out.println("7. Завершити роботу");
            System.out.print("Оберіть пункт: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchMenu();
                    break;

                case "2":
                    createObject();
                    break;

                case "3":
                    modifyEmployee();
                    break;

                case "4":
                    deleteEmployee();
                    break;

                case "5":
                    printAllObjects();
                    break;

                case "6":
                    sortMenu();
                    break;

                case "7":
                    CompanyFileManager.saveToFile(company, FILE_NAME);
                    System.out.println("Дані збережено у файл " + FILE_NAME + ".");
                    System.out.println("Роботу завершено.");
                    return;

                default:
                    System.out.println("Помилка: введіть число від 1 до 7.");
            }
        }
    }

    private static void createObject() {
        while (true) {
            System.out.println("\n=== СТВОРЕННЯ ОБ'ЄКТА ===");
            System.out.println("1. ContractEmployee");
            System.out.println("2. FullTimeEmployee");
            System.out.println("3. PartTimeEmployee");
            System.out.println("4. InternEmployee");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Оберіть тип: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ContractEmployee contractEmployee =
                        createContractEmployee();
                    int contractQuantity =
                        readInt("Кількість працівників: ");
                    company.addNewEmployee(contractEmployee, contractQuantity);
                    System.out.println("Об'єкт ContractEmployee успішно створено.");
                    return;

                case "2":
                    FullTimeEmployee fullTimeEmployee =
                        createFullTimeEmployee();
                    int fullTimeQuantity =
                        readInt("Кількість працівників: ");
                    company.addNewEmployee(fullTimeEmployee, fullTimeQuantity);
                    System.out.println("Об'єкт FullTimeEmployee успішно створено.");
                    return;

                case "3":
                    PartTimeEmployee partTimeEmployee =
                        createPartTimeEmployee();
                    int partTimeQuantity =
                        readInt("Кількість працівників: ");
                    company.addNewEmployee(partTimeEmployee, partTimeQuantity);
                    System.out.println("Об'єкт PartTimeEmployee успішно створено.");
                    return;

                case "4":
                    InternEmployee internEmployee = createInternEmployee();
                    int internQuantity =
                        readInt("Кількість працівників: ");
                    company.addNewEmployee(internEmployee, internQuantity);
                    System.out.println("Об'єкт InternEmployee успішно створено.");
                    return;

                case "0":
                    return;

                default:
                    System.out.println("Помилка: введіть число від 0 до 4.");
            }
        }
    }

    private static void searchMenu() {
        while (true) {
            System.out.println("\n=== ПОШУК ОБ'ЄКТА ===");
            System.out.println("1. За посадою");
            System.out.println("2. За мінімальним стажем");
            System.out.println("3. За діапазоном зарплати");
            System.out.println("4. За іменем (частина рядка)");
            System.out.println("0. Повернутися до головного меню");
            System.out.println("5. За UUID");
            System.out.print("Оберіть критерій: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchByPosition();
                    return;
                case "2":
                    searchByMinExperience();
                    return;
                case "3":
                    searchBySalaryRange();
                    return;
                case "4":
                    searchByName();
                    return;
                case "5":
                    searchByUuid();
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Помилка: введіть число від 0 до 5.");
            }
        }
    }

    private static void searchByPosition() {
        Position position = readPosition();
        printSearchResults(company.findByPosition(position));
    }

    private static void searchByMinExperience() {
        int minExperience = readInt("Мінімальний стаж (років): ");
        printSearchResults(
                company.findByMinExperience(minExperience)
        );
    }

    private static void searchBySalaryRange() {
        double minSalary = readDouble("Мінімальна зарплата: ");
        double maxSalary = readDouble("Максимальна зарплата: ");
        printSearchResults(
                company.findBySalaryRange(minSalary, maxSalary)
        );
    }

    private static void searchByName() {
        String fragment = readString("Ім'я або його частина: ");
        printSearchResults(company.findByName(fragment));
    }

    private static void searchByUuid() {
        String uuidText = readString("Введіть UUID: ");

        try {
            UUID uuid = UUID.fromString(uuidText);
            Employee employee =
                    EmployeeSearcher.findByUuid(
                            company.getEmployees(),
                            uuid
                    );

            if (employee == null) {
                System.out.println(
                        "Працівника з таким UUID не знайдено."
                );
                return;
            }

            System.out.println("\n=== ЗНАЙДЕНИЙ ПРАЦІВНИК ===");
            System.out.println("Тип: "
                    + employee.getClass().getSimpleName());
            System.out.println(employee);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Помилка: введено некоректний формат UUID."
            );
        }
    }

    private static void printSearchResults(List<Employee> result) {
        System.out.println("\n=== РЕЗУЛЬТАТИ ПОШУКУ ===");

        if (result.isEmpty()) {
            System.out.println("Жоден об'єкт не відповідає умовам пошуку.");
            return;
        }

        for (Employee employee : result) {
            System.out.println("Тип: " + employee.getClass().getSimpleName());
            System.out.println(employee);
            System.out.println();
        }
    }

    // private static Employee createEmployee() {
    //     System.out.println("\n--- Створення Employee ---");

    //     String name = readString("Ім'я: ");
    //     Position position = readPosition();
    //     double salary = readDouble("Зарплата: ");
    //     int experienceYears = readInt("Стаж (років): ");
    //     String email = readString("Email: ");

    //     return new Employee(
    //             name,
    //             position,
    //             salary,
    //             experienceYears,
    //             email
    //     );
    // }

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

        if (company.getEmployees().isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }

        for (Employee employee : company.getEmployees()) {
            System.out.println("Тип: "
                    + employee.getClass().getSimpleName());
            System.out.println(employee);
            System.out.println();
        }
    }

    private static void modifyEmployee() {
        List<Employee> employees = company.getEmployees();

        if (employees.isEmpty()) {
            System.out.println("Немає працівників для модифікації.");
            return;
        }

        System.out.println("\n=== МОДИФІКАЦІЯ ПРАЦІВНИКА ===");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println((i + 1) + ". "
                    + employee.getClass().getSimpleName() + ": " + employee);
        }

        int employeeChoice = readInt("Оберіть номер працівника: ");
        if (employeeChoice < 1 || employeeChoice > employees.size()) {
            System.out.println("Помилка: працівника з таким номером немає.");
            return;
        }

        Employee existingEmployee = employees.get(employeeChoice - 1);
        System.out.println("1. Ім'я");
        System.out.println("2. Посада");
        System.out.println("3. Зарплата");
        System.out.println("4. Стаж");
        System.out.println("5. Email");

        if (existingEmployee instanceof ContractEmployee) {
            System.out.println("6. Тривалість контракту");
        } else if (existingEmployee instanceof FullTimeEmployee) {
            System.out.println("6. Бонус");
        } else if (existingEmployee instanceof PartTimeEmployee) {
            System.out.println("6. Робочі години на тиждень");
        } else if (existingEmployee instanceof InternEmployee) {
            System.out.println("6. Навчальний заклад");
            System.out.println("7. Тривалість стажування");
        }

        int attributeChoice = readInt("Оберіть атрибут для зміни: ");
        int maxAttribute = existingEmployee instanceof InternEmployee ? 7 : 6;
        if (attributeChoice < 1 || attributeChoice > maxAttribute) {
            System.out.println("Помилка: такого атрибута немає.");
            return;
        }

        Employee updatedEmployee = copyEmployee(existingEmployee);

        try {
            switch (attributeChoice) {
                case 1:
                    updatedEmployee.setName(readString("Нове ім'я: "));
                    break;
                case 2:
                    updatedEmployee.setPosition(readPosition());
                    break;
                case 3:
                    updatedEmployee.setSalary(readDouble("Нова зарплата: "));
                    break;
                case 4:
                    updatedEmployee.setExperienceYears(
                            readInt("Новий стаж (років): "));
                    break;
                case 5:
                    updatedEmployee.setEmail(readString("Новий email: "));
                    break;
                case 6:
                    if (updatedEmployee instanceof ContractEmployee) {
                        ((ContractEmployee) updatedEmployee).setContractMonths(
                                readInt("Нова тривалість контракту (місяців): "));
                    } else if (updatedEmployee instanceof FullTimeEmployee) {
                        ((FullTimeEmployee) updatedEmployee).setBonus(
                                readDouble("Новий бонус: "));
                    } else if (updatedEmployee instanceof PartTimeEmployee) {
                        ((PartTimeEmployee) updatedEmployee).setWeeklyHours(
                                readInt("Нові робочі години на тиждень: "));
                    } else if (updatedEmployee instanceof InternEmployee) {
                        ((InternEmployee) updatedEmployee)
                                .setEducationalInstitution(
                                        readString("Новий навчальний заклад: "));
                    }
                    break;
                case 7:
                    ((InternEmployee) updatedEmployee).setInternshipMonths(
                            readInt("Нова тривалість стажування (місяців): "));
                    break;
                default:
                    return;
            }

            if (company.update(existingEmployee, updatedEmployee)) {
                System.out.println("Дані працівника успішно оновлено.");
            } else {
                System.out.println("Працівника не знайдено; оновлення не виконано.");
            }
        } catch (ObjectNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static Employee copyEmployee(Employee employee) {
        if (employee instanceof ContractEmployee) {
            ContractEmployee contractEmployee = (ContractEmployee) employee;
            return new ContractEmployee(
                    employee.getName(), employee.getPosition(),
                    employee.getSalary(), employee.getExperienceYears(),
                    employee.getEmail(), contractEmployee.getContractMonths());
        }
        if (employee instanceof FullTimeEmployee) {
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employee;
            return new FullTimeEmployee(
                    employee.getName(), employee.getPosition(),
                    employee.getSalary(), employee.getExperienceYears(),
                    employee.getEmail(), fullTimeEmployee.getBonus());
        }
        if (employee instanceof PartTimeEmployee) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
            return new PartTimeEmployee(
                    employee.getName(), employee.getPosition(),
                    employee.getSalary(), employee.getExperienceYears(),
                    employee.getEmail(), partTimeEmployee.getWeeklyHours());
        }
        if (employee instanceof InternEmployee) {
            InternEmployee internEmployee = (InternEmployee) employee;
            return new InternEmployee(
                    employee.getName(), employee.getPosition(),
                    employee.getSalary(), employee.getExperienceYears(),
                    employee.getEmail(),
                    internEmployee.getEducationalInstitution(),
                    internEmployee.getInternshipMonths());
        }

        throw new IllegalArgumentException("Невідомий тип працівника.");
    }

    private static void deleteEmployee() {
        List<Employee> employees = company.getEmployees();

        if (employees.isEmpty()) {
            System.out.println("Немає працівників для видалення.");
            return;
        }

        System.out.println("\n=== ВИДАЛЕННЯ ПРАЦІВНИКА ===");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println((i + 1) + ". "
                    + employee.getClass().getSimpleName() + ": " + employee);
        }

        int choice = readInt("Оберіть номер працівника: ");
        if (choice < 1 || choice > employees.size()) {
            System.out.println("Помилка: працівника з таким номером немає.");
            return;
        }

        Employee employee = employees.get(choice - 1);
        System.out.println("Видалити цього працівника? (yes/no)");
        System.out.println(employee);
        System.out.print("Ваш вибір: ");

        String confirmation = scanner.nextLine().trim();
        if (confirmation.equalsIgnoreCase("no")) {
            System.out.println("Видалення скасовано.");
            return;
        }
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Введіть yes або no. Видалення скасовано.");
            return;
        }

        try {
            if (company.delete(employee)) {
                System.out.println("Працівника успішно видалено.");
            } else {
                System.out.println("Працівника не знайдено; видалення не виконано.");
            }
        } catch (ObjectNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void sortMenu() {
        while (true) {
            System.out.println("\n=== ВИБІР КРИТЕРІЮ СОРТУВАННЯ ===");
            System.out.println("1. За ім'ям");
            System.out.println("2. За зарплатою");
            System.out.println("3. За досвідом роботи");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Оберіть критерій: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    sortByName();
                    return;
                case "2":
                    sortBySalary();
                    return;
                case "3":
                    sortByExperience();
                    return;
                case "0":
                    return;
                default:
                    System.out.println(
                            "Помилка: введіть число від 0 до 3."
                    );
            }
        }
    }

    private static void sortByName() {
        List<Employee> sortedEmployees =
                new ArrayList<>(company.getEmployees());

        Comparator<Employee> comparator =
                (first, second) -> first.getName().compareTo(second.getName());

        Collections.sort(sortedEmployees, comparator);

        printSortedResults(sortedEmployees, "ім'ям");
    }

    private static void sortBySalary() {
        List<Employee> sortedEmployees =
                new ArrayList<>(company.getEmployees());

        Comparator<Employee> comparator =
                (first, second) -> Double.compare(
                        first.getSalary(),
                        second.getSalary()
                );

        Collections.sort(sortedEmployees, comparator);

        printSortedResults(sortedEmployees, "зарплатою");
    }

    private static void sortByExperience() {
        List<Employee> sortedEmployees =
                new ArrayList<>(company.getEmployees());

        Comparator<Employee> comparator =
                (first, second) -> Integer.compare(
                        first.getExperienceYears(),
                        second.getExperienceYears()
                );

        Collections.sort(sortedEmployees, comparator);

        printSortedResults(sortedEmployees, "досвідом роботи");
    }

    private static void printSortedResults(
        List<Employee> sortedEmployees,
        String criterion) {

        System.out.println(
                "\n=== ВІДСОРТОВАНА ІНФОРМАЦІЯ ЗА " + criterion.toUpperCase() + " ==="
        );

        if (sortedEmployees.isEmpty()) {
            System.out.println("Список працівників порожній.");
            return;
        }

        for (Employee employee : sortedEmployees) {
            System.out.println(
                    employee + ", quantity=" + company.getQuantity(employee)
            );
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
