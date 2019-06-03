package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Card;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.ServerCallback;

import java.util.LinkedList;
import java.util.List;

public class PurchaseCompleted extends AppCompatActivity {

    TextView purchaseInformation;
    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_completed);

        purchaseInformation = findViewById(R.id.purchaseInformation);

        Intent intent = getIntent();

        Purchase purchase = (Purchase) intent.getSerializableExtra("purchase");

        purchaseInformation.setText(purchase.getId());
    }

    public void showMyTickets(View view) {
        Intent intent = new Intent(this, MyTickets.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
