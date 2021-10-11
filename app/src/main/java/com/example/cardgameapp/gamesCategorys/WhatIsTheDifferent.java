package com.example.cardgameapp.gamesCategorys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cardgameapp.ProgressBarThread;
import com.example.cardgameapp.R;
import com.example.cardgameapp.WIDGames;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.HashMap;

public class WhatIsTheDifferent extends AppCompatActivity {

    private WIDGames games;
    private static int level=0;
    private HashMap<Integer, DifferentGameObj>  differentGames;
    private ImageView img1, img2, img3, img4;
    private DifferentGameObj game;
    private ProgressBar progressBar;
    private TextView gameCoinsText, hartCountText;
    private int scoreDifference,sourceAnswer;
    private int hartCount=3;
    private DatabaseReference DifferentGameReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_the_different);
        gameCoinsText=(TextView) findViewById(R.id.WITPcoins) ;
        hartCountText =(TextView) findViewById(R.id.WIThartCount);
        games= WIDGames.getInstance();
        progressBar=findViewById(R.id.progressBar);
        img1 =findViewById(R.id.image1);
        img2 =findViewById(R.id.image2);
        img3 =findViewById(R.id.image3);
        img4 =findViewById(R.id.image4);
        CreateDifferentGames();
        game=games.GetDifferentGames().get(level);
        DifferentGameReference= FirebaseDatabase.getInstance().getReference("Different");
        hartCountText.setText(String.valueOf(hartCount));
        Init(level);
        scoreDifference=0;
    }

    public void CreateDifferentGames()
    {
        games.CreateDifferentGame(new DifferentGameObj( R.drawable.tricycle, R.drawable.motorcycle, R.drawable.hoverboard, R.drawable.scooter, R.drawable.tricycle,0));
        games.CreateDifferentGame(new DifferentGameObj(R.drawable.stork, R.drawable.dolphin, R.drawable.fish, R.drawable.shark, R.drawable.stork,1));
        games.CreateDifferentGame(new DifferentGameObj( R.drawable.slmon, R.drawable.ice_cream, R.drawable.slmon, R.drawable.chocolate, R.drawable.cake,2));
        games.CreateDifferentGame(new DifferentGameObj( R.drawable.not_salad, R.drawable.not_salad, R.drawable.salad1, R.drawable.salad2, R.drawable.salad3,3));
        games.CreateDifferentGame(new DifferentGameObj(R.drawable.watch_green, R.drawable.red_ball, R.drawable.balon, R.drawable.red_roses, R.drawable.watch_green,4));
        games.CreateDifferentGame(new DifferentGameObj( R.drawable.butterfly, R.drawable.lionn, R.drawable.hamster, R.drawable.giraffe, R.drawable.butterfly,5));
        games.CreateDifferentGame(new DifferentGameObj( R.drawable.banana, R.drawable.gamba, R.drawable.cucumber, R.drawable.banana, R.drawable.tomato,6));
        games.WriteDifferentGames();

    }

    public void ChooseLevel(DifferentGameObj game)
    {
        differentGames.get(level);
        LayoutInflater inflater =(LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        img1.setImageResource(game.getPicturePath1());
        img2.setImageResource(game.getPicturePath2());
        img3.setImageResource(game.getPicturePath3());
        img4.setImageResource(game.getPicturePath4());

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.getDAnswer()== game.getPicturePath1()) {
                    levelHandler("win");
                } else {
                    levelHandler("loseLifes");

                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (game.getDAnswer() == game.getPicturePath2())
                {
                    levelHandler("win");
                }
                else {
                    levelHandler("loseLifes");

                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (game.getDAnswer() == game.getPicturePath3())
                {
                    levelHandler("win");
                }
                else {
                    levelHandler("loseLifes");

                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (game.getDAnswer() == game.getPicturePath4())
                {
                    levelHandler("win");
                }
                else {
                    levelHandler("loseLifes");

                }
            }
        });
        }

    private void levelHandler(String action){
        switch (action){
            case "win":
                scoreDifference= scoreDifference+ 10;
                gameCoinsText.setText(Integer.toString(scoreDifference));
                Init(game.getLevel() + 1);
                break;
            case "lose":
                break;
            case "endGame":
                break;
            case "addPoints":
                scoreDifference = scoreDifference+ 10;
                gameCoinsText.setText(String.valueOf(scoreDifference));
                break;
            case "loseLifes":
                hartCount --;
                if (hartCount == 0 ){
                    levelHandler("endGame");
                }
                else{
                    hartCountText.setText(String.valueOf(hartCount));
                }
                break;


        }

    }

    public void Init(int level)
    {
        ValueEventListener DifferentGameListener = new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                DifferentGameObj game = new DifferentGameObj();
                game.setDAnswer(snapshot.child("answer").getValue(Integer.class));
                game.setLevel(snapshot.child("level").getValue(Integer.class));
                game.setPicturePath1(snapshot.child("PicturePath1").getValue(Integer.class));
                game.setPicturePath2(snapshot.child("PicturePath2").getValue(Integer.class));
                game.setPicturePath3(snapshot.child("PicturePath3").getValue(Integer.class));
                game.setPicturePath4(snapshot.child("PicturePath4").getValue(Integer.class));
                ChooseLevel(game);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        switch (level)
        {
            case 0:
                DifferentGameReference.child("0").addValueEventListener(DifferentGameListener);
                break;
            case 1:
                DifferentGameReference.child("1").addValueEventListener(DifferentGameListener);
                break;
            case 2:
                DifferentGameReference.child("2").addValueEventListener(DifferentGameListener);
                break;
            case 3:
                DifferentGameReference.child("3").addValueEventListener(DifferentGameListener);
                break;
            case 4:
                DifferentGameReference.child("4").addValueEventListener(DifferentGameListener);
                break;
            case 5:
                DifferentGameReference.child("5").addValueEventListener(DifferentGameListener);
                break;
            case 6:
                DifferentGameReference.child("6").addValueEventListener(DifferentGameListener);
                break;
        }
    }


    public void StartTimerGame()
    {
        ProgressBarThread thread=new ProgressBarThread(progressBar,60);
        thread.start();

    }

}