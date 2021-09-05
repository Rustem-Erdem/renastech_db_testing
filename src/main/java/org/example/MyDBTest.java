package org.example;

import java.sql.*;

public class MyDBTest {
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
            ResultSet resultSet = statement.executeQuery("select first_name, last_name\n" +
                    "  from employees\n" +
                    " where salary > 5000\n" +
                    "   and (salary < 8000 or commission_pct > 0.35)\n" +
                    "   and commission_pct is not null;");
            // go through all the data
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                System.out.printf("Employee Name : %s %s, ", firstName, lastName);
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