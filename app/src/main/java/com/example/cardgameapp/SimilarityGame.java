package com.example.cardgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.gamesCategorys.Games;
import com.example.cardgameapp.gamesCategorys.SameGameObj;

import java.util.HashMap;

public class SimilarityGame extends AppCompatActivity {

    private Games games;
    private static int level=0;
    private HashMap<Integer, TextView> letters;
    private StringBuilder answer;
    private LinearLayout layoutAnswer;
    private HashMap<Integer, SameGameObj>  sameGames;
    private ImageView imageView1,imageView2,imageView3,imageView4;
    private static int indexAnswer;
    private static int countAnswer;
    private SameGameObj game;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarity_game);
        games= Games.getInstance();
        letters=new HashMap<Integer, TextView>();
        answer=new StringBuilder();
        progressBar=findViewById(R.id.progressBar);
        indexAnswer=0;
        countAnswer=0;
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        layoutAnswer=findViewById(R.id.answerLayout);
        games.GetSameGameFromDb();
        game=games.GetSameGames().get(level);
        TaggingLetters();
        Init();
        StartGame();
    }

    public void CreateGames()
    {
        games.CreateSameGame(new SameGameObj(R.drawable.bucket,R.drawable.sandcastle,R.drawable.shell,R.drawable.sunglasses,"Beach",0));
        games.CreateSameGame(new SameGameObj(R.drawable.fins,R.drawable.mask,R.drawable.seahorse,R.drawable.turtle,"Diving",1));
        games.CreateSameGame(new SameGameObj(R.drawable.grapefruit,R.drawable.kiwi,R.drawable.strawberry,R.drawable.watermelon,"fruits",2));
        games.CreateSameGame(new SameGameObj(R.drawable.aeroplane,R.drawable.camera,R.drawable.palm,R.drawable.passport,"Holiday",3));
        games.CreateSameGame(new SameGameObj(R.drawable.alien,R.drawable.astronaut,R.drawable.galaxy,R.drawable.ship,"space",4));
        games.CreateSameGame(new SameGameObj(R.drawable.bigben,R.drawable.egypt,R.drawable.eiffel,R.drawable.liberty,"Tourism",5));
        games.WriteSameGames();
    }

    public void TaggingLetters()
    {
        letters.put(11,findViewById(R.id.letter11));
        letters.put(12,findViewById(R.id.letter12));
        letters.put(13,findViewById(R.id.letter13));
        letters.put(14,findViewById(R.id.letter14));
        letters.put(15,findViewById(R.id.letter15));
        letters.put(16,findViewById(R.id.letter16));
        letters.put(21,findViewById(R.id.letter21));
        letters.put(22,findViewById(R.id.letter22));
        letters.put(23,findViewById(R.id.letter23));
        letters.put(24,findViewById(R.id.letter24));
        letters.put(25,findViewById(R.id.letter25));
        letters.put(26,findViewById(R.id.letter26));
    }


    public void ChooseLevel(int level)
    {
        LayoutInflater inflater =(LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        imageView1.setImageResource(game.getPiq1());
        imageView2.setImageResource(game.getPiq2());
        imageView3.setImageResource(game.getPiq3());
        imageView4.setImageResource(game.getPiq4());
        int answerSize=game.getAnswer().length();
        for(int i = 0; i < answerSize; i++)
        {
            View to_add = inflater.inflate(R.layout.text_answer_layout,layoutAnswer,false);
            TextView textView = (TextView) to_add.findViewById(R.id.answerLetter);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setId(i);
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(textView.getText()!=null)
                    {
                        textView.setText(null);
                        countAnswer--;
                        for(int i=0 ; i<answerSize;i++)
                        {
                            if(textView.findViewById(i)==null)
                            {
                                indexAnswer=textView.getId();
                            }
                        }
                    }
                }
            });
            layoutAnswer.addView(to_add);
        }
    }


    public void Init()
    {
        switch (level)
        {
            case 0:
                ChooseLevel(0);
                break;
            case 1:
                ChooseLevel(1);
                break;
            case 2:
                ChooseLevel(2);
                break;
            case 3:
                ChooseLevel(3);
                break;
            case 4:
                ChooseLevel(4);
                break;
            case 5:
                ChooseLevel(5);
                break;
            case 6:
                ChooseLevel(6);
                break;
        }
    }

    public void StartGame()
    {
        ProgressBarThread thread=new ProgressBarThread(progressBar,60);
        thread.start();

    }

}
