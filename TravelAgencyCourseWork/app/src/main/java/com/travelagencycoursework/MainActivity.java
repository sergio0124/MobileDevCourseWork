package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.travelagencycoursework.R;
import com.travelagencycoursework.database.fireboxlogic.TripFirebaseLogic;
import com.travelagencycoursework.database.logics.TripLogic;
import com.travelagencycoursework.database.logics.UserLogic;
import com.travelagencycoursework.database.models.TripModel;
import com.travelagencycoursework.database.models.UserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView nameTextView;
    UserModel user;
    TripLogic tripLogic;
    UserLogic userLogic;
    TableRow selectedRow;
    Button button_create;
    Button button_delete;
    Button button_report;
    int INDEX_OF_ID_CELL = 5;
    TripFirebaseLogic tripFirebaseLogic;

    @Override
    public void onResume() {
        super.onResume();
        tripLogic.open();
        TripModel tripModel = new TripModel();
        tripModel.setUserId(user.getId());
        tripModel.setUserLogin(user.getLogin());
        List<TripModel> list = tripLogic.getFullList();
        List<TripModel> result_list = new ArrayList<>();
        for (TripModel trip : list
        ) {
            if (trip.getUserLogin().equals(tripModel.getUserLogin())) {
                result_list.add(trip);
            }
        }
        fillTable(Arrays.asList("Название", "Место", "Гид", "Цена", "Дата"), result_list);
        tripLogic.close();
        tripFirebaseLogic.syncTrips(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tripLogic = new TripLogic(this);
        userLogic = new UserLogic(this);
        tripFirebaseLogic = new TripFirebaseLogic();

        String login = getIntent().getStringExtra("userLogin");
        UserModel userModel = new UserModel();
        userLogic.open();
        userModel.setLogin(login);
        user = userLogic.getElement(userModel);
        userLogic.close();

        nameTextView = findViewById(R.id.loginText);
        button_delete = findViewById(R.id.button_delete);
        button_create = findViewById(R.id.button_create);
        button_report = findViewById(R.id.button_report);
        nameTextView.setText(login);

        button_create.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, TripActivity.class);
                    intent.putExtra("userId", user.getId());
                    intent.putExtra("userLogin", user.getLogin());
                    startActivity(intent);
                }
        );

        button_delete.setOnClickListener(v -> {
                if (selectedRow != null) {
                    tripLogic.open();
                    TextView textView = (TextView) selectedRow.getChildAt(INDEX_OF_ID_CELL);
                    TripModel model = new TripModel();
                    model.setId(Integer.valueOf(textView.getText().toString()));
                    model.setUserId(user.getId());
                    model.setUserLogin(user.getLogin());
                    tripLogic.delete(model);
                    List<TripModel> list = tripLogic.getFullList();
                    List<TripModel> result_list = new ArrayList<>();
                    for (TripModel trip : list
                    ) {
                        if (trip.getUserLogin().equals(model.getUserLogin())) {
                            result_list.add(trip);
                        }
                    }
                    fillTable(Arrays.asList("Название", "Место", "Гид", "Цена", "Дата"), result_list);
                    tripLogic.close();
                    selectedRow = null;
                }
            }
        );

        button_report.setOnClickListener(v->{
            Intent intent = new Intent(this, ReportActivity.class);
            intent.putExtra("userId", user.getId());
            intent.putExtra("userLogin", user.getLogin());
            startActivity(intent);
        });
    }

    void fillTable(List<String> titles, List<TripModel> trips) {

        TableLayout tableLayoutTrips = findViewById(R.id.tableTrips);
        tableLayoutTrips.removeAllViews();

        TableRow tableRowTitles = new TableRow(this);

        for (String title : titles) {
            TextView textView = new TextView(this);

            textView.setTextSize(16);
            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth((int) (getWindowManager().getDefaultDisplay().getWidth() / titles.size() + 0.2));
            tableRowTitles.addView(textView);
        }

        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayoutTrips.addView(tableRowTitles);


        for (TripModel trip : trips) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(trip.getName());
            textViewName.setHeight(100);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewPlace = new TextView(this);
            textViewPlace.setHeight(100);
            textViewPlace.setTextSize(16);
            textViewPlace.setText(trip.getPlaceName());
            textViewPlace.setTextColor(Color.WHITE);
            textViewPlace.setGravity(Gravity.CENTER);

            TextView textViewGuide = new TextView(this);
            textViewGuide.setHeight(100);
            textViewGuide.setTextSize(16);
            textViewGuide.setText(String.valueOf(trip.getGuideName()));
            textViewGuide.setTextColor(Color.WHITE);
            textViewGuide.setGravity(Gravity.CENTER);

            TextView textViewPrice = new TextView(this);
            textViewPrice.setHeight(100);
            textViewPrice.setTextSize(16);
            textViewPrice.setText(String.valueOf(trip.getPrice()));
            textViewPrice.setTextColor(Color.WHITE);
            textViewPrice.setGravity(Gravity.CENTER);

            TextView textViewDate = new TextView(this);
            textViewDate.setHeight(100);
            textViewDate.setTextSize(16);
            Calendar date = trip.getDate();
            String text = date.getTime().getDate() + "/" +
                    date.getTime().getMonth() + "/" + (date.getTime().getYear()+ 1900);
            textViewDate.setText(text);
            textViewDate.setTextColor(Color.WHITE);
            textViewDate.setGravity(Gravity.CENTER);

            TextView textViewId = new TextView(this);
            textViewId.setVisibility(View.INVISIBLE);
            textViewId.setText(String.valueOf(trip.getId()));

            tableRow.addView(textViewName);
            tableRow.addView(textViewPlace);
            tableRow.addView(textViewGuide);
            tableRow.addView(textViewPrice);
            tableRow.addView(textViewDate);
            tableRow.addView(textViewId);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(v -> {

                selectedRow = tableRow;

                for (int i = 0; i < tableLayoutTrips.getChildCount(); i++) {
                    View view = tableLayoutTrips.getChildAt(i);
                    if (view instanceof TableRow) {
                        view.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    }
                }

                tableRow.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            });

            tableLayoutTrips.addView(tableRow);
        }
    }
}