package com.example.progmobile_android.model.manager;

import android.content.Context;

import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.Ticket;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManager {
    private Context context;

    public PurchaseManager(Context context){
        this.context=context;
    }

    public List<Purchase> getListPurchases(String userId, String token) {
        return new ArrayList<Purchase>();
    }

    public Purchase setPurchase(String userId, String token, int eventId, List<Pair> list) {
        return new Purchase();
    }

    public Purchase getPurchase(String userId, String token, int purchaseId) {
        return new Purchase();
    }

    public Ticket getTicket(String userId, String token, int ticketId) {
        return new Ticket();
    }
}
