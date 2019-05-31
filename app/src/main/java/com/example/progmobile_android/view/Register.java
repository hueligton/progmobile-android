package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;

public class Register extends AppCompatActivity {

    private Toolbar toolbar;

    private ManagerFacade managerFacade;

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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        managerFacade = new ManagerFacade(this);
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

        final boolean[] registerValidation = {false};

        if(validateFields(name, email, login, password)) {

            managerFacade.createUser(login, name, password, email, object -> {
                registerValidation[0] = true;
            });

            if (!registerValidation[0]) {
                Toast.makeText(Register.this, "Não conseguimos realizar seu " +
                        "cadastro no momento, tente novamente mais tarde!", Toast.LENGTH_SHORT)
                        .show();

                etName.getText().clear();
                etEmail.getText().clear();
                etLogin.getText().clear();
                etPassword.getText().clear();
            }
        }

        if (registerValidation[0]) {
            Toast.makeText(Register.this, "Cadastro realizado com sucesso",
                    Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, Login.class));
        }

    }

    private boolean validateFields(String name, String email, String login, String password) {
        Boolean result = true;

        if(name.isEmpty() || email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos",
                    Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

}
