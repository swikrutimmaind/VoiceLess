package com.example.voiceless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceToSign extends AppCompatActivity {
    ImageButton imageButton;
    EditText editText;
    SpeechRecognizer speechRecognizer;
    Button displaySign;
    int count = 0;
    ImageView signDisplay;
    Compute objectVoiceToSign = new Compute();
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_to_sign);

//        Intent intent = getIntent();
        imageButton =  findViewById(R.id.micoffId);
        editText =  findViewById(R.id.voicetotextId);
        displaySign =  findViewById(R.id.displaySignId);
        signDisplay =  findViewById(R.id.signFromImageId);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0){
                    imageButton.setImageDrawable(getDrawable(R.drawable.ic_mic));
                    //start listening
                    speechRecognizer.startListening(speechRecognizerIntent);
                    count = 1;
                }
                else {
                    imageButton.setImageDrawable(getDrawable(R.drawable.ic_mic_off));
                    //stop listening
                    speechRecognizer.stopListening();
                    count = 0;
                }
            }
        });

//        Sets the listener that will receive all the callbacks.
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) { }

            @Override
            public void onBeginningOfSpeech() { }

            @Override
            public void onRmsChanged(float rmsdB) { }

            @Override
            public void onBufferReceived(byte[] buffer) { }

            @Override
            public void onEndOfSpeech() { }

            @Override
            public void onError(int error) { }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                editText.setText(data.get(0));
                str = data.get(0);
            }

            @Override
            public void onPartialResults(Bundle partialResults) { }

            @Override
            public void onEvent(int eventType, Bundle params) { }
        });

        displaySign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objectVoiceToSign.detectSign(str, signDisplay);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT);
            }
            else{
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT);
            }
        }
    }


}