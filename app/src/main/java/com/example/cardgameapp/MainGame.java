package com.example.cardgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainGame extends AppCompatActivity {

    private Button OpenCategoryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        OpenCategoryButton =findViewById(R.id.startGameBtn);
        OpenCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCategory();
            }
        });
    }

    public void OpenCategory() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }
}