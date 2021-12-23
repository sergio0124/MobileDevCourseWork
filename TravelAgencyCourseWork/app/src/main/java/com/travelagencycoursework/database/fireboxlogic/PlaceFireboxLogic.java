package com.travelagencycoursework.database.fireboxlogic;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.travelagencycoursework.database.models.PlaceModel;

import java.util.ArrayList;
import java.util.List;

public class PlaceFireboxLogic {

    private final String TABLE = "place";
    List<PlaceModel> result = new ArrayList<PlaceModel>();

    private DatabaseReference database;

    public PlaceFireboxLogic() {
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }


    public void fillData(){
        result.clear();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PlaceModel model = ds.getValue(PlaceModel.class);
                    assert model != null;
                    result.add(model);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        database.addListenerForSingleValueEvent(valueEventListener);
        return;
    }


    public List<PlaceModel> getFullList() {
        return result;
    }
}
