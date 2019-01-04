package com.example.bsilva.stockapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataFromFirebase {
    String nome;
    String value;
    private FirebaseDatabase database;


    DataFromFirebase(){

    }

    public String getNome() {
        return nome;

    }

    public List<Values> getValue() {

        database =FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Calcado").child("Profissional");
        final List<Values> valuesList = new ArrayList<>();
// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    String name = dataSnapshot1.getKey();
                    String quantity =  dataSnapshot1.getValue(String.class);

                    Values tablerow = new Values();
                    tablerow.setName(name);
                    tablerow.setQuantity(quantity);

                    valuesList.add(tablerow);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

            return valuesList;

    }
}
