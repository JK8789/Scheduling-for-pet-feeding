package com.epam.services;

import com.epam.dao.PersonDao;
import com.epam.dao.PersonDaoImpl;
import com.epam.domain.Person;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService{
    private static PersonServiceImpl instance = new PersonServiceImpl();
    private PersonServiceImpl() {}
    public static PersonService instance() {
        return instance;
    }

    @Override
    public Optional<Person> getById(Integer id) {
        return PersonDaoImpl.instance().getById(id);
    }

    @Override
    public List<Person> getAll() {
        return PersonDaoImpl.instance().getAll();
    }

    @Override
    public Person create(Person person) {
        return PersonDaoImpl.instance().create(person);
    }

    @Override
    public void delete(Person person) {
        PersonDaoImpl.instance().delete(person);
    }

    @Override
    public Optional<Person> getByEmail(String email) {
        return PersonDaoImpl.instance().getByEmail(email);
    }
}
