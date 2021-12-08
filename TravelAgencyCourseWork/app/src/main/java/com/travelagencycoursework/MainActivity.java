package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.travelagencycoursework.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buttonGuides_Click(View view) {
        Intent intent = new Intent(this,GuidesActivity.class);
        startActivity(intent);
    }

    public void buttonTrips_Click(View view) {
        Intent intent = new Intent(this,TripsActivity.class);
        startActivity(intent);
    }

    public void buttonPlaces_Click(View view) {
        Intent intent = new Intent(this,PlacesActivity.class);
        startActivity(intent);
    }
}