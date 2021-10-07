package com.example.cardgameapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Score extends AppCompatActivity {


    private ListView mScoreTable;
    private ArrayList<ScoreItem> scores;
    private  ArrayList<String> UserNames;
    private static IDao DB;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        DB= DaoFirebaseImpl.getInstance();

        mScoreTable=findViewById(R.id.listView);
        scores = new ArrayList<>();
        UserNames = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {

                    for(DataSnapshot dss:snapshot.getChildren())
                    {
                        scores.add(new ScoreItem(dss.child("userName").getValue(String.class),String.valueOf(dss.child("score").getValue(Integer.class)),dss.child("image").getValue(Integer.class)));
                        UserNames.add(dss.child("userName").getValue(String.class));
                    }

                    ProgramAdapter programAdapter=new ProgramAdapter(Score.this,scores,UserNames);
                    mScoreTable.setAdapter(programAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void ReturnHome(View view) {
        Intent home=new Intent(this,MainGame.class);
        startActivity(home);
    }
}