package com.example.progmobile_android.view;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.EventRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventList extends AppCompatActivity {

    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

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
        managerFacade.getListEvents(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                List<Event> list = (List<Event>) object;

                List<Integer> eventId = new ArrayList<>();
                List<String> eventImage = new ArrayList<>();
                List<String> eventName = new ArrayList<>();

                list.forEach(event -> {
                    eventId.add(event.getId());
                    eventImage.add(event.getImageURL());
                    eventName.add(event.getName());
                });

                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(eventId, eventImage, eventName, context);
                recyclerView.setAdapter(eventRecyclerAdapter);
            }

            @Override
            public void onError(Object object) {

            }
        });

        /* for unsuccessful managerFacade.getListEvents

        List<String> eventImage = new ArrayList<>();
        List<String> eventName = new ArrayList<>();

        eventImage.add("http://caliescribe.com/sites/default/files/imagenes_revista/2011/noviembre/13-19/gastronomia/gastronomia-minimalista.jpg");
        eventImage.add("https://img.pystatic.com/header-backgrounds/mobile/comida-internacional-6.jpg");

        eventName.add("food 1");
        eventName.add("food 2");

        EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(eventImage, eventName);
        recyclerView.setAdapter(eventRecyclerAdapter);

        */
    }

    @Override
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
