package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.travelagencycoursework.database.logics.TripLogic;
import com.travelagencycoursework.database.models.TripModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    Button button_from;
    Button button_to;
    Button button_create;

    Date dateFrom;
    Date dateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        button_create = findViewById(R.id.button_create_report);
        button_create.setOnClickListener(v -> {
            Report report = new Report();
            TripLogic logic = new TripLogic(this);
            List<TripModel> trips = logic.getFullList();
            List<TripModel> result_trips = new ArrayList<>();
            String login = getIntent().getStringExtra("userLogin");
            int id = getIntent().getIntExtra("userId", 0);

            for (TripModel trip : trips
            ) {
                if (trip.getDate().getTime().after(dateFrom) &&
                        trip.getDate().getTime().before(dateTo) && trip.getUserLogin().equals(login)) {
                    result_trips.add(trip);
                }
            }
            try {
                report.generatePdf(result_trips, dateFrom, dateTo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userId", id);
            intent.putExtra("userLogin", login);
            startActivity(intent);
            return;
        });


        button_from = findViewById(R.id.button_datefrom);
        button_from.setOnClickListener(v -> {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar date = new GregorianCalendar();
                    date.set(year, monthOfYear + 1, dayOfMonth);
                    String text = date.getTime().getDate() + " / " +
                            date.getTime().getMonth() + " / " + (date.getTime().getYear() + 1900);
                    button_from.setText(text);
                    dateFrom = date.getTime();
                }
            };
            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, 2021, 0, 1);

            datePickerDialog.show();
        });


        button_to = findViewById(R.id.button_dateto);
        button_to.setOnClickListener(v -> {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar date = new GregorianCalendar();
                    date.set(year, monthOfYear + 1, dayOfMonth);
                    String text = date.getTime().getDate() + " / " +
                            date.getTime().getMonth() + " / " + (date.getTime().getYear() + 1900);
                    button_to.setText(text);
                    dateTo = date.getTime();
                }
            };
            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, 2021, 0, 1);

            datePickerDialog.show();
        });
    }
}