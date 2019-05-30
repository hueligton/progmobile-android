package com.example.progmobile_android.model.entities;

public class User {

    private int id;
    private String login;
    private String name;
    private String password;
    private String email;

    public User(int id, String login, String name, String password, String email) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
       return id + " - " + login + " - " + name + " - " + password + " - " + email;
    }
}
