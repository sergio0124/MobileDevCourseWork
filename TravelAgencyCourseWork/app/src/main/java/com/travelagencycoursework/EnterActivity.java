package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.travelagencycoursework.R;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
    }

    public void textViewRegistration_Click(View view) {
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }

    public void buttonEntry_Click(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}