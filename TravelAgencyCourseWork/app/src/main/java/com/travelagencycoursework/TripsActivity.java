package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
    }

    public void buttonAdd_Click(View view) {
        Intent intent = new Intent(this, TripActivity.class);
        startActivity(intent);
    }

    public void buttonDelete_Click(View view) {
    }

    public void buttonRefactor_Click(View view) {
        Intent intent = new Intent(this, TripActivity.class);
        startActivity(intent);
    }
}