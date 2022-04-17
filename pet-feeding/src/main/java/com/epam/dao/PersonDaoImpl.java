package com.epam.dao;

import com.epam.domain.Person;
import com.epam.hikari.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {
    private static PersonDaoImpl instance = new PersonDaoImpl();
    private static final String GET_ONE = "SELECT ID, NAME, EMAIL, PASSWORD FROM persons WHERE ID = ?";
    private static final String GET_ALL = "SELECT ID, NAME, EMAIL, PASSWORD FROM persons";
    private static final String INSERT = "INSERT INTO persons (id, name," +
            "email, password) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM persons WHERE id = ?";
    private static final String GET_BY_EMAIL =  "SELECT ID, NAME, EMAIL, PASSWORD FROM persons WHERE EMAIL = ?";


    private PersonDaoImpl() {}

    public static PersonDao instance() {
        return instance;
    }

    @Override
    public Optional<Person> getById(Integer id) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ONE)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Person person = new Person();
            while (rs.next()) {
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
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
            System.out.println(e + " GetById Person is NOT fetched");
        }
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        ArrayList<Person> persons = new ArrayList<Person>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            Person person;
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                person = new Person();
                 person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e + "GET_ALL persons is NOT fetched");
        }
        return persons;
    }

    @Override
    public Person create(Person person) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getEmail());
            statement.setString(4, person.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e + "Persons is NOT created");

        }
        return person;
    }

    @Override
    public void delete(Person person) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, person.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e + " Person is NOT delete");

        }
    }

    public Optional<Person> getByEmail(String email) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            Person person = new Person();
            while (rs.next()) {
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
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
            System.out.println(e + " Person is NOT Get By Email");
        }
        return Optional.empty();
    }
}
