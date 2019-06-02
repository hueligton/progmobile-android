package com.example.progmobile_android.view;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.TicketType;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.TicketTypeRecyclerAdapter;

import java.util.List;
import java.util.Objects;

public class EventDetails extends AppCompatActivity {

    public Context context;
    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    private RecyclerView recyclerView;

    private TextView tvEventName;
    private TextView tvEventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_event_details);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        int eventId = getIntent().getIntExtra("event_id", 0);

        captureViewComponents();

        recyclerView = findViewById(R.id.rvTicketType);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        context = this;
        managerFacade.getEvent(eventId, new ServerCallback() {

            @Override
            public void onSuccess(Object object) {
                Event event = (Event) object;
                List<TicketType> ticketTypes = event.getTicketTypes();

                TicketTypeRecyclerAdapter ticketTypeRecyclerAdapter;
                ticketTypeRecyclerAdapter = new TicketTypeRecyclerAdapter(ticketTypes);
                recyclerView.setAdapter(ticketTypeRecyclerAdapter);

                tvEventDescription.setText(event.getDescription());
                tvEventName.setText(event.getName());
            }

            @Override
            public void onError(Object object) {

            }
        });

    }

    private void captureViewComponents() {
        tvEventName = findViewById(R.id.tvEventName);
        tvEventDescription = findViewById(R.id.tvEventDescription);
    }

    public void buyTickets(View view) {
    }

}
