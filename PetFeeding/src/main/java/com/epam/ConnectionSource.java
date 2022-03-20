package com.epam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConnectionSource {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petsdb";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "password";

    private static final ConnectionSource instance = new ConnectionSource();

    public static ConnectionSource instance() {
        return instance;
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private ConnectionSource() {

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                try (Statement statement = conn.createStatement()) {
                    statement.execute(getSql("PetFeeding.sql"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getSql(final String resourceName) {
        return new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                ConnectionSource.class.getClassLoader().getResourceAsStream(resourceName))))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args){
        System.out.println("Hello");

    }
}
