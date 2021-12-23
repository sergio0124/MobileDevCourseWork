package com.travelagencycoursework.database.fireboxlogic;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.travelagencycoursework.database.models.GuideModel;

import java.util.ArrayList;
import java.util.List;

public class GuideFireboxLogic {
    private final String TABLE = "guide";

        List<GuideModel> result = new ArrayList<GuideModel>();

        private DatabaseReference database;

        public GuideFireboxLogic() {
            database = FirebaseDatabase.getInstance().getReference(TABLE);
        }


        public void fillData(){
            result.clear();
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        GuideModel model = ds.getValue(GuideModel.class);
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


        public List<GuideModel> getFullList() {
            return result;
        }
}
