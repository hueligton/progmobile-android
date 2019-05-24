package com.example.progmobile_android.model.entities;

public class TicketType {
    private int ticketTypeId;
    private String type;
    private double price;

    public TicketType(int ticketTypeId, String type, double price){
        this.ticketTypeId = ticketTypeId;
        this.type = type;
        this.price = price;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "ticketTypeId=" + ticketTypeId +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
