package com.example.voiceless;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    @Override
    //to do initial creation of the fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
//    called by Android once the Fragment should inflate a view
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
//    called after onCreateView() and ensures that the fragment's root view is non-null .
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button textToSign = view.findViewById(R.id.text_to_sign_button);
        Button imageToSign = view.findViewById(R.id.image_to_sign_button);
        Button voiceToSign = view.findViewById(R.id.voice_to_sign_button);

        textToSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TextToSign.class);
                startActivity(intent);
            }
        });

        imageToSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageToSign.class);
                startActivity(intent);
            }
        });

        voiceToSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VoiceToSign.class);
                startActivity(intent);
            }
        });
    }

}

