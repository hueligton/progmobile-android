package com.example.progmobile_android.model.entities;

import java.io.Serializable;

public class UserToken implements Serializable {
    private User user;
    private String token;

    public UserToken(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
