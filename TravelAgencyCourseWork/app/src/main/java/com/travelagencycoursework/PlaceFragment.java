package com.travelagencycoursework;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.travelagencycoursework.database.fireboxlogic.PlaceFireboxLogic;
import com.travelagencycoursework.database.models.PlaceModel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlaceFragment extends Fragment {

    Button buttonPlace;
    TableRow selectedRow;
    PlaceFireboxLogic placeFireboxLogic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place, container, false);
        initializeElements(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        placeFireboxLogic.fillData();
    }

    private void initializeElements(View view) {
        buttonPlace = view.findViewById(R.id.buttonAdd);
        placeFireboxLogic = new PlaceFireboxLogic();

        buttonPlace.setOnClickListener(v -> {
            if (selectedRow != null) {
                TripActivity tripActivity = (TripActivity) getActivity();
                tripActivity.setPlaceData(String.valueOf(((TextView) selectedRow.getChildAt(0)).getText()),
                        Double.parseDouble(String.valueOf(((TextView) selectedRow.getChildAt(1)).getText())));
                selectedRow = null;
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button buttonRefresh = view.findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(v -> {
            fillTable(Arrays.asList("Название", "Цена"),
                    placeFireboxLogic.getFullList());
        });
    }

    void fillTable(List<String> titles, List<PlaceModel> places) {

        View view = getView();
        TableLayout tableLayoutTrips = view.findViewById(R.id.tableLayoutPlaces);
        tableLayoutTrips.removeAllViews();

        TableRow tableRowTitles = new TableRow(view.getContext());

        for (String title : titles) {
            TextView textView = new TextView(view.getContext());

            textView.setTextSize(16);
            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth((int) (view.getWidth() / titles.size() + 0.2));
            tableRowTitles.addView(textView);
        }

        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayoutTrips.addView(tableRowTitles);


        for (PlaceModel medicine : places) {
            TableRow tableRow = new TableRow(view.getContext());

            TextView textViewName = new TextView(view.getContext());
            textViewName.setText(medicine.getName());
            textViewName.setHeight(100);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewPrice = new TextView(view.getContext());
            textViewPrice.setHeight(100);
            textViewPrice.setTextSize(16);
            textViewPrice.setText(String.valueOf(medicine.getPrice()));
            textViewPrice.setTextColor(Color.WHITE);
            textViewPrice.setGravity(Gravity.CENTER);

            tableRow.addView(textViewName);
            tableRow.addView(textViewPrice);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(v -> {

                selectedRow = tableRow;

                for (int i = 0; i < tableLayoutTrips.getChildCount(); i++) {
                    View view1 = tableLayoutTrips.getChildAt(i);
                    if (view1 instanceof TableRow) {
                        view1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    }
                }

                tableRow.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            });

            tableLayoutTrips.addView(tableRow);
        }
    }
}