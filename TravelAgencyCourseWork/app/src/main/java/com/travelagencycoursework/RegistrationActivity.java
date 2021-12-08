package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void buttonRegister_Click(View view) {
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
}