package com.example.voiceless;

import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class Compute {

    public void detectSign(String str, ImageView signDisplay){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://voiceless-ccca3-default-rtdb.firebaseio.com/");
        DatabaseReference databaseReference = firebaseDatabase.getReference("alphabets");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            //DataSnapshot :  efficiently-generated immutable copy of the data at a Firebase Location
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                Log.i("TAG",dataSnapshot.getValue(alphabets.class).getSign().getClass().getName());;
//                Log.i("TAG",str.getClass().getName());
                //getValue : used to get values from alphabets class
                if(str.equalsIgnoreCase(dataSnapshot.getValue(alphabets.class).getSign())) {
                    String url = dataSnapshot.getValue(alphabets.class).getURL();
                    Picasso.get()
                            .load(url)
                            .into(signDisplay);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

    }
}
