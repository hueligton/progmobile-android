package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Card;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.ServerCallback;

import java.util.LinkedList;
import java.util.List;

public class PurchaseConfirmation extends AppCompatActivity {

    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_confirmation);
    }

    public void conclude(View view) {

        Intent intent = getIntent();

        int eventId = intent.getIntExtra("eventId", 0);
        List<Pair> pairList = (List<Pair>) intent.getSerializableExtra("pairList");
        Card card = (Card) intent.getSerializableExtra("card");

        managerFacade.getUser(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                UserToken user = (UserToken) object;

                managerFacade.setPurchase(
                        user.getUser().getId(),
                        user.getToken(),
                        card,
                        eventId,
                        pairList,
                        new ServerCallback() {
                            @Override
                            public void onSuccess(Object object) {
                                Intent purchaseIntent = new Intent(PurchaseConfirmation.this, PurchaseCompleted.class);
                                Purchase purchase = (Purchase) object;
                                purchaseIntent.putExtra("purchase", purchase);
                                startActivity(purchaseIntent);
                            }

                            @Override
                            public void onError(Object object) {
                                Toast.makeText(PurchaseConfirmation.this, R.string.toast_unsuccessful_purchase, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }

            @Override
            public void onError(Object object) {
            }
        });


    }
}
