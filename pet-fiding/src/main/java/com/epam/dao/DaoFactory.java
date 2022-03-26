package com.epam.dao;

import com.epam.ConnectionSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private ConnectionSource connectionSource;
    private Connection connection;
    private PersonDao personDao;

    public DaoFactory() throws SQLException {
        connectionSource = ConnectionSource.instance();
        connection = connectionSource.createConnection();
        personDao = new PersonDaoImpl(connection);
    }

    public PersonDao personDao() throws SQLException {
        return personDao;
    }
}
