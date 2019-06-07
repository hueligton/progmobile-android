package com.example.progmobile_android.view;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.TicketType;
import com.example.progmobile_android.model.manager.Constants;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.RATicketType1;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventDetails extends BaseActivity {

    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    private RATicketType1 raTicketType1;
    private RecyclerView rvTicketType;
    
    private ImageView ivEventImage;
    private TextView tvEventDate;
    private TextView tvEventDescription;
    private TextView tvEventName;
    private TextView tvEventTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_event_details);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        Event event = (Event) getIntent().getSerializableExtra("event");

        captureViewComponents();

        rvTicketType.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rvTicketType.setLayoutManager(layoutManager);

        List<TicketType> ticketTypes = event.getTicket_types();

        ivEventImage.setContentDescription(event.getDescription());
        Picasso.get().load(Constants.URL + event.getImageUrl()).into(ivEventImage);

        tvEventDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(event.getDate().getTime()));
        tvEventDescription.setText(event.getDescription());
        tvEventName.setText(event.getName());
        tvEventTime.setText(new SimpleDateFormat("HH:mm").format(event.getDate().getTime()));

        raTicketType1 = new RATicketType1(ticketTypes);
        rvTicketType.setAdapter(raTicketType1);
    }

    private void captureViewComponents() {
        ivEventImage = findViewById(R.id.ivEventImage);

        rvTicketType = findViewById(R.id.rvTicketType);

        tvEventDate = findViewById(R.id.tvEventDate);
        tvEventDescription = findViewById(R.id.tvEventDescription);
        tvEventName = findViewById(R.id.tvEventName);
        tvEventTime = findViewById(R.id.tvEventTime);
    }

    public void insertPaymentData(View view) {
        List<Pair> pairList = raTicketType1.getInformations();

        if (!pairList
                .stream()
                .filter(pair ->
                        pair.getAmount() > 0)
                .collect(Collectors.toList())
                .isEmpty()) {

            if (super.getCustomMenu().isNotLogged()) {
                startActivityForResult(new Intent(this, Login.class), 0);
            } else {
                Intent intent = new Intent(this, PaymentData.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("pairList", (Serializable) pairList);
                bundle.putSerializable("event", getIntent().getSerializableExtra("event"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, R.string.toast_unfilled_amount, Toast.LENGTH_SHORT).show();
        }
    }

}
