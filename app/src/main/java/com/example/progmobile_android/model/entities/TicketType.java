package com.example.progmobile_android.model.entities;

public class TicketType {
    private int id;
    private String type;
    private double price;

    public TicketType() {
    }

    public TicketType(int ticketTypeId, String type, double price){
        this.id = ticketTypeId;
        this.type = type;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
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
                "ticketTypeId=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
