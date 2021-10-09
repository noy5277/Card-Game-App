package com.example.cardgameapp.gamesCategorys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.example.cardgameapp.Category;
import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.MainActivity;
import com.example.cardgameapp.MainGame;
import com.example.cardgameapp.R;
import com.example.cardgameapp.Image;
import com.example.cardgameapp.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private TextView gameLevelText,gameCoinsText,hartCountText,exitLevelBtn;
    public TextView closeButton,nextLevelButton,winCoinPoints,LoseCoinPoints;
    public HashMap<TextView, TextView> answerTextView = new HashMap<TextView, TextView>();// Move up
    private String inputWord2="";
    private String[] inputWord;
    private int score;
    private int hartCount;
    private DaoFirebaseImpl daoFirebaseImpl;
    FirebaseDatabase fAuth;
    private User user =null;
    private Animation animShake;

    private PopupWindow mPopupWindow;
    private LinearLayout  mRelativeLayout;


    public int lastIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_in_the_picture);
        daoFirebaseImpl = new DaoFirebaseImpl();

        gameCoinsText =(TextView) findViewById(R.id.WITPcoins);
        hartCountText =(TextView) findViewById(R.id.WIThartCount);
        animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        levelImage = (ImageView) findViewById(R.id.WITPlevelImage);
        backBtn =  (ImageView) findViewById(R.id.WITPbackBtn);
        answerLettersLayout = (LinearLayout) findViewById(R.id.answerLayout);
        lettersLayoutl = (LinearLayout) findViewById(R.id.WITPletters);
        gameLevelText = (TextView) findViewById(R.id.WITPlevel);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getLeves();
        score = 0;
        hartCount = 3;
        hartCountText.setText(String.valueOf(hartCount));


    }


    private void buildGameLevel() {
        setImage();
        setAnswerletters();
        setletters();
        setLevelNumber(gameLevel.getLevel());

    }

    private void setImage() {
        int imageResource = getResources().getIdentifier(gameLevel.getImage().getName(), "drawable", getPackageName());
        levelImage.setImageResource(imageResource);

    }

    private void setAnswerletters() {
        LayoutInflater inflater = getLayoutInflater();
        int answerSize = gameLevel.getImage().getAnswer().length();
        inputWord = new String[answerSize];

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
                    if (removeLettter != null){
                        removeLettter.setVisibility(View.VISIBLE);
                        textView.setText("");
                        answerTextView.replace(textView, null);
                        int id =textView.getId();
                        if (lastIndex>id){
                            lastIndex =textView.getId();
                        }
                        inputWord[id]="";
                        inputWord2 = Arrays.toString(inputWord);
                        if (gameLevel.getImage().getAnswer().length() != inputWord2.length()) {

                            for (Map.Entry<TextView, TextView> entryWrong : answerTextView.entrySet()) {
                                TextView key = entryWrong.getKey();
                                key.setTextColor(Color.BLACK);


                            }
                            animShake.cancel();
                        }

                }
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
                        TextView answerText =null ;
                        int  index = answerTextView.size();
                        for (Map.Entry<TextView, TextView> entry : answerTextView.entrySet()) {
                            TextView key = entry.getKey();
                            TextView value = entry.getValue();
                            if ((value == null)) {
                                if (index > (int) key.getId()) {
                                    answerText = key;
                                    index = (int) key.getId();
                                }
                            }
                        }
                        if (answerText!=null) {
                            answerTextView.replace(answerText, textView);
                            answerText.setText(textView.getText());
                            textView.setVisibility(View.INVISIBLE);
                            inputWord[(int)answerText.getId()] = answerText.getText().toString();

                        }
                        inputWord2 =
                                Stream.of(inputWord)
                                        .filter(s -> s != null && !s.isEmpty())
                                        .collect(Collectors.joining(""));

                        if (gameLevel.getImage().getAnswer().length() == inputWord2.length())
                        {
                            if (gameLevel.getImage().getAnswer().equals(inputWord2.toString()) ) {
                                popUpHendlerWin();
                            }
                            else {
                                for (Map.Entry<TextView, TextView> entryWrong : answerTextView.entrySet()) {
                                    TextView key2 = entryWrong.getKey();
                                    key2.setTextColor(Color.RED);
                                    key2.startAnimation(animShake);

                                }
                                levelHandler("loseLifes");

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
        //gameLevelText.setText(String.valueOf(level));
    }
    private void backButton(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhatInThePicture.this, Category.class));

            }
        });
    }
    private void popUpHendlerWin(){

        mRelativeLayout = (LinearLayout ) findViewById(R.id.WITPMain);
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.activity_win,null,false);
        mPopupWindow = new PopupWindow(
                customView,
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        closeButton = (TextView) customView.findViewById(R.id.exitLevelBtn);
            closeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    levelHandler("endGame");
                }
            });
        nextLevelButton = (TextView) customView.findViewById(R.id.nextLevelBtn);
        nextLevelButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                levelHandler("win");
                mPopupWindow.dismiss();
            }
        });
        winCoinPoints = (TextView) customView.findViewById(R.id.WINcoinsAdd);
        winCoinPoints.setText("+10");

        exitLevelBtn =  (TextView) customView.findViewById(R.id.exitLevelBtn);
        exitLevelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhatInThePicture.this, MainGame.class));
            }
        });

        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);


    }
    private void popUpHendlerlose() {
        mRelativeLayout = (LinearLayout ) findViewById(R.id.WITPMain);
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.activity_lose,null,false);
        mPopupWindow = new PopupWindow(
                customView,
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        closeButton = (TextView) customView.findViewById(R.id.LOSEcoinsAdd);
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                levelHandler("endGame");
            }
        });

        LoseCoinPoints = (TextView) customView.findViewById(R.id.LOSEcoinsAdd);
        LoseCoinPoints.setText(String.valueOf(score));

        exitLevelBtn =  (TextView) customView.findViewById(R.id.exitLevelBtn);
        exitLevelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhatInThePicture.this, MainGame.class));
            }
        });

        String uid = ((DaoFirebaseImpl) this.getApplication()).getCurrentUserId();
        user = ((DaoFirebaseImpl) this.getApplication()).getUser(uid);
        user.setScore(user.getScore()+score);
        ((DaoFirebaseImpl) this.getApplication()).UpdateUser(user);

        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
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
    }
    private void levelHandler(String action){
        switch (action){
            case "win":
                cleanLevel();
                score = score+ 10;
                gameCoinsText.setText(String.valueOf(score));
                setGameLevelHendler(gameLevel.getLevel() + 1);
                break;
            case "lose":
                popUpHendlerlose();
                break;
            case "endGame":

                break;
            case "addPoints":
                score = score+ 10;
                gameCoinsText.setText(String.valueOf(score));
                break;
            case "loseLifes":
                hartCount --;
                if (hartCount == 0 ){
                    levelHandler("lose");
                }
                else{
                    hartCountText.setText(String.valueOf(hartCount));
                }
                break;


        }

    }
}