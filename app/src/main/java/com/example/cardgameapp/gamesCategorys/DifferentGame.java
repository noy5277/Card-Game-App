package com.example.cardgameapp.gamesCategorys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.Category;
import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.Image;
import com.example.cardgameapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class DifferentGame extends AppCompatActivity {
    private ImageView backCategory;
    private TextView textTimer;
    private ImageButton levelImage1,levelImage2,levelImage3,levelImage4;
    private TextView gameLevelText;
    private DifferentGameObj gameLevel;
    private IDao db;
    private DifferentGameObj gameImage ;
    private List<DifferentGameObj> gameLevels = new ArrayList<DifferentGameObj>();
    private List<DifferentGameObj> gameImages = new ArrayList<DifferentGameObj>();
    FirebaseDatabase fDB;
    String answer;

    


    private void getLeves() {
        fDB = FirebaseDatabase.getInstance();
        DatabaseReference reference = fDB.getReference("Different");
        reference.addValueEventListener(new ValueEventListener() {
            int levelCounter = 1;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    DifferentGameObj newImage = datasnapshot.getValue(DifferentGameObj.class);
                    gameLevels.add(new DifferentGameObj(0,newImage.getImage1(),newImage.getImage2(),newImage.getImage3(),newImage.getImage4(),
                            newImage.getDAnswer(),newImage.getCategory(),newImage.getPicturePath1(),newImage.getPicturePath2(),
                            newImage.getPicturePath3(),newImage.getPicturePath4()));
                }
                setGameLevelHendler(1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_different_game);
            getLeves();
            this.db = DaoFirebaseImpl.getInstance();

            gameImage = new DifferentGameObj(0, "motorcycle", "hoverboard", "scooter", "tricycle", "tricycle", "vehicle"
                    , "R.drawablt.motorcycle", "R.drawable.hoverboard", "R.drawablt.scooter", "R.drawablt.tricycle");
            db.writeNewDifferentGame(gameImage);
            levelImage1 = (ImageButton) findViewById(R.id.WDLevelImage1);
            levelImage2 = (ImageButton) findViewById(R.id.WDLevelImage2);
            levelImage3 = (ImageButton) findViewById(R.id.WDLevelImage3);
            levelImage4 = (ImageButton) findViewById(R.id.WDLevelImage4);
            gameLevelText = (TextView) findViewById(R.id.WDlevel);

            backCategory = (ImageView) findViewById(R.id.WITPbackBtn);
            backCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenCategory();
                }
            });

            textTimer = findViewById(R.id.Timer);
            long duration = TimeUnit.MINUTES.toMillis(1);
            new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long l) {
                    String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                            , TimeUnit.NANOSECONDS.toMillis(l)
                            , TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                    textTimer.setText(sDuration);
                }

                @Override
                public void onFinish() {
                    textTimer.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(),
                            "time is up", Toast.LENGTH_LONG).show();
                    OpenCategory();
                }
            }.start();

            for(int i=0;i<5;i++) {
                levelImage1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (gameImage.getImage1() != gameLevel.getDAnswer())
                            OpenCategory();
                        else
                            setGameLevelHendler(gameImage.getLevel());
                    }
                });

                levelImage2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (gameLevel.getImage2() != gameLevel.getDAnswer())
                            OpenCategory();
                        else
                            setGameLevelHendler(gameImage.getLevel());

                    }
                });

                levelImage3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (gameImage.getImage3() != gameLevel.getDAnswer())
                            OpenCategory();
                        else
                            setGameLevelHendler(gameImage.getLevel());
                    }
                });

                levelImage4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (gameImage.getImage4() != gameLevel.getDAnswer())
                            OpenCategory();
                        else {
                            setGameImageHandler();
                        }

                    }
                });
            }
    }


    private void buildGameLevel() {
       // setImage();
        setGameImageHandler();
    }

    /**private void setImage() {
        int imageResource1 = getResources().getIdentifier(gameLevel.getImage1(), "drawable", getPackageName());
        int imageResource2 = getResources().getIdentifier(gameLevel.getImage2(), "drawable", getPackageName());
        int imageResource3 = getResources().getIdentifier(gameLevel.getImage3(), "drawable", getPackageName());
        int imageResource4 = getResources().getIdentifier(gameLevel.getImage4(), "drawable", getPackageName());
        levelImage1.setImageResource(imageResource1);
        levelImage2.setImageResource(imageResource2);
        levelImage3.setImageResource(imageResource3);
        levelImage4.setImageResource(imageResource4);

    }**/


    public void OpenCategory() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

    private void setLevelNumber(int level){
        //gameLevel.gameLevelText(String.valueOf(level));
    }

    private void  setGameLevelHendler(int level){
        if (gameLevels.size()> level) {
            gameLevel = gameLevels.get(level-1);
            setGameImageHandler();
        }

    }

    private void setGameImageHandler() {
        switch (gameLevel.getLevel() + 2) {
            case 0:
                /**gameLevel = new DifferentGameObj(0, "motorcycle", "hoverboard", "scooter", "tricycle", "tricycle", "vehicle"
                 , "R.drawablt.motorcycle", "R.drawable.hoverboard", "R.drawablt.scooter", "R.drawablt.tricycle");
                 db.writeNewDifferentGame(gameImage);**/
                levelImage1.setImageResource(R.drawable.motorcycle);
                levelImage2.setImageResource(R.drawable.hoverboard);
                levelImage3.setImageResource(R.drawable.scooter);
                levelImage4.setImageResource(R.drawable.tricycle);
                gameLevel.setDAnswer("tricycle");
                gameLevel.setCategory("vehicle");
                gameLevel.setImage1("motorcycle");
                gameLevel.setImage2("hoverboard");
                gameLevel.setImage3("scooter");
                gameLevel.setImage4("tricycle");
                gameLevel.setPicturePath1("R.drawable.motorcycle");
                gameLevel.setPicturePath2("R.drawable.hoverboard");
                gameLevel.setPicturePath3("R.drawable.scooter");
                gameLevel.setPicturePath4("R.drawable.tricycle");

                break;

            case 1:
                levelImage1.setImageResource(R.drawable.dolphin);
                levelImage2.setImageResource(R.drawable.fish);
                levelImage3.setImageResource(R.drawable.shark);
                levelImage4.setImageResource(R.drawable.stork);
                gameLevel.setDAnswer("stork");
                gameLevel.setCategory("Lives in water");
                gameLevel.setImage1("dolphin");
                gameLevel.setImage2("fish");
                gameLevel.setImage3("shark");
                gameLevel.setImage4("stork");
                gameLevel.setPicturePath1("R.drawable.motorcycle");
                gameLevel.setPicturePath2("R.drawable.hoverboard");
                gameLevel.setPicturePath3("R.drawable.scooter");
                gameLevel.setPicturePath4("R.drawable.tricycle");
                db.writeNewDifferentGame(gameImage);
                break;

            case 2:
                levelImage1.setImageResource(R.drawable.ice_cream);
                levelImage2.setImageResource(R.drawable.salmon);
                levelImage3.setImageResource(R.drawable.chocolate);
                levelImage4.setImageResource(R.drawable.cake);
                gameLevel.setDAnswer("salmon");
                gameLevel.setCategory("desserts");
                gameLevel.setImage1("iceCream");
                gameLevel.setImage2("salmon");
                gameLevel.setImage3("chocolate");
                gameLevel.setImage4("cake");
                gameLevel.setPicturePath1("R.drawable.iceCream");
                gameLevel.setPicturePath2("R.drawable.salmon");
                gameLevel.setPicturePath3("R.drawable.chocolate");
                gameLevel.setPicturePath4("R.drawable.cake");
                db.writeNewDifferentGame(gameImage);
                break;

            case 3:
                levelImage1.setImageResource(R.drawable.not_a_salad);
                levelImage2.setImageResource(R.drawable.salad1);
                levelImage3.setImageResource(R.drawable.salad2);
                levelImage4.setImageResource(R.drawable.salad3);
                gameLevel.setDAnswer("not_a_salad");
                gameLevel.setCategory("salads");
                gameLevel.setImage1("not_a_salad");
                gameLevel.setImage2("salad1");
                gameLevel.setImage3("salad2");
                gameLevel.setImage4("salad3");
                gameLevel.setPicturePath1("R.drawable.not_a_salad");
                gameLevel.setPicturePath2("R.drawable.salad1");
                gameLevel.setPicturePath3("R.drawable.salad2");
                gameLevel.setPicturePath4("R.drawable.salad3");
                db.writeNewDifferentGame(gameImage);
                break;

            case 4:
                levelImage1.setImageResource(R.drawable.red_ball);
                levelImage2.setImageResource(R.drawable.red_roses);
                levelImage3.setImageResource(R.drawable.green_heart);
                levelImage4.setImageResource(R.drawable.a_red_apple);
                gameLevel.setDAnswer("green_heart");
                gameLevel.setCategory("redColor");
                gameLevel.setImage1("red_ball");
                gameLevel.setImage2("red_roses");
                gameLevel.setImage3("green_heart");
                gameLevel.setImage4("a_red_apple");
                gameLevel.setPicturePath1("R.drawable.red_ball");
                gameLevel.setPicturePath2("R.drawable.red_roses");
                gameLevel.setPicturePath3("R.drawable.green_heart");
                gameLevel.setPicturePath4("R.drawable.a_red_apple");
                db.writeNewDifferentGame(gameImage);
                break;

            case 5:
                levelImage1.setImageResource(R.drawable.lion);
                levelImage2.setImageResource(R.drawable.hamster);
                levelImage3.setImageResource(R.drawable.giraffe);
                levelImage4.setImageResource(R.drawable.butterfly);
                gameLevel.setDAnswer("butterfly");
                gameLevel.setCategory("mammals");
                gameLevel.setImage1("lion");
                gameLevel.setImage2("hamster");
                gameLevel.setImage3("giraffe");
                gameLevel.setImage4("butterfly");
                gameLevel.setPicturePath1("R.drawable.lion");
                gameLevel.setPicturePath2("R.drawable.hamster");
                gameLevel.setPicturePath3("R.drawable.giraffe");
                gameLevel.setPicturePath4("R.drawable.butterfly");
                db.writeNewDifferentGame(gameImage);
                break;

            case 6:
                levelImage1.setImageResource(R.drawable.gamba);
                levelImage2.setImageResource(R.drawable.cucumber);
                levelImage3.setImageResource(R.drawable.banana);
                levelImage4.setImageResource(R.drawable.tomato);
                gameLevel.setDAnswer("banana");
                gameLevel.setCategory("vegetables");
                gameLevel.setImage1("gamba");
                gameLevel.setImage2("cucumber");
                gameLevel.setImage3("banana");
                gameLevel.setImage4("tomato");
                gameLevel.setPicturePath1("R.drawable.gamba");
                gameLevel.setPicturePath2("R.drawable.cucumber");
                gameLevel.setPicturePath3("R.drawable.banana");
                gameLevel.setPicturePath4("R.drawable.tomato");
                db.writeNewDifferentGame(gameImage);
                break;
        }
    }
    private void cleanLevel(){
      //  answerLettersLayout.removeAllViews();
      //  lettersLayoutl.removeAllViews();
    }

}