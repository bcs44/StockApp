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

    public void getValue() {
        database =FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Calcado");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Values> productsList = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Values productsTable = dataSnapshot1.getValue(Values.class);
                    value = productsTable.getValue();
                    productsList.add(productsTable);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Value", "Failed to read value.", error.toException());
            }
        });


    }
}
