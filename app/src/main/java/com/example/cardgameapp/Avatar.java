package com.example.cardgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Avatar extends AppCompatActivity {
    public static final String INPUT_AVATAR="Avatar.INPUT_AVATAR";
    private Integer selectedAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }
}