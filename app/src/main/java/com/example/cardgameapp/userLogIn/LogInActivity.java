package com.example.cardgameapp.userLogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.R;
import com.example.cardgameapp.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
/*
###################################
NOT IN USE
#####################################
 */


public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

}