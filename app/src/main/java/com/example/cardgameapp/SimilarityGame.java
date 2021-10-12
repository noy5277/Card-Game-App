package com.example.cardgameapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.gamesCategorys.Games;
import com.example.cardgameapp.gamesCategorys.SameGameObj;
import com.example.cardgameapp.userLogIn.RegistrUserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimilarityGame extends AppCompatActivity implements IObserver {
    public static final String Next_Level="NEXT.LEVEL";
    private Games games;
    private int level=0;
    private HashMap<Integer, TextView> letters;
    private StringBuilder answer;
    private LinearLayout layoutAnswer;
    private HashMap<Integer, SameGameObj>  sameGames;
    private ImageView imageView1,imageView2,imageView3,imageView4;
    private static int indexAnswer;
    private static int countAnswer;
    private ProgressBar progressBar;
    private TextView lives, score;
    private int livesInt, scoreInt;
    private DatabaseReference sameGameReference;
    private DatabaseReference userReference;
    private HashMap<Integer,Integer> saveIndexLetters;
    private int answerSize;
    private String sourceAnswer;
    private ProgressBarThread thread;
    private IDao db;
    private FirebaseUser FBuser;
    private FirebaseAuth mAuth;
    private TextView levelView;
    private TextView quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarity_game);
        games= Games.getInstance();
        letters=new HashMap<Integer, TextView>();
        answer=new StringBuilder("");
        saveIndexLetters=new HashMap<Integer,Integer>();
        progressBar=findViewById(R.id.progressBar);
        indexAnswer=0;
        countAnswer=0;
        //GetScoreFromDB();
        livesInt=3;
        mAuth = FirebaseAuth.getInstance();
        FBuser = mAuth.getCurrentUser();
        db= DaoFirebaseImpl.getInstance();
        lives=findViewById(R.id.WIThartCount);
        score=findViewById(R.id.WITPcoins);
        quit=findViewById(R.id.exitLevelBtn);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        levelView=findViewById(R.id.WITPlevel);
        layoutAnswer=findViewById(R.id.answerLayout);
        sameGameReference=FirebaseDatabase.getInstance().getReference("SameGame");
        userReference=FirebaseDatabase.getInstance().getReference("Users");
        TaggingLetters();
        Init();

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


    public void InitLevel(SameGameObj game)
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
                    TextView dest=findViewById(saveIndexLetters.get(textView.getId()));
                    if(textView.getText()!=null)
                    {
                        textView.setText(null);
                        dest.setVisibility(View.VISIBLE);
                        saveIndexLetters.remove(textView.getId());
                        countAnswer--;
                        for(int i=0 ; i<answerSize;i++)
                        {
                            if(textView.findViewById(i)==null)
                            {
                                indexAnswer=textView.getId();
                                break;
                            }
                        }
                    }
                }
            });
            layoutAnswer.addView(to_add);
        }
    }


    public void Shuffle(String answer)
    {

        for(int index:letters.keySet())
        {
            letters.get(index).setVisibility(View.VISIBLE);
        }
        Random rand=new Random();
        int j=0;
        int ch;
        List<Character> characters = new LinkedList<>();
        for(char c:answer.toCharArray())
        {
            characters.add(c);
        }
        for(int i=0; i<12-answer.length();i++)
        {
            ch=97+rand.nextInt(25);
            characters.add((char) ch);
        }
        Collections.shuffle(characters);
        for(int index:letters.keySet())
        {
            letters.get(index).setText(characters.get(j).toString());
            j++;
        }
    }



    public void UpdateDetails()
    {
        lives.setText(Integer.toString(livesInt));
    }

    public void Init()
    {
        UpdateDetails();
        countAnswer = 0;
        indexAnswer = 0;
        layoutAnswer.removeAllViews();
        answer = new StringBuilder();
        String uid = FBuser.getUid();
        ValueEventListener scoreListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView score=findViewById(R.id.WITPcoins);
                score.setText(Integer.toString(snapshot.child("score").getValue(Integer.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        userReference.child(uid).addValueEventListener(scoreListener);

        ValueEventListener sameGameListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SameGameObj game = new SameGameObj();
                game.setAnswer(snapshot.child("answer").getValue().toString());
                game.setLevel(snapshot.child("level").getValue(Integer.class));
                game.setPiq1(snapshot.child("piq1").getValue(Integer.class));
                game.setPiq2(snapshot.child("piq2").getValue(Integer.class));
                game.setPiq3(snapshot.child("piq3").getValue(Integer.class));
                game.setPiq4(snapshot.child("piq4").getValue(Integer.class));
                answerSize = game.getAnswer().length();
                sourceAnswer = game.getAnswer();
                InitLevel(game);
                Shuffle(game.getAnswer());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };

        switch (level) {
            case 0:
                sameGameReference.child("0").addValueEventListener(sameGameListener);
                break;
            case 1:
                sameGameReference.child("1").addValueEventListener(sameGameListener);
                break;
            case 2:
                sameGameReference.child("2").addValueEventListener(sameGameListener);
                break;
            case 3:
                sameGameReference.child("3").addValueEventListener(sameGameListener);
                break;
            case 4:
                sameGameReference.child("4").addValueEventListener(sameGameListener);
                break;
            case 5:
                sameGameReference.child("5").addValueEventListener(sameGameListener);
                break;
            case 6:
                sameGameReference.child("6").addValueEventListener(sameGameListener);
                break;
        }
        StartGame();
    }

    public void StartGame()
    {
        thread=new ProgressBarThread(progressBar,60);
        thread.Add(this);
        thread.start();
    }

    @Override
    public void Update() {
        //time is up intent
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

    public void ChooseLetter(View view) {
        TextView source=findViewById(view.getId());
        TextView dest=findViewById(indexAnswer);
        dest.setText(source.getText());
        source.setVisibility(View.INVISIBLE);
        saveIndexLetters.put(dest.getId(),source.getId());
        indexAnswer++;
        countAnswer++;
        TextView textView;
        if(countAnswer<=answerSize)
        {
            if(countAnswer==answerSize)
            {
                for(int i=0;i<answerSize;i++)
                {
                    textView=findViewById(i);
                    answer.append(textView.getText());
                }
                Toast.makeText(this, Boolean.toString(answer.toString().equals(sourceAnswer)), Toast.LENGTH_SHORT).show();
                if(answer.toString().equals(sourceAnswer))
                {
                    level++;
                    levelView.setText(Integer.toString(level));
                    scoreInt=Integer.valueOf(score.getText().toString());
                    scoreInt+=10;
                    thread.Exit();
                    db.UpdateUser(scoreInt);

                    if(level<6)
                    {
                        Init();
                    }
                    if(level>5)
                    {
                        Intent intent=new Intent(this,Category.class);
                        startActivity(intent);
                    }

                }
                else
                {
                    if(livesInt!=0)
                    {
                        livesInt--;
                        lives.setText(Integer.toString(livesInt));
                        answer=new StringBuilder();
                    }
                    else
                    {
                        Intent intent=new Intent(this,lose.class);
                        startActivity(intent);
                    }
                }
            }
        }
    }
}