package com.example.progmobile_android.model.entities;

import java.util.Date;

public class Purchase {

    private int id;
    private Date date;
    private double value;
    private PaymentStatus paymentStatus;

    public Purchase() {
    }

    public Purchase(int purchaseId, Date date, double value, PaymentStatus paymentStatus) {
        this.id = purchaseId;
        this.date = date;
        this.value = value;
        this.paymentStatus = paymentStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + id +
                ", date=" + date +
                ", value=" + value +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
