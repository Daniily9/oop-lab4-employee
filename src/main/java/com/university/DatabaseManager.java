package com.university;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class DatabaseManager {

    private final String url;
    private final String user;
    private final String password;

    public DatabaseManager(String configPath) throws IOException {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(configPath)) {
            properties.load(input);
        }

        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = properties.getProperty("db.password");

        if (url == null || user == null || password == null) {
            throw new IllegalArgumentException(
                    "У db.properties відсутні параметри підключення."
            );
        }
    }

    public void insertEmployee(Employee employee) throws SQLException {
        String sql =
                "INSERT INTO employees (" +
                "type, name, position, salary, experience_years, email, " +
                "contract_months, bonus, weekly_hours, " +
                "educational_institution, internship_months" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DriverManager.getConnection(url, user, password);
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(
                    1,
                    employee.getClass().getSimpleName()
            );
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getPosition().name());
            statement.setDouble(4, employee.getSalary());
            statement.setInt(5, employee.getExperienceYears());
            statement.setString(6, employee.getEmail());

            setContractMonths(statement, employee);
            setBonus(statement, employee);
            setWeeklyHours(statement, employee);
            setEducationalInstitution(statement, employee);
            setInternshipMonths(statement, employee);

            statement.executeUpdate();
        }
    }

    private void setContractMonths(
            PreparedStatement statement,
            Employee employee
    ) throws SQLException {
        if (employee instanceof ContractEmployee) {
            ContractEmployee contractEmployee =
                    (ContractEmployee) employee;

            statement.setInt(
                    7,
                    contractEmployee.getContractMonths()
            );
        } else {
            statement.setNull(7, Types.INTEGER);
        }
    }

    private void setBonus(
            PreparedStatement statement,
            Employee employee
    ) throws SQLException {
        if (employee instanceof FullTimeEmployee) {
            FullTimeEmployee fullTimeEmployee =
                    (FullTimeEmployee) employee;

            statement.setDouble(
                    8,
                    fullTimeEmployee.getBonus()
            );
        } else {
            statement.setNull(8, Types.DOUBLE);
        }
    }

    private void setWeeklyHours(
            PreparedStatement statement,
            Employee employee
    ) throws SQLException {
        if (employee instanceof PartTimeEmployee) {
            PartTimeEmployee partTimeEmployee =
                    (PartTimeEmployee) employee;

            statement.setInt(
                    9,
                    partTimeEmployee.getWeeklyHours()
            );
        } else {
            statement.setNull(9, Types.INTEGER);
        }
    }

    private void setEducationalInstitution(
            PreparedStatement statement,
            Employee employee
    ) throws SQLException {
        if (employee instanceof InternEmployee) {
            InternEmployee internEmployee =
                    (InternEmployee) employee;

            statement.setString(
                    10,
                    internEmployee.getEducationalInstitution()
            );
        } else {
            statement.setNull(10, Types.VARCHAR);
        }
    }

    private void setInternshipMonths(
            PreparedStatement statement,
            Employee employee
    ) throws SQLException {
        if (employee instanceof InternEmployee) {
            InternEmployee internEmployee =
                    (InternEmployee) employee;

            statement.setInt(
                    11,
                    internEmployee.getInternshipMonths()
            );
        } else {
            statement.setNull(11, Types.INTEGER);
        }
    }
}