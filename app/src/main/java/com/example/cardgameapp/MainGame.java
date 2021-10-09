package com.example.cardgameapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainGame extends AppCompatActivity {

    private Button OpenCategoryButton;
    private ImageView settingsBtn,scoreBtn;
    private TextView userName,playerScore;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        userName = (TextView) findViewById(R.id.userNameText);
        playerScore = (TextView) findViewById(R.id.palyerScoreMain);

        getUserInfo();
        OpenCategoryButton = (Button) findViewById(R.id.startGameBtn);
        OpenCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCategory();
            }
        });
        settingsBtn =  (ImageView) findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opensettings();
            }
        });
        scoreBtn = (ImageView) findViewById(R.id.scoreListbtn);
        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenScoreActivity();
            }
        });
    }
    public void OpenCategory() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }
    public void Opensettings() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

    public void OpenScoreActivity() {
        Intent scores=new Intent(this,Score.class);
        startActivity(scores);
    }
    private void buildGame(){
        if (!user.getUserName().isEmpty())
        {
            userName.setText(user.getUserName());
            playerScore.setText(String.valueOf(user.getScore()));
        }
    }
    private void getUserInfo(){
        String uid = "";
        try {
            uid = ((DaoFirebaseImpl) this.getApplication()).getCurrentUserId();
            user = ((DaoFirebaseImpl) this.getApplication()).getUser(uid);
            buildGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}