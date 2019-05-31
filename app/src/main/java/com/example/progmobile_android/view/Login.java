package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;

public class Login extends AppCompatActivity {

    private Toolbar toolbar;

    private ManagerFacade managerFacade;

    private Button btLogin;
    private EditText etLogin;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        managerFacade = new ManagerFacade(this);
        captureViewComponents();
    }

    private void captureViewComponents() {
        btLogin = findViewById(R.id.btLogin);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
    }

    public void login(View view) {
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        final boolean[] loginValidation = {false};

        if(validateFields(login, password)) {
            managerFacade.login(login, password, object -> {
                loginValidation[0] = true;
            });

            if (!loginValidation[0]) {
                Toast.makeText(this, "Login e/ou senha invalido, verifique os dados preenchidos",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (loginValidation[0]) {
            startActivity(new Intent(this, Home.class));
        }
        else {
            etLogin.getText().clear();
            etPassword.getText().clear();
        }

    }

    private boolean validateFields(String login, String password) {
        Boolean result = true;

        if(login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um login e uma senha",
                    Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

    public void register(View view) {
        // startActivity(new Intent(this, Register.class));
    }


}
