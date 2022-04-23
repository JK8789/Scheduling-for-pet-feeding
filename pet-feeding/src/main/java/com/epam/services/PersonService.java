package com.epam.services;

import com.epam.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> getById(Integer id);
    List<Person> getAll();
    Person create(Person person);
    void delete(Person person);
    Optional<Person> getByEmail(String email);
}
