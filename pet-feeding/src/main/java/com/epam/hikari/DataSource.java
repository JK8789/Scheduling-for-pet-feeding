package com.epam.hikari;

import com.epam.dao.PersonDaoImpl;
import com.epam.domain.Person;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    static HikariConfig config = new HikariConfig("/hikari.properties");
    static HikariDataSource ds = new HikariDataSource(config);
    private static final DataSource instance = new DataSource();

    public static DataSource instance() {
        return instance;
    }

    private DataSource() {
    }

//    public static List<Person> fetchData() {
//
//        final String SQL_QUERY = "SELECT * FROM persons";
//        List<Person> persons = null;
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement pst = con.prepareStatement(SQL_QUERY);
//             ResultSet rs = pst.executeQuery()) {
//            if (con != null)
//                System.out.println("Connection Successful!\n");
//            persons = new ArrayList<Person>();
//            Person person;
//            while (rs.next()) {
//                person = new Person();
//                person.setId(rs.getInt("id"));
//                person.setName(rs.getString("name"));
//                person.setEmail(rs.getString("email"));
//                person.setPassword(rs.getString("password"));
//                persons.add(person);
//                System.out.println("Data is fetched: " + persons);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Data is NOT fetched");
//        }
//        return persons;
//    }

    public static void main(String[] args) {
        //fetchData();
        System.out.println("getById: " + PersonDaoImpl.instance().getById(1));
        System.out.println("getALL: " + PersonDaoImpl.instance().getAll());
        System.out.println("getByEmail: " + PersonDaoImpl.instance().getByEmail("Yulia-89@yandex.com"));

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
