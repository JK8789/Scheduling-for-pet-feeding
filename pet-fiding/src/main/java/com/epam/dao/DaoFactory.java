package com.epam.dao;

import com.epam.hikari.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private DataSource dataSource;
    private Connection connection;
    private PersonDao personDao;

    public DaoFactory() throws SQLException {
        dataSource = DataSource.instance();
        connection = (Connection) dataSource.getConnection();
        personDao = new PersonDaoImpl(connection);
    }

    public PersonDao personDao() throws SQLException {
        return personDao;
    }
}
