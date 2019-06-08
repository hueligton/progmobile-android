package com.example.progmobile_android.model.entity;

import java.io.Serializable;

public class TicketType implements Serializable {
    private int id;
    private String name;
    private double price;

    public TicketType() {
    }

    public TicketType(int ticketTypeId, String name, double price) {
        this.id = ticketTypeId;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "ticketTypeId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
