package com.example.cardgameapp.gamesCategorys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cardgameapp.Category;
import com.example.cardgameapp.R;
import com.example.cardgameapp.Image;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WhatInThePicture extends AppCompatActivity {

    //-----------Test-------------
    private int hints = 3;
    //---------------------------
    private WhatInThePictureObj gameLevel;
    private List<WhatInThePictureObj> gameLevels = new ArrayList<WhatInThePictureObj>();
    private ImageView levelImage,backBtn;
    private LinearLayout answerLettersLayout;
    private LinearLayout lettersLayoutl;
    private DisplayMetrics displayMetrics;
    private Button hintBtn;
    private TextView gameLevelText;
    public HashMap<TextView, TextView> answerTextView = new HashMap<TextView, TextView>();// Move up
    private String inputWord="";
    FirebaseDatabase fAuth;


    public int lastIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_in_the_picture);

        levelImage = (ImageView) findViewById(R.id.WITPlevelImage);
        backBtn =  (ImageView) findViewById(R.id.WITPbackBtn);
        answerLettersLayout = (LinearLayout) findViewById(R.id.WITPanswerLetters);
        lettersLayoutl = (LinearLayout) findViewById(R.id.WITPletters);
        gameLevelText = (TextView) findViewById(R.id.level_txt);
        displayMetrics = new DisplayMetrics();
        hintBtn = (Button) findViewById(R.id.WITPhint);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getLeves();
    }


    private void buildGameLevel() {
        setImage();
        setAnswerletters();
        setletters();
        backButton();
        setLevelNumber(gameLevel.getLevel());
    }

    private void setImage() {
        int imageResource = getResources().getIdentifier(gameLevel.getImage().getName(), "drawable", getPackageName());
        levelImage.setImageResource(imageResource);
    }

    private void setAnswerletters() {
        LayoutInflater inflater = getLayoutInflater();
        int answerSize = gameLevel.getImage().getAnswer().length(); //optionjs

        for (int i = 0; i < answerSize; i++) {
            View to_add = inflater.inflate(R.layout.text_answer_layout,
                    answerLettersLayout, false);

            TextView textView = (TextView) to_add.findViewById(R.id.answerLetter);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setId(i);
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TextView removeLettter = (TextView) answerTextView.get(textView);
                    removeLettter.setVisibility(View.VISIBLE);
                    textView.setText("");
                    answerTextView.replace(textView,null);
                    lastIndex--;
                    inputWord.substring(0, inputWord.length() - 1);
                }

            });
            answerLettersLayout.addView(to_add);
            answerTextView.put(textView,null);
        }
    }

    private void setletters() {
        Random r = new Random();
        int amountOfLetters = 14;
        List lettersList = randomListOfWords( gameLevel.getImage().getAnswer(),amountOfLetters);

        //----style ----
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;
        LinearLayout newRow;

        LayoutInflater inflater = getLayoutInflater();
        //-----

        for (int j = 0; j < 2; j++) {
            newRow = new LinearLayout(this);
            newRow.setOrientation(LinearLayout.HORIZONTAL);
            newRow.setLayoutParams(params);

            for (int i = 0, index = 0; i < amountOfLetters/2; i++,index++) {

                View to_add = inflater.inflate(R.layout.letter,
                        lettersLayoutl, false);

                TextView textView = (TextView) to_add.findViewById(R.id.letter);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setText(String.valueOf(lettersList.get(lettersList.size() - 1)));
                textView.setId(index+lettersList.get(lettersList.size() - 1).hashCode());
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        for (Map.Entry<TextView, TextView> entry : answerTextView.entrySet()) {
                            TextView key = entry.getKey();
                            TextView value = entry.getValue();
                            if (value==null && key.getId() == lastIndex) {
                                answerTextView.replace(key, textView);
                                key.setText(textView.getText());
                                textView.setVisibility(View.INVISIBLE);
                                lastIndex++;

                                inputWord = inputWord + key.getText();
                                if (gameLevel.getImage().getAnswer().equals(inputWord) && gameLevel.getImage().getAnswer().length() == inputWord.length()) {
                                    cleanLevel();
                                    setGameLevelHendler(gameLevel.getLevel() + 1);

                                }
                                break;

                            }
                        }
                    }
                });
                newRow.addView(to_add);
                lettersList.remove(lettersList.size() - 1);

            }
            lettersLayoutl.addView(newRow);

        }
    }

    private void setLevelNumber(int level){
        //gameLevel.gameLevelText(String.valueOf(level));
    }
    private void backButton(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhatInThePicture.this, Category.class));

            }
        });
    }
    private void hintBtn(View  v){

    }
    private void getLeves(){
        fAuth = FirebaseDatabase.getInstance();
        DatabaseReference reference = fAuth.getReference("WhatsInThePicture");
        reference.addValueEventListener(new ValueEventListener(){
            int levelCounter = 1;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot: snapshot.getChildren()){
                    Image newImage = datasnapshot.getValue(Image.class);
                    gameLevels.add(new WhatInThePictureObj(levelCounter++,newImage));
                }
                setGameLevelHendler(1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void  setGameLevelHendler(int level){
        if (gameLevels.size()> level) {
            gameLevel = gameLevels.get(level-1);
            buildGameLevel();
        }

    }
    private List randomListOfWords(String WordToinsert, int size) {
        Random r = new Random();
        List list = new ArrayList();
        while(size>list.size()){
            if (WordToinsert.length() > list.size())
                list.add(WordToinsert.charAt(list.size()));
            else {
                char newLetter = (char) (r.nextInt(26) + 'a');
                if (!list.contains(newLetter))
                    list.add(newLetter);
            }
        }
        Collections.shuffle(list);
        return list;
    }

    private void cleanLevel(){
        answerLettersLayout.removeAllViews();
        lettersLayoutl.removeAllViews();
        answerTextView.clear();
        lastIndex = 0;
        inputWord = "";
    }
}