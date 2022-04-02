package com.epam.dao;

import com.epam.domain.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {
    private Connection connection;
    private static final String GET_ONE = "SELECT ID, NAME, EMAIL, PASSWORD FROM person WHERE ID = ?";
    private static final String GET_ALL = "SELECT ID, NAME, EMAIL, PASSWORD FROM person";
    private static final String INSERT = "INSERT INTO person (id, name," +
            "email, password) VALUES (?, ?, ?, ?)";

    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Person> getById(Integer id) {
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            Person person = new Person();
            while (rs.next()) {
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
                System.out.println("Person by id: " + person);
            }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("id", rs.getInt("ID"));
//            jsonObject.put("name", rs.getString("name"));
//            jsonObject.put("email", rs.getString("email"));
//            jsonObject.put("password", rs.getString("password"));
//            Person person = Person.Parser.parseJson(jsonObject.toString());

            return Optional.of(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        LinkedList<Person> result = new LinkedList<Person>();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ALL)) {
            Person person;
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
                result.add(person);
                System.out.println("GET_ALL persons: " + result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data is NOT fetched");
        }
        return result;
    }

    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public void delete(Person person) {
    }
}
