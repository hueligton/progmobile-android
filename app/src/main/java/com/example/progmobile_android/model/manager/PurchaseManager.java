package com.example.progmobile_android.model.manager;

import android.content.Context;

import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.Ticket;
import com.example.progmobile_android.model.repository.ServerCallback;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManager {
    private Context context;

    public PurchaseManager(Context context){
        this.context=context;
    }

    public void getListPurchases(String userId, String token, final ServerCallback serverCallback) {

    }

    public void setPurchase(String userId, String token, int eventId, List<Pair> list, final ServerCallback serverCallback) {

    }

    public void getPurchase(String userId, String token, int purchaseId, final ServerCallback serverCallback) {

    }

    public void getTicket(String userId, String token, int ticketId, final ServerCallback serverCallback) {

    }
}
