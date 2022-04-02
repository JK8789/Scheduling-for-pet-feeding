package com.epam.dao;

public class DaoFactory {

    public DaoFactory() {}

    public PersonDao personDao() {
        return PersonDaoImpl.instance();
    }

}
