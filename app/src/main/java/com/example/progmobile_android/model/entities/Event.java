package com.example.progmobile_android.model.entities;

import java.util.Date;
import java.util.List;

public class Event {

    private int eventId;
    private String name;
    private Date date;
    private Place place;
    private List<TicketType> ticketTypes;

    public Event (int eventId, String name, Date date, Place place, List<TicketType> ticketTypes){
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.place = place;
        this.ticketTypes = ticketTypes;
    }

    public Event() {

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", place=" + place +
                ", ticketTypes=" + ticketTypes +
                '}';
    }
}
