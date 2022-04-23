package com.epam.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class Person {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.toString();
        //return String.format("Person [id=%d, name=%s, email=%s, password=%s]", id, name, email, password);
    }
}
