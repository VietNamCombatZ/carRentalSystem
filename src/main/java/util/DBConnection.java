package main.java.util;

//package carRentalsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12341234"; // TODO: Thay đổi

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
