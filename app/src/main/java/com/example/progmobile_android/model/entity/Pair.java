package com.example.progmobile_android.model.entity;

import java.io.Serializable;

public class Pair implements Serializable {
    private int ticketTypeId;
    private int amount;

    public Pair(int ticketTypeId, int amount) {
        this.ticketTypeId = ticketTypeId;
        this.amount = amount;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
