package com.epam;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConnectionSource {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petsdb";
    private static final ConnectionSource instance = new ConnectionSource();

    public static ConnectionSource instance() {
        return instance;
    }

    public Connection createConnection() throws SQLException {
        try (InputStream input = new FileInputStream("resurces/DBconfig.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return DriverManager.getConnection(DB_URL,
                    prop.getProperty("db.user"), prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private ConnectionSource() {

        try (InputStream input = new FileInputStream("resurces/DBconfig.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL,
                                                               prop.getProperty("db.user"),
                                                               prop.getProperty("db.password"))) {
                try (Statement statement = conn.createStatement()) {
                    statement.execute(getSql("pet_script.sql"));
                    System.out.println("Database is Connected");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database is Not Connected");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
}
