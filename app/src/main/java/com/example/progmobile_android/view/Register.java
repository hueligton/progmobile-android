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

public class Register extends AppCompatActivity {

    private ManagerFacade managerFacade = new ManagerFacade(this);

    private EditText etName;
    private EditText etEmail;
    private EditText etLogin;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        captureViewComponents();
    }

    private void captureViewComponents() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
    }

    public void register(View view) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        if(validateFields(name, email, login, password))
            managerFacade.createUser(login, name, password, email, this::onSuccess);
    }

    private boolean validateFields(String name, String email, String login, String password) {
        if(Stream.of(name, email, login, password).anyMatch(String::isEmpty)) {
            makeText(this, R.string.toast_unfilled_fields, LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void onSuccess(Object object) {
        if (object != null) {
            makeText(this, R.string.toast_successful_registration, LENGTH_SHORT).show();
            startActivity(new Intent(this, Login.class));
        } else {
            makeText(this, R.string.toast_unsuccessful_registration, LENGTH_SHORT).show();
            etName.getText().clear();
            etEmail.getText().clear();
            etLogin.getText().clear();
            etPassword.getText().clear();
        }
    }

}
