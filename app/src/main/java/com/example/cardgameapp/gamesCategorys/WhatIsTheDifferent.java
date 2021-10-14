package com.example.cardgameapp.gamesCategorys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cardgameapp.Category;
import com.example.cardgameapp.IObserver;
import com.example.cardgameapp.ProgressBarThread;
import com.example.cardgameapp.R;
import com.example.cardgameapp.WIDGames;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WhatIsTheDifferent extends AppCompatActivity implements IObserver {

    private WIDGames games;
    private static int level=0;
    private ImageView img1, img2, img3, img4;
    private DifferentGameObj game;
    private ProgressBar progressBar;
    private TextView gameCoinsText, hartCountText, gameLevelText;
    private int scoreDifference;
    private int hartCount=3;
    private DatabaseReference DifferentGameReference;
    private ProgressBarThread thread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_the_different);
        gameCoinsText=(TextView) findViewById(R.id.WITPcoins) ;
        hartCountText =(TextView) findViewById(R.id.WIThartCount);
        progressBar =findViewById(R.id.progressBar);
        gameLevelText = (TextView) findViewById(R.id.WITPlevel);
        games= WIDGames.getInstance();
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
    public void StartTimerGame()
    {
        thread=new ProgressBarThread(progressBar,60);
        thread.Add(this::Update);
        thread.start();
    }

    @Override
    public void Update() {
        //time is up intent
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

    public void ChooseLevel(DifferentGameObj game)
    {
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
                setLevelNumber(game.getLevel()+1);
                Init(game.getLevel() + 1);
                thread.Exit();
                break;
            case "lose":
                break;
            case "endGame":
                OpenCategory();
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
                    Init(game.getLevel() + 1);
                    thread.Exit();
                }
                break;
        }

    }

    public void Init(int level)
    {
        switch (level)
        {
            case 0:
                game=games.GetDifferentGames().get(0);
                ChooseLevel(game);
                break;
            case 1:
                game=games.GetDifferentGames().get(1);
                ChooseLevel(game);
                break;
            case 2:
                game=games.GetDifferentGames().get(2);
                ChooseLevel(game);
                break;
            case 3:
                game=games.GetDifferentGames().get(3);
                ChooseLevel(game);
                break;
            case 4:
                game=games.GetDifferentGames().get(4);
                ChooseLevel(game);
                break;
            case 5:
                game=games.GetDifferentGames().get(5);
                ChooseLevel(game);
                break;
            case 6:
                game=games.GetDifferentGames().get(6);
                ChooseLevel(game);
                break;
        }
        StartTimerGame();

    }

    private void OpenCategory() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }
    private void setLevelNumber(int level){
        gameLevelText.setText(String.valueOf(level));
    }
}