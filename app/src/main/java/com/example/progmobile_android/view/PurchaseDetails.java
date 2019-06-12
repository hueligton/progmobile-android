package com.example.progmobile_android.view;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entity.Event;
import com.example.progmobile_android.model.entity.Purchase;
import com.example.progmobile_android.model.entity.Ticket;
import com.example.progmobile_android.view.RecyclerAdapter.RATicketType3;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PurchaseDetails extends AppCompatActivity {

    private TextView tvEventDate;
    private TextView tvEventLocal;
    private TextView tvEventName;
    private TextView tvEventTime;
    private RecyclerView rvTickets;

    private Event event;

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

        Purchase purchase = (Purchase) getIntent().getSerializableExtra("purchase");

        List<Ticket> list = purchase.getTickets();
        event = purchase.getEvent();
        Date date = event.getDate();

        tvEventDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(date.getTime()));
        tvEventName.setText(event.getName());
        tvEventLocal.setText(event.getPlace().getAddress());
        tvEventTime.setText(new SimpleDateFormat("HH:mm").format(date.getTime()));

        RATicketType3 raTicketType3 = new RATicketType3(list);
        rvTickets.setAdapter(raTicketType3);
    }

    private void captureViewComponents() {
        tvEventDate = findViewById(R.id.tvEventDate);
        tvEventLocal = findViewById(R.id.tvEventLocal);
        tvEventName = findViewById(R.id.tvEventName);
        tvEventTime = findViewById(R.id.tvEventTime);
        rvTickets = findViewById(R.id.rvTickets);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void seeLocal(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("event", event);
        startActivity(intent);
    }

}
