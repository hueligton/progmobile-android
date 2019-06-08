package com.example.progmobile_android.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

    private int id;
    private String validation_hash;
    private boolean validated;
    private Date validated_date;
    private int ticketTypeId;
    private TicketType ticketType;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValidation_hash() {
        return validation_hash;
    }

    public void setValidation_hash(String validation_hash) {
        this.validation_hash = validation_hash;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Date getValidated_date() {
        return validated_date;
    }

    public void setValidated_date(Date validated_date) {
        this.validated_date = validated_date;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", validation_hash='" + validation_hash + '\'' +
                ", validated=" + validated +
                ", validated_date=" + validated_date +
                ", ticketTypeId=" + ticketTypeId +
                ", ticketType=" + ticketType +
                '}';
    }
}
