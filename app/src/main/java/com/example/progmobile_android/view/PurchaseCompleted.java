package com.example.progmobile_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entity.Purchase;

public class PurchaseCompleted extends AppCompatActivity {

    TextView purchaseInformation;
    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_purchase_completed);

        purchaseInformation = findViewById(R.id.purchaseInformation);

        Intent intent = getIntent();

        Purchase purchase = (Purchase) intent.getSerializableExtra("purchase");

        purchaseInformation.setText(String.valueOf(purchase.getId()));
    }

    public void showMyTickets(View view) {
        Intent intent = new Intent(this, Purchases.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
