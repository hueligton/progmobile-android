package com.example.progmobile_android.view;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.Ticket;
import com.example.progmobile_android.view.RecyclerAdapter.RATicketType3;

import java.util.List;
import java.util.Objects;

public class PurchaseDetails extends AppCompatActivity {

    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    private TextView tvEventDate;
    private TextView tvEventLocal;
    private TextView tvEventName;
    private RecyclerView rvTickets;
    private RATicketType3 raTicketType3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_purchase_details);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        captureViewComponents();

        rvTickets.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTickets.setLayoutManager(layoutManager);

        //TODO VOLTAR
        //int purchaseId = getIntent().getIntExtra("purchase_id", 0);
        Purchase purchase = (Purchase) getIntent().getSerializableExtra("purchase_id");

        List<Ticket> list = purchase.getTickets();
        Event event = purchase.getEvent();
        list.forEach(ticket ->
                ticket.setTicketType(event
                        .getTicket_types()
                        .stream()
                        .filter(ticketType ->
                                ticketType.getId() == ticket.getTicketTypeId())
                        .findFirst().get()));

        tvEventDate.setText(event.getDate().toString());
        tvEventName.setText(event.getName());
        tvEventLocal.setText(event.getPlace().getAddress());

        raTicketType3 = new RATicketType3(list);
        rvTickets.setAdapter(raTicketType3);

        //TODO VOLTAR
        /*managerFacade.getUser(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                UserToken userToken = (UserToken) object;
                int userId = userToken.getUser().getId();
                String token = userToken.getToken();

                managerFacade.getPurchase(userId, token, purchaseId, new ServerCallback() {
                    @Override
                    public void onSuccess(Object object) {
                        Purchase purchase = (Purchase) object;
                        List<Ticket> list = purchase.getTickets();
                        Event event = purchase.getEvent();

                        tvEventDate.setText(event.getDate().toString());
                        tvEventName.setText(event.getName());
                        tvEventLocal.setText(event.getPlace().getAddress());

                        raTicketType3 = new RATicketType3(list);
                        rvTickets.setAdapter(raTicketType3);
                    }

                    @Override
                    public void onError(Object object) { }
                });
            }

            @Override
            public void onError(Object object) { }
        });*/
    }

    private void captureViewComponents() {
        tvEventDate = findViewById(R.id.tvEventDate);
        tvEventLocal = findViewById(R.id.tvEventLocal);
        tvEventName = findViewById(R.id.tvEventName);
        rvTickets = findViewById(R.id.rvTickets);
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
