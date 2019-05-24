package com.example.progmobile_android.model.entities;

import java.util.Date;

public class Ticket {

    private Event event;
    private int ticketId;
    private String validationHash;
    private boolean validated;
    private Date validatedDate;
    private TicketType ticketType;

    public Ticket (int ticketId, Event event, String validationHash, boolean validated, Date validatedDate, TicketType ticketType){
        this.ticketId = ticketId;
        this.event=event;
        this.validationHash = validationHash;
        this.validated = validated;
        this.validatedDate = validatedDate;
        this.ticketType=ticketType;
    }

    public Ticket() {

    }

    public int getTicketId() {
        return ticketId;
    }

    public String getValidationHash() {
        return validationHash;
    }

    public boolean isValidated() {
        return validated;
    }

    public Date getValidatedDate() {
        return validatedDate;
    }

    public Event getEvent() {
        return event;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event=" + event +
                ", ticketId=" + ticketId +
                ", validationHash='" + validationHash + '\'' +
                ", validated=" + validated +
                ", validatedDate=" + validatedDate +
                ", ticketType=" + ticketType +
                '}';
    }
}
