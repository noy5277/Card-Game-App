package com.example.cardgameapp;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundSound  extends Service {
    private static final String TAG = null;
    private static boolean play_stop = true;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.backgroud_music);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
    }
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        play_stop = true;
        return 1;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

    }

    public void onPause() {
        play_stop = false;
        player.stop();
        player.release();
    }
    @Override
    public void onDestroy() {
        play_stop = false;
        player.stop();
        player.release();
    }
    public Boolean playStatus(){
        return play_stop;
    }
    @Override
    public void onLowMemory() {

    }
}
