package com.example.progmobile_android.model.manager;

import android.content.Context;

import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.UserToken;

public class UserManager {
    private Context context;

    public UserManager(Context context){
        this.context=context;
    }

    public UserToken login(String login, String password){
        return new UserToken(new User(login,password), "test");
    }

    public void logout(String userId, String token) {

    }

    public User createUser(String login, String name, String password, String email) {
        return new User(login, name, password, email);
    }
}
