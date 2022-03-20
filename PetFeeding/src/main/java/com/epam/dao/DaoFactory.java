package com.epam.dao;

import com.epam.ConnectionSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    //import com.epam.rd.autocode.ConnectionSource;
    private ConnectionSource connectionSource;
    private Connection connection;

    public DaoFactory() throws SQLException {
        connectionSource = ConnectionSource.instance();
        connection = connectionSource.createConnection();
    }

//    public EmployeeDao employeeDAO() throws SQLException {
//        EmployeeDao employeeDao = new EmployeeDaoImpl(connection);
//        return employeeDao;
//    }
//
//    public DepartmentDao departmentDAO() throws SQLException {
//        DepartmentDao departmentDao = new DepartmentDaoImpl(connection);
//        return departmentDao;
//    }
}
