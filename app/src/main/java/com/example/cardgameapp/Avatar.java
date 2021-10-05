package com.example.cardgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Avatar extends AppCompatActivity {
    public static final String INPUT_AVATAR="Avatar.INPUT_AVATAR";
    private Integer selectedAvatar;
    private Button female_6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }


    public void ChooseFemale_6(View view) {

    }
}