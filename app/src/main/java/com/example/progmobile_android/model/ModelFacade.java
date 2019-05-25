package com.example.progmobile_android.model;

import android.content.Context;

import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Ticket;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.manager.EventManager;
import com.example.progmobile_android.model.manager.PurchaseManager;
import com.example.progmobile_android.model.manager.UserManager;
import java.util.List;

public class ModelFacade {
    private EventManager eventManager;
    private UserManager userManager;
    private PurchaseManager purchaseManager;

    public ModelFacade(Context context) {
        this.eventManager=new EventManager(context);
        this.userManager=new UserManager(context);
        this.purchaseManager=new PurchaseManager(context);
    }

    public UserToken login(String login, String password) {
        return userManager.login(login,password);
    }

    public void logout(String userId, String token) {
        userManager.logout(userId, token);
    }

    public User createUser(String login, String name, String password, String email) {
        return userManager.createUser(login, name, password, email);
    }

    public List<Event> getListEvents() {
        return eventManager.getListEvents();
    }

    public List<Event> searchEventByName(String name) {
        return eventManager.searchEventByName(name);
    }

    public Event getEvent(int eventId) {
        return eventManager.getEvent(eventId);
    }

    public List<Purchase> getListPurchases(String userId, String token) {
        return purchaseManager.getListPurchases(userId, token);
    }

    public Purchase setPurchase(String userId, String token, int eventId, List<Pair> list) {
        return purchaseManager.setPurchase(userId,token,eventId,list);
    }

    public Purchase getPurchase(String userId, String token, int purchaseId) {
        return purchaseManager.getPurchase(userId, token, purchaseId);
    }

    public Ticket getTicket(String userId, String token, int ticketId) {
        return purchaseManager.getTicket(userId, token, ticketId);
    }
}
