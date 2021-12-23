package com.travelagencycoursework.database.models;

import com.google.firebase.database.Exclude;

import java.util.Calendar;

public class TripModel {

    int id;
    String name;
    int userId;
    String userLogin;
    String guideName;
    String placeName;
    Calendar date;
    double placePrice = 0;
    double guidePrice = 0;
    double price = 0;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPlacePrice() {
        return placePrice;
    }

    public double getGuidePrice() {
        return guidePrice;
    }

    public double getPrice() {
        if (price == 0) {
            return placePrice + guidePrice;
        } else {
            return price;
        }
    }

    public void setPlacePrice(double placePrice) {
        this.placePrice = placePrice;
    }

    public void setGuidePrice(double guidePrice) {
        this.guidePrice = guidePrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Exclude
    public Calendar getDate() {
        return date;
    }

    @Exclude
    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getGuideName() {
        return guideName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
