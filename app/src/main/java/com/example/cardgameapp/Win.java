package com.example.cardgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cardgameapp.userLogIn.RegistrUserActivity;

public class Win extends AppCompatActivity {
    public static final String Next_Level="NEXT.LEVEL";
    private Intent intent;
    private int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        /*
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bundle extras=getIntent().getExtras();
        level=extras.getInt(Next_Level);
        intent=new Intent(this, extras.getClass());
        intent.putExtra(Next_Level,level);
        startActivity(intent);
        */

    }




}