package com.example.progmobile_android.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SearchView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entity.Event;
import com.example.progmobile_android.model.util.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.RAEvent;

import java.util.List;
import java.util.Objects;

public class EventList extends BaseActivity implements SearchView.OnQueryTextListener {

    private RAEvent raEvent;
    private RecyclerView rvEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_event_list);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        rvEventList = findViewById(R.id.rvEventList);
        rvEventList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rvEventList.setLayoutManager(layoutManager);

        Context context = this;
        super.getManagerFacade().getListEvents(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                List<Event> list = (List<Event>) object;
                raEvent = new RAEvent(list, context);
                rvEventList.setAdapter(raEvent);
            }

            @Override
            public void onError(Object object) {}
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String input) {
        List<Event> events = getManagerFacade().searchEventByName(input);
        raEvent.updateList(events);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
}
