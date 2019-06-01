package com.example.progmobile_android.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventList extends AppCompatActivity {

    ManagerFacade managerFacade = new ManagerFacade(this);

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

        managerFacade.getListEvents(this::onSuccess);

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

    private void onSuccess(Object object) {
        @SuppressWarnings("unchecked")
        List<Event> list = (List<Event>) object;

        List<String> eventImage = new ArrayList<>();
        List<String> eventName = new ArrayList<>();

        list.forEach(event -> {
            eventImage.add(event.getImage());
            eventName.add(event.getImage());
        });

        eventImage.add("http://caliescribe.com/sites/default/files/imagenes_revista/2011/noviembre/13-19/gastronomia/gastronomia-minimalista.jpg");
        eventImage.add("https://img.pystatic.com/header-backgrounds/mobile/comida-internacional-6.jpg");

        eventName.add("food 1");
        eventName.add("food 2");

        EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(eventImage, eventName);
        recyclerView.setAdapter(eventRecyclerAdapter);
    }

}
