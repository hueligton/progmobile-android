package com.example.progmobile_android.model.entities;

import java.util.Date;

public class Purchase {

    private int purchaseId;
    private Date date;
    private double value;
    private PaymentStatus paymentStatus;
    private Ticket ticket;

    public Purchase (int purchaseId, Date date, double value, PaymentStatus paymentStatus, Ticket ticket){
        this.purchaseId = purchaseId;
        this.date = date;
        this.value = value;
        this.paymentStatus = paymentStatus;
        this.ticket = ticket;
    }

    public Purchase() {

    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", date=" + date +
                ", value=" + value +
                ", paymentStatus=" + paymentStatus +
                ", ticket=" + ticket +
                '}';
    }
}
