package com.travelagencycoursework.database.fireboxlogic;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.travelagencycoursework.database.logics.TripLogic;
import com.travelagencycoursework.database.logics.UserLogic;
import com.travelagencycoursework.database.models.TripModel;
import com.travelagencycoursework.database.models.UserModel;

import java.util.List;

public class TripFirebaseLogic {


    private final String TABLE = "trip";

    private DatabaseReference database;

    public TripFirebaseLogic(){
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }

    public void syncTrips(Context context) {
        TripLogic logic = new TripLogic(context);

        logic.open();
        List<TripModel> models = logic.getFullList();
        logic.close();

        database.removeValue();

        for (TripModel model: models) {
            database.push().setValue(model);
        }
    }
}
