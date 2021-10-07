package com.example.cardgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardgameapp.gamesCategorys.DifferentGame;
import com.example.cardgameapp.gamesCategorys.WhatInThePicture;

public class Category extends AppCompatActivity {
    private Button backMainGame,whatIsInThePictureBtn,openDifferentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        whatIsInThePictureBtn = (Button) findViewById(R.id.what_is_in_the_picture_btn);
        whatIsInThePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category.this, WhatInThePicture.class));
            }
        });

        openDifferentButton = (Button) findViewById(R.id.different);
        openDifferentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDifferentGame();
            }
        });

        backMainGame = (Button) findViewById(R.id.back);
        backMainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainGame();
            }
        });
    }

    private void OpenDifferentGame() {
        Intent intent = new Intent(this, DifferentGame.class);
        startActivity(intent);
    }

    public void OpenMainGame() {
        Intent intent = new Intent(this,MainGame.class);
        startActivity(intent);
    }


}