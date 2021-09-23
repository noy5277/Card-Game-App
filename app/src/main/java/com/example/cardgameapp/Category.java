package com.example.cardgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Category extends AppCompatActivity {
    private Button backMainGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        backMainGame = (Button) findViewById(R.id.back);
        backMainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainGame();
            }
        });
    }

    public void OpenMainGame() {
        Intent intent = new Intent(this,MainGame.class);
        startActivity(intent);
    }
}