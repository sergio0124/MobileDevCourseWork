package com.travelagencycoursework.database.models;

public class PlaceModel {
    int id;
    String description;
    String address;

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
