package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entities.Card;

import java.util.stream.Stream;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class PaymentData extends AppCompatActivity {

    private EditText etCardHolderName;
    private EditText etCardNumber;
    private EditText etValid;
    private EditText etSecurityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_data);

        captureViewComponents();
    }

    private void captureViewComponents() {
        etCardHolderName = findViewById(R.id.etCardHolderName);
        etCardNumber = findViewById(R.id.etCardNumber);
        etValid = findViewById(R.id.etValid);
        etSecurityCode = findViewById(R.id.etSecurityCode);
    }

    public void advance(View view) {
        String cardHolderName = etCardHolderName.getText().toString();
        String cardNumber = etCardNumber.getText().toString();
        String valid = etValid.getText().toString();
        String securityCode = etSecurityCode.getText().toString();


        if (validateFields(cardHolderName, cardNumber, valid, securityCode)) {
            Card card = new Card(cardHolderName, cardNumber, valid, securityCode);

            Intent intent = new Intent(this, PurchaseConfirmation.class);
            intent.putExtra("card", card);
            startActivity(intent);
        }
    }

    public void back(View view) {
        finish();
    }

    private boolean validateFields(String name, String email, String login, String password) {
        if (Stream.of(name, email, login, password).anyMatch(String::isEmpty)) {
            makeText(this, R.string.toast_unfilled_fields, LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
