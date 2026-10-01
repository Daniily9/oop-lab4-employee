package com.university;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.UUID;

public class MainApp extends Application {

    private static final String FILE_NAME = "input.txt";

    private Company company;

    private ComboBox<String> typeComboBox;
    private ComboBox<Position> positionComboBox;

    private TextField nameField;
    private TextField salaryField;
    private TextField experienceField;
    private TextField emailField;
    private TextField extraField;
    private TextField internshipMonthsField;
    private TextField uuidSearchField;

    private Label extraLabel;
    private Label statusLabel;

    private ListView<String> employeeListView;
    private TextArea resultArea;

    @Override
    public void start(Stage stage) {
        company = CompanyFileManager.loadFromFile(FILE_NAME);

        Label titleLabel = new Label("Employee Management");

        typeComboBox = new ComboBox<>(
                FXCollections.observableArrayList(
                        "ContractEmployee",
                        "FullTimeEmployee",
                        "PartTimeEmployee",
                        "InternEmployee"
                )
        );
        typeComboBox.setValue("FullTimeEmployee");

        positionComboBox = new ComboBox<>(
                FXCollections.observableArrayList(Position.values())
        );
        positionComboBox.setValue(Position.DEVELOPER);

        nameField = new TextField();
        salaryField = new TextField();
        experienceField = new TextField();
        emailField = new TextField();
        extraField = new TextField();
        internshipMonthsField = new TextField();

        extraLabel = new Label("Bonus:");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(8);

        form.add(new Label("Type:"), 0, 0);
        form.add(typeComboBox, 1, 0);

        form.add(new Label("Name:"), 0, 1);
        form.add(nameField, 1, 1);

        form.add(new Label("Position:"), 0, 2);
        form.add(positionComboBox, 1, 2);

        form.add(new Label("Salary:"), 0, 3);
        form.add(salaryField, 1, 3);

        form.add(new Label("Experience:"), 0, 4);
        form.add(experienceField, 1, 4);

        form.add(new Label("Email:"), 0, 5);
        form.add(emailField, 1, 5);

        form.add(extraLabel, 0, 6);
        form.add(extraField, 1, 6);

        Label internshipMonthsLabel = new Label("Internship months:");
        form.add(internshipMonthsLabel, 0, 7);
        form.add(internshipMonthsField, 1, 7);

        Button addButton = new Button("Add employee");
        addButton.setOnAction(event -> addEmployee());

        statusLabel = new Label();

        VBox createBox = new VBox(
                10,
                new Label("Create employee"),
                form,
                addButton,
                statusLabel
        );

        employeeListView = new ListView<>();
        refreshEmployeeList();

        VBox listBox = new VBox(
                10,
                new Label("Employees"),
                employeeListView
        );

        uuidSearchField = new TextField();
        uuidSearchField.setPromptText("Enter employee UUID");

        Button findButton = new Button("Find");
        findButton.setOnAction(event -> findByUuid());

        HBox searchBox = new HBox(
                10,
                uuidSearchField,
                findButton
        );

        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);

        VBox searchSection = new VBox(
                10,
                new Label("Search by UUID"),
                searchBox,
                resultArea
        );

        typeComboBox.setOnAction(event -> updateExtraField());

        VBox root = new VBox(
                15,
                titleLabel,
                createBox,
                listBox,
                searchSection
        );

        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 750, 700);

        stage.setTitle("Employee Management");
        stage.setScene(scene);
        stage.show();

        updateExtraField();
    }

    private void updateExtraField() {
        String type = typeComboBox.getValue();

        if ("FullTimeEmployee".equals(type)) {
            extraLabel.setText("Bonus:");
            extraField.setPromptText("Example: 500");
        } else if ("PartTimeEmployee".equals(type)) {
            extraLabel.setText("Weekly hours:");
            extraField.setPromptText("Example: 20");
        } else if ("ContractEmployee".equals(type)) {
            extraLabel.setText("Contract months:");
            extraField.setPromptText("Example: 12");
        } else if ("InternEmployee".equals(type)) {
            extraLabel.setText("Institution:");
            extraField.setPromptText("Example: University");
                        internshipMonthsField.setPromptText("Example: 6");

        } else {
            extraLabel.setText("Extra:");
            extraField.setPromptText("");
        }
    }

    private void addEmployee() {
        try {
            String name = nameField.getText().trim();
            Position position = positionComboBox.getValue();

            double salary = Double.parseDouble(
                    salaryField.getText().trim()
            );

            int experience = Integer.parseInt(
                    experienceField.getText().trim()
            );

            String email = emailField.getText().trim();
            String type = typeComboBox.getValue();

            Employee employee;

            switch (type) {
                case "FullTimeEmployee":
                    double bonus = Double.parseDouble(
                            extraField.getText().trim()
                    );

                    employee = new FullTimeEmployee(
                            name,
                            position,
                            salary,
                            experience,
                            email,
                            bonus
                    );
                    break;

                case "PartTimeEmployee":
                    int weeklyHours = Integer.parseInt(
                            extraField.getText().trim()
                    );

                    employee = new PartTimeEmployee(
                            name,
                            position,
                            salary,
                            experience,
                            email,
                            weeklyHours
                    );
                    break;

                case "ContractEmployee":
                    int contractMonths = Integer.parseInt(
                            extraField.getText().trim()
                    );

                    employee = new ContractEmployee(
                            name,
                            position,
                            salary,
                            experience,
                            email,
                            contractMonths
                    );
                    break;

                case "InternEmployee":
                    employee = new InternEmployee(
                            name,
                            position,
                            salary,
                            experience,
                            email,
                            extraField.getText().trim(),
                            1
                    );
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown employee type"
                    );
            }

            company.addNewEmployee(employee, 1);

            refreshEmployeeList();

            statusLabel.setText(
                    "Employee added. UUID: " + employee.getUuid()
            );

            clearFields();

        } catch (NumberFormatException e) {
            statusLabel.setText(
                    "Error: salary, experience and extra value "
                            + "must contain valid numbers."
            );

        } catch (IllegalArgumentException e) {
            statusLabel.setText(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void refreshEmployeeList() {
        employeeListView.getItems().clear();

        for (Employee employee : company.getEmployees()) {
            employeeListView.getItems().add(
                    employee.getClass().getSimpleName()
                            + " | "
                            + employee.getName()
                            + " | UUID: "
                            + employee.getUuid()
            );
        }
    }

    private void findByUuid() {
        String uuidText = uuidSearchField.getText().trim();

        try {
            UUID uuid = UUID.fromString(uuidText);

            Employee employee =
                    EmployeeSearcher.findByUuid(
                            company.getEmployees(),
                            uuid
                    );

            if (employee == null) {
                resultArea.setText(
                        "Employee with this UUID was not found."
                );
                return;
            }

            resultArea.setText(
                    "Type: "
                            + employee.getClass().getSimpleName()
                            + "\n\n"
                            + employee
            );

        } catch (IllegalArgumentException e) {
            resultArea.setText(
                    "Error: invalid UUID format."
            );
        }
    }

    private void clearFields() {
        nameField.clear();
        salaryField.clear();
        experienceField.clear();
        emailField.clear();
        extraField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}