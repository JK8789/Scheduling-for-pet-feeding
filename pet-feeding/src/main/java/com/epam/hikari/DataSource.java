package com.epam.hikari;

import com.epam.dao.PersonDaoImpl;
import com.epam.domain.Person;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    static HikariConfig config = new HikariConfig("/hikari.properties");
    static HikariDataSource ds = new HikariDataSource(config);
    private static final DataSource instance = new DataSource();

    public static DataSource instance() {
        return instance;
    }

    private DataSource() {
    }

    public static void main(String[] args) {
        System.out.println("getById: " + PersonDaoImpl.instance().getById(1));
        System.out.println("getALL: " + PersonDaoImpl.instance().getAll());
        System.out.println("getByEmail: " + PersonDaoImpl.instance().getByEmail("Yulia-89@yandex.com"));
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
