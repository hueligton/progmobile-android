package com.example.progmobile_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.repository.ServerCallback;

public class Home extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        setSupportActionBar(findViewById(R.id.toolbar));

    }

    public void buyTickets(View view) {
        startActivity(new Intent(this, EventList.class));
    }

    public void showMyTickets(View view) {
        // startActivity(new Intent(this, MyTickets.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.getManagerFacade().logout(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                Home.super.getCustomMenu().updateMenu(getMenu(), true);
            }

            @Override
            public void onError(Object object) {
            }
        });
    }
}
