package com.example.voiceless;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TextToSign extends AppCompatActivity {

    EditText userStr;
    String str;
    Button detectSignfromText;
    ImageView signDisplay;

    Compute computeTextToSign = new Compute();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_sign);

//        Intent intent = getIntent();
        userStr =  findViewById(R.id.ip);

        detectSignfromText =  findViewById(R.id.toDetectSign);
        signDisplay = findViewById(R.id.signId);

        detectSignfromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = userStr.getText().toString();
                computeTextToSign.detectSign(str, signDisplay);
            }
        });

    }

}