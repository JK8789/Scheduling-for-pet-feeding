package com.epam.dao;

import com.epam.domain.Person;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {
    private Connection connection;
    private static final String GET_ONE = "SELECT ID, NAME, EMAIL, PASSWORD FROM PERSON WHERE ID = ?";

    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Person> getById(Integer Id) {
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public void delete(Person person) {
    }
}
