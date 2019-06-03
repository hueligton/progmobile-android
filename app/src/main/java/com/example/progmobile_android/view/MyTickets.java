package com.example.progmobile_android.view;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.PaymentStatus;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.example.progmobile_android.view.RecyclerAdapter.RVPurchase;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MyTickets extends AppCompatActivity {

    ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    private RVPurchase rvPurchase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_my_tickets);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rvTickets);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Context context = this;

        managerFacade.getUser(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                UserToken userToken = (UserToken) object;
                int userId = userToken.getUser().getId();
                String token = userToken.getToken();

                managerFacade.getListPurchases(userId, token, new ServerCallback() {
                    @Override
                    public void onSuccess(Object object) {
                        List<Purchase> list = (List<Purchase>) object;

                        rvPurchase = new RVPurchase(list, context);
                        recyclerView.setAdapter(rvPurchase);
                    }

                    @Override
                    public void onError(Object object) {

                    }
                });

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
