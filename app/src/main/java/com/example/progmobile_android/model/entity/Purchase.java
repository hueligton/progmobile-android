package com.example.progmobile_android.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Purchase implements Serializable {

    private int id;
    private Date date;
    private double total_value;
    private PaymentStatus payment_status;
    private List<Ticket> tickets;
    private Event event;
    private int userId;

    public Purchase() {
    }

    public Purchase(int id, Date date, double total_value, PaymentStatus payment_status, List<Ticket> tickets, Event event, int userId) {
        this.id = id;
        this.date = date;
        this.total_value = total_value;
        this.payment_status = payment_status;
        this.tickets = tickets;
        this.event = event;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal_value(double total_value) {
        this.total_value = total_value;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getTotal_value() {
        return total_value;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date=" + date +
                ", total_value=" + total_value +
                ", payment_status=" + payment_status +
                ", tickets=" + tickets +
                ", event=" + event +
                ", userId=" + userId +
                '}';
    }
}
