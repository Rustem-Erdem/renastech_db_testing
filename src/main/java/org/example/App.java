package org.example;

import java.sql.*;

/**
 * Unit test for simple App.
 */
public class App {
    public static void main(String[] args) {
        Connection dbConnection = null;
        try {
            // connect to our database
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://rt-db-3.ctigbjf2wamf.us-east-1.rds.amazonaws.com/hr?user=rt_1&password=rt_1"
            );
            // make a SQL statement
            Statement statement = dbConnection.createStatement();
            // get the result from the execution
            ResultSet resultSet = statement.executeQuery("select * from employees;");
            // go through all the data
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date hireDate = resultSet.getDate("hire_date");
                int id = resultSet.getInt("employee_id");
                System.out.printf("Employee Name and ID: %s %s, %d, Hire date: %s", firstName, lastName, id, hireDate.toString());
                System.out.println();
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
            }
        }
    }
}