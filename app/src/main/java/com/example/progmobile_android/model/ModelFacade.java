package com.example.progmobile_android.model;

import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Ticket;
import com.example.progmobile_android.model.entities.UserToken;

import java.util.ArrayList;
import java.util.List;

public class ModelFacade {

    public UserToken login(String login, String password){
        return new UserToken(new User(login, "Felipe", password, "teste@email.com"), "abc");
    }

    public void logout(String userId, String token){

    }

    public User createUser(String login, String name, String password, String email){
        return new User(login, name, password, email);
    }

    public List<Event> listEvents(){
        return new ArrayList<Event>();
    }

    public Event getEvent(int eventId){
        return new Event();
    }

    public List<Purchase> listPurchases(String userId, String token){
        return new ArrayList<Purchase>();
    }

    public Purchase setPurchase(String userId, String token, int eventId, List<Pair> list){
        return new Purchase();
    }

    public Purchase getPurchase(String userId, String token, int purchaseId){
        return new Purchase();
    }

    public Ticket getTicket(String userId, String token, int ticketId){
        return new Ticket();
    }
}
