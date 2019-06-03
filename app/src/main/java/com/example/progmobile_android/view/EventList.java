package com.example.progmobile_android.view;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.RVEvent;

import java.util.List;
import java.util.Objects;

public class EventList extends BaseActivity {

    private RVEvent rvEvent;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_event_list);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        Context context = this;
        super.getManagerFacade().getListEvents(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                List<Event> list = (List<Event>) object;
                rvEvent = new RVEvent(list, context);
                recyclerView.setAdapter(rvEvent);
            }

            @Override
            public void onError(Object object) {

            }
        });
    }
}
