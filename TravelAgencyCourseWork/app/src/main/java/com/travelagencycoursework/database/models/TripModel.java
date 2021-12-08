package com.travelagencycoursework.database.models;

import java.util.Calendar;

public class TripModel {

    int id;
    String name;
    int userId;
    String userLogin;
    int guideId;
    String guideName;
    int placeId;
    String placeName;
    Calendar date;
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

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

    public int getGuideId() {
        return guideId;
    }

    public String getGuideName() {
        return guideName;
    }

    public int getPlaceId() {
        return placeId;
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

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
