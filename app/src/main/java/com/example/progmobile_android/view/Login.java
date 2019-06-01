package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;

import java.util.Objects;
import java.util.stream.Stream;

import static android.widget.Toast.*;

public class Login extends AppCompatActivity {

    private ManagerFacade managerFacade = new ManagerFacade(this);

    private EditText etLogin;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        captureViewComponents();
    }

    private void captureViewComponents() {
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
    }

    public void login(View view) {
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        if(validateFields(login, password))
            managerFacade.login(login, password, this::onSuccess);
    }

    private boolean validateFields(String login, String password) {
        if(Stream.of(login, password).anyMatch(String::isEmpty)) {
            makeText(this, R.string.toast_unfilled_fields, LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void onSuccess(Object object) {
        if (object != null)
            startActivity(new Intent(this, Home.class));
        else {
            makeText(this, R.string.toast_invalid_login, LENGTH_SHORT).show();
            etLogin.getText().clear();
            etPassword.getText().clear();
        }
    }

    public void register(View view) {
        startActivity(new Intent(this, Register.class));
    }

}
