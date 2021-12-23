package com.travelagencycoursework;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.travelagencycoursework.database.fireboxlogic.GuideFireboxLogic;
import com.travelagencycoursework.database.fireboxlogic.PlaceFireboxLogic;
import com.travelagencycoursework.database.models.GuideModel;
import com.travelagencycoursework.database.models.PlaceModel;

import java.util.Arrays;
import java.util.List;

public class GuideFragment extends Fragment {

    Button buttonGuide;
    TableRow selectedRow;
    GuideFireboxLogic guideFireboxLogic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        initializeElements(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        guideFireboxLogic.fillData();
    }

    private void initializeElements(View view) {
        buttonGuide = view.findViewById(R.id.buttonAdd);
        guideFireboxLogic = new GuideFireboxLogic();

        buttonGuide.setOnClickListener(v -> {
            if (selectedRow != null) {
                TripActivity tripActivity = (TripActivity) getActivity();
                tripActivity.setGuideData(String.valueOf(((TextView) selectedRow.getChildAt(0)).getText()),
                        Double.parseDouble(String.valueOf(((TextView) selectedRow.getChildAt(1)).getText())));
                selectedRow = null;
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button buttonRefresh = view.findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(v -> {
            fillTable(Arrays.asList("Имя гида", "Цена"),
                    guideFireboxLogic.getFullList());
        });
    }

    void fillTable(List<String> titles, List<GuideModel> guides) {

        View view = getView();
        TableLayout tableLayoutTrips = view.findViewById(R.id.tableLayoutGuides);
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


        for (GuideModel medicine : guides) {
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