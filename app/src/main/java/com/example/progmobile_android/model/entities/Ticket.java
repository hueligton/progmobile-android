package com.example.progmobile_android.model.entities;

import java.util.Date;

public class Ticket {

    private Event event;
    private int id;
    private String validationHash;
    private boolean validated;
    private Date validatedDate;
    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket (int ticketId, Event event, String validationHash, boolean validated, Date validatedDate, TicketType ticketType){
        this.id = ticketId;
        this.event=event;
        this.validationHash = validationHash;
        this.validated = validated;
        this.validatedDate = validatedDate;
        this.ticketType=ticketType;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValidationHash(String validationHash) {
        this.validationHash = validationHash;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void setValidatedDate(Date validatedDate) {
        this.validatedDate = validatedDate;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
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
                ", ticketId=" + id +
                ", validationHash='" + validationHash + '\'' +
                ", validated=" + validated +
                ", validatedDate=" + validatedDate +
                ", ticketType=" + ticketType +
                '}';
    }
}
