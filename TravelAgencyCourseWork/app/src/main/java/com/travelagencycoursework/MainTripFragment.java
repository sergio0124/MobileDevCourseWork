package com.travelagencycoursework;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.travelagencycoursework.database.logics.TripLogic;
import com.travelagencycoursework.database.models.TripModel;
import com.travelagencycoursework.database.models.UserModel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainTripFragment extends Fragment {

    EditText tripNameEditText;
    public TextView guideNameTextView;
    public TextView guidePriceTextView;
    public TextView placeNameTextView;
    public TextView placePriceTextView;

    Button buttonGuide;
    Button buttonPlace;
    Button buttonDate;
    Button buttonSave;

    Calendar date = new GregorianCalendar();


    @Override
    public void onResume() {
        super.onResume();
        TripModel tripModel = ((TripActivity)getActivity()).getTripModel();
        guideNameTextView.setText(tripModel.getGuideName());
        guidePriceTextView.setText(tripModel.getGuidePrice() + "руб.");
        placePriceTextView.setText(tripModel.getPlacePrice() + "руб.");
        placeNameTextView.setText(tripModel.getPlaceName());
        Calendar date = tripModel.getDate();
        String text = date==null? "Choose date" : date.getTime().getDate() + " / " +
                date.getTime().getMonth() + " / " + (date.getTime().getYear()+ 1900);
        buttonDate.setText(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_trip, null);
        initializeElements(view);
        return view;
    }

    private void initializeElements(View view) {

        tripNameEditText = view.findViewById(R.id.nameEditText);
        guideNameTextView = view.findViewById(R.id.guideTextView);
        guidePriceTextView = view.findViewById(R.id.guidePriceTextView);
        placeNameTextView = view.findViewById(R.id.placeTextView);
        placePriceTextView = view.findViewById(R.id.placePriceTextView);

        buttonDate = view.findViewById(R.id.buttonDate);
        buttonGuide = view.findViewById(R.id.button_guide);
        buttonPlace = view.findViewById(R.id.button_place);
        buttonSave = view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v->{
            TripModel tripModel = ((TripActivity)getActivity()).getTripModel();
            TextView textView = getView().findViewById(R.id.nameEditText);
            tripModel.setName(String.valueOf(textView.getText()));
            TripLogic tripLogic = new TripLogic(getContext());
            tripLogic.open();
            tripLogic.insert(tripModel);
            tripLogic.close();

            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("userId", tripModel.getUserId());
            intent.putExtra("userLogin", tripModel.getUserLogin());
            startActivity(intent);
            return;
        });

        buttonDate.setOnClickListener(
                v -> {
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.set(year, monthOfYear + 1, dayOfMonth);
                            String text = date.getTime().getDate() + " / " +
                                    date.getTime().getMonth() + " / " + (date.getTime().getYear()+ 1900);
                            buttonDate.setText(text);
                            TripActivity tripActivity = (TripActivity) getActivity();
                            tripActivity.setTripDate(date);
                        }
                    };
                    DatePickerDialog datePickerDialog;
                    datePickerDialog = new DatePickerDialog(view.getContext(),
                            android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                            dateSetListener, 2021, 0, 1);

                    datePickerDialog.show();
                }
        );

        buttonPlace.setOnClickListener(v->{
            PlaceFragment placeFragment = new PlaceFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, placeFragment, null)
                    .addToBackStack("")
                    .commit();
        });

        buttonGuide.setOnClickListener(v->{
            GuideFragment guideFragment = new GuideFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, guideFragment, null)
                    .addToBackStack("")
                    .commit();
        });
    }
}