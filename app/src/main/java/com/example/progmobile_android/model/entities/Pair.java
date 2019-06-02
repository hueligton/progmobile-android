package com.example.progmobile_android.model.entities;

import java.io.Serializable;

public class Pair implements Serializable {
    private int eventTypeId;
    private int amount;

    public Pair(int eventTypeId, int amount) {
        this.eventTypeId = eventTypeId;
        this.amount = amount;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
