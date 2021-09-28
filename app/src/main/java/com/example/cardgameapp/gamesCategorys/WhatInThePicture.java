package com.example.cardgameapp.gamesCategorys;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.R;
import com.example.cardgameapp.userLogIn.RegistrUserActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WhatInThePicture extends AppCompatActivity {

    //-----------Test-------------

    private String picPath = "drawable/whatinthepicturetest.jpg";
    private String answer = "lionas";
    private int level = 1;
    private int hints = 3;
    //---------------------------
    private WhatInThePictureObj game = new WhatInThePictureObj(picPath, answer, level);
    private ImageView levelImage;
    private LinearLayout answerLettersLayout;
    private LinearLayout lettersLayoutl;
    private DisplayMetrics displayMetrics;
    public HashMap<Integer, TextView> answerTextView = new HashMap<Integer, TextView>();// Move up
    HashMap<String, TextView> lettersTextView = new HashMap<String, TextView>();// Move up

    public int lastIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_in_the_picture);

        //levelImage = (ImageView) findViewById(R.id.WITPlevelImage);
        answerLettersLayout = (LinearLayout) findViewById(R.id.WITPanswerLetters);
        lettersLayoutl = (LinearLayout) findViewById(R.id.WITPletters);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        buildGameLevel();
    }

    private void buildGameLevel() {
       // setImage();
        setAnswerletters();
        setletters();
    }

    private void setImage() {
        levelImage.setImageResource(R.drawable.whatinthepicturetest);
    }

    private void setAnswerletters() {
        LayoutInflater inflater = getLayoutInflater();
        int answerSize = game.getAnswer().length(); //optionjs

        for (int i = 0; i < answerSize; i++) {
            View to_add = inflater.inflate(R.layout.text_answer_layout,
                    answerLettersLayout, false);

            TextView textView = (TextView) to_add.findViewById(R.id.answerLetter);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setId(i);
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!textView.getText().toString().isEmpty()) {
                        String oldLetter = textView.getText().toString();
                        textView.setText("");
                        lettersTextView.get(oldLetter).setVisibility(View.VISIBLE);
                        for (int i = 0; i < lastIndex; i++) {
                            if (answerTextView.get(i).getText().toString().isEmpty())
                                lastIndex = answerTextView.get(i).getId();
                        }
                    }

                }

            });
            answerLettersLayout.addView(to_add);
            answerTextView.put(i, textView);
        }
    }

    private void setletters() {
        int answerSize = game.getAnswer().length();
        Random r = new Random();
        int amountOfLetters = 14;
        List lettersList = randomListOfWords( game.getAnswer(),amountOfLetters);

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

            for (int i = 0; i < amountOfLetters/2; i++) {

                View to_add = inflater.inflate(R.layout.letter,
                        lettersLayoutl, false);
                TextView textView = (TextView) to_add.findViewById(R.id.letter);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setText(String.valueOf(lettersList.get(lettersList.size() - 1)));

                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (lastIndex < game.getAnswer().length()) {
                            while (!answerTextView.get(lastIndex).getText().toString().isEmpty())
                                lastIndex++;

                            answerTextView.get(lastIndex).setText(textView.getText());
                            textView.setVisibility(View.INVISIBLE);
                            lastIndex++;
                        }
                        if (lastIndex == game.getAnswer().length()) {
                            for (int i = 0; i < game.getAnswer().length(); i++) {

                                if (!answerTextView.get(i).getText().toString().equals(String.valueOf(game.getAnswer().charAt(i)).toString())) {
                                    Toast.makeText(WhatInThePicture.this, "Faild", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(WhatInThePicture.this, "succesd", Toast.LENGTH_LONG).show();

                                }
                            }

                        }

                    }

                });
                newRow.addView(to_add);
                lettersTextView.put(String.valueOf(lettersList.get(lettersList.size() - 1)), textView);
                lettersList.remove(lettersList.size() - 1);

            }
            lettersLayoutl.addView(newRow);

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
}