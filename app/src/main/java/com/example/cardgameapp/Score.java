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
    private ArrayList<String> usernames;
    private ArrayList<String> scores;
    private ArrayList<Integer> images;
    private static IDao DB;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        DB= DaoFirebaseImpl.getInstance();
        mScoreTable=findViewById(R.id.listView);

        FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("score")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    usernames=new ArrayList<String>();
                    scores=new ArrayList<String>();
                    images=new ArrayList<Integer>();
                    for(DataSnapshot dss:snapshot.getChildren())
                    {
                        usernames.add(dss.child("userName").getValue(String.class));
                        scores.add("Score: "+Integer.toString(dss.child("score").getValue(Integer.class)));
                        images.add(dss.child("image").getValue(Integer.class));
                    }

                    ProgramAdapter programAdapter=new ProgramAdapter(Score.this,usernames,scores,images);
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