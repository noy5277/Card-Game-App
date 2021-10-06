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
    private List<DifferentGameObj> gameLevels = new ArrayList<DifferentGameObj>();
    FirebaseDatabase fDB;


    private void getLeves() {
        fDB = FirebaseDatabase.getInstance();
        DatabaseReference reference = fDB.getReference("Different");
        reference.addValueEventListener(new ValueEventListener() {
            int levelCounter = 1;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Image newImage = datasnapshot.getValue(Image.class);
                    gameLevels.add(new DifferentGameObj(levelCounter++, newImage));
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


        levelImage1 = (ImageButton) findViewById(R.id.WDLevelImage1);
        levelImage2 = (ImageButton) findViewById(R.id.WDLevelImage2);
        levelImage3 = (ImageButton) findViewById(R.id.WDLevelImage3);
        levelImage4 = (ImageButton) findViewById(R.id.WDLevelImage4);
        gameLevelText = (TextView) findViewById(R.id.WDlevel);


        textTimer=findViewById(R.id.Timer);
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH,"%02d : %02d"
                ,TimeUnit.NANOSECONDS.toMillis(l)
                ,TimeUnit.MILLISECONDS.toSeconds(l) -
                 TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                textTimer.setText(sDuration);
            }

            @Override
            public void onFinish() {
                textTimer.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(),
                        "time is up", Toast.LENGTH_LONG).show();

            }
        }.start();

        backCategory = (ImageView) findViewById(R.id.WITPbackBtn);
        backCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCategory();
            }
        });
    }

    private void buildGameLevel() {
        setImage();
        OpenCategory();
        setLevelNumber(gameLevel.getLevel());
    }

    private void setImage() {
        int imageResource1 = getResources().getIdentifier(gameLevel.getImage().getName(), "drawable", getPackageName());
        int imageResource2 = getResources().getIdentifier(gameLevel.getImage().getName(), "drawable", getPackageName());
        int imageResource3 = getResources().getIdentifier(gameLevel.getImage().getName(), "drawable", getPackageName());
        int imageResource4 = getResources().getIdentifier(gameLevel.getImage().getName(), "drawable", getPackageName());
        levelImage1.setImageResource(imageResource1);
        levelImage2.setImageResource(imageResource2);
        levelImage3.setImageResource(imageResource3);
        levelImage4.setImageResource(imageResource4);


    }

    public void OpenCategory() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

    private void setLevelNumber(int level){
        //gameLevel.gameLevelText(String.valueOf(level));
    }

    private void  setDifferentGameLevelHendler(int level){
        if (gameLevels.size()> level) {
            gameLevel = gameLevels.get(level-1);
            buildGameLevel();
        }

    }


    private void  setGameLevelHendler(int level){
        if (gameLevels.size()> level) {
            gameLevel = gameLevels.get(level-1);
            buildGameLevel();
        }

    }



}