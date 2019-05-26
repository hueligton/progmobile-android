package com.example.progmobile_android.model.entities;

import java.util.Date;
import java.util.List;

public class Event {

    private int eventId;
    private String name;
    private String description;
    private Date date;
    private Place place;
    private String image;
    private List<TicketType> ticketTypes;

    public Event(int eventId, String name, String description, Date date, Place place, String image, List<TicketType> ticketTypes) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.place = place;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
