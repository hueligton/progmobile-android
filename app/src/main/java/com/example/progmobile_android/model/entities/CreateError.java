package com.example.progmobile_android.model.entities;

import java.util.Arrays;

public class CreateError {
    private String[] errors;

    public CreateError() {
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return Arrays.toString(errors);
    }
}
