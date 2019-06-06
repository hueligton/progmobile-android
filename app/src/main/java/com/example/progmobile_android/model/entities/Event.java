package com.example.progmobile_android.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {

    private int id;
    private String name;
    private String description;
    private Date date;
    private String imageUrl;
    private Place place;
    private List<TicketType> ticket_types;

    public Event() {
    }

    public Event(int eventId, String name, String description, Date date, String image, Place place, List<TicketType> ticket_types) {
        this.id = eventId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.place = place;
        this.imageUrl = image;
        this.ticket_types = ticket_types;
    }

    public int getId() {
        return id;
    }

    public void setId(int eventId) {
        this.id = eventId;
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

    public List<TicketType> getTicket_types() {
        return ticket_types;
    }

    public void setTicket_types(List<TicketType> ticket_types) {
        this.ticket_types = ticket_types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void getImageUrl(String image) {
        this.imageUrl = image;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", place=" + place +
                ", imageURL='" + imageUrl + '\'' +
                ", ticket_types=" + ticket_types +
                '}';
    }
}
