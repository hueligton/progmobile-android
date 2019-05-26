package com.example.progmobile_android.model.manager;

import android.content.Context;

import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.ServerCallback;

public class UserManager {
    private Context context;

    public UserManager(Context context){
        this.context=context;
    }

    public void login(String login, String password, final ServerCallback serverCallback) {

    }

    public void logout(String userId, String token, final ServerCallback serverCallback) {

    }

    public void createUser(String login, String name, String password, String email, final ServerCallback serverCallback) {

    }
}
