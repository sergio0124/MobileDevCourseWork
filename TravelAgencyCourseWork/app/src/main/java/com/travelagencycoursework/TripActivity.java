package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.travelagencycoursework.database.models.TripModel;

import java.util.Calendar;

public class TripActivity extends AppCompatActivity {

    FragmentManager fm;
    Fragment mainTripFragment = new MainTripFragment();
    TripModel tripModel = new TripModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        String login = getIntent().getStringExtra("userLogin");
        tripModel.setUserLogin(login);
        int userId = getIntent().getIntExtra("userId",0);
        tripModel.setUserId(userId);
        int id = getIntent().getIntExtra("userId",0);
        tripModel.setUserId(id);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, mainTripFragment);
        ft.commit();
    }

    public void setPlaceData(String name, double price){
        tripModel.setPlaceName(name);
        tripModel.setPlacePrice(price);
    }

    public void setGuideData(String name, double price){
        tripModel.setGuideName(name);
        tripModel.setGuidePrice(price);
    }

    public void setTripDate(Calendar date){
        tripModel.setDate(date);
    }

    public TripModel getTripModel(){
        return tripModel;
    }
}