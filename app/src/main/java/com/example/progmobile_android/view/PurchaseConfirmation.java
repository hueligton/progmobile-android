package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Card;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.TicketType;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.RATicketType2;

import java.util.List;
import java.util.Objects;

public class PurchaseConfirmation extends AppCompatActivity {

    ManagerFacade managerFacade = ManagerFacade.getInstance(this);
    private int eventId;
    private List<Pair> pairList;
    private Card card;
    private TextView tvCardHolderName;
    private TextView tvCardNumber;
    private TextView tvValid;
    private TextView tvEvent;

    private RATicketType2 raTicketType2;
    private RecyclerView rvTicketType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_confirmation);

        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        captureViewComponents();

        Bundle bundle = getIntent().getExtras();

        eventId = bundle.getInt("eventId", 0);
        pairList = (List<Pair>) bundle.getSerializable("pairList");
        card = (Card) bundle.getSerializable("card");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTicketType.setLayoutManager(layoutManager);
        rvTicketType.setHasFixedSize(true);

        managerFacade.getEvent(eventId, new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                Event event = (Event) object;
                List<TicketType> ticketTypes = event.getTicketTypes();

                tvCardHolderName.setText(card.getCardHolderName());
                tvCardNumber.setText(card.getCardNumber());
                tvValid.setText(card.getValid());
                tvEvent.setText(event.getName());

                raTicketType2 = new RATicketType2(ticketTypes, pairList);
                rvTicketType.setAdapter(raTicketType2);
            }

            @Override
            public void onError(Object object) {

            }
        });
    }

    private void captureViewComponents() {
        tvCardHolderName = findViewById(R.id.tvCardHolderName);
        tvCardNumber = findViewById(R.id.tvCardNumber);
        tvValid = findViewById(R.id.tvValid);
        tvEvent = findViewById(R.id.tvEvent);
        rvTicketType = findViewById(R.id.rvTicketType);
    }

    public void conclude(View view) {

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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
