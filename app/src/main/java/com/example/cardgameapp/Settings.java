package com.example.cardgameapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cardgameapp.Database.DaoFirebaseImpl;


public class Settings extends AppCompatActivity {

    private ImageView SoundBtn,LogourBtn;
    private DaoFirebaseImpl FireDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FireDB = new DaoFirebaseImpl();
        SoundBtn = (ImageView) findViewById(R.id.SoundBtn);
        LogourBtn = (ImageView) findViewById(R.id.Logout);
        startService(new Intent(Settings.this, BackgroundSound.class));

        SoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMyServiceRunning(BackgroundSound.class)) {
                    stopService(new Intent(Settings.this, BackgroundSound.class));
                    SoundBtn.setBackgroundResource(R.drawable.sound_of);
                } else {
                    startService(new Intent(Settings.this, BackgroundSound.class));
                    SoundBtn.setBackgroundResource(R.drawable.sound_on);

                }
            }

        });
        LogourBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FireDB.SignOut();
                stopService(new Intent(Settings.this, BackgroundSound.class));
                startActivity(new Intent(Settings.this, MainActivity.class));
            }

        });

    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    }
