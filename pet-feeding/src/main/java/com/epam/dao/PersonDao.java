package com.epam.dao;

import com.epam.domain.Person;

import java.util.Optional;

public interface PersonDao extends Dao<Person, Integer> {
    public Optional<Person> getByEmail(String email);

    }
