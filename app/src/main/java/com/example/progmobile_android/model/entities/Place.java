package com.example.progmobile_android.model.entities;

public class Place {

    private int placeId;
    private String name;
    private String city;
    private String state;
    private String country;
    private String address;
    private double latitude;
    private double longitude;

    public Place(int placeId, String name, String city, String state, String country, String address, double latitude, double longitude){
        this.placeId = placeId;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
