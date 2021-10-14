package com.example.cardgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cardgameapp.userLogIn.RegistrUserActivity;

public class Avatar extends AppCompatActivity {
    public static final String INPUT_AVATAR="Avatar.INPUT_AVATAR";
    private Integer selectedAvatar;
    private int chosenImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }


    public void ChooseFemale_29(View view) {
        selectedAvatar=R.drawable.female_29;
    }


    public void ChooseMale_30(View view) {
        selectedAvatar=R.drawable.male_30;
    }


    public void ChooseMale_123(View view) {
        selectedAvatar=R.drawable.male_123;
    }

    public void ChooseMale_122(View view) {
        selectedAvatar=R.drawable.male_122;
    }

    public void ChooseFemale_1(View view) {
        selectedAvatar=R.drawable.female_1;
    }

    public void ChooseFemale_3(View view) {
        selectedAvatar=R.drawable.female_3;
    }

    public void ChooseFemale_2(View view) {
        selectedAvatar=R.drawable.female_2;
    }

    public void ChooseFemale_7(View view) {
        selectedAvatar=R.drawable.female_7;
    }

    public void Choosemale_121(View view) {
        selectedAvatar=R.drawable.male_121;
    }

    public void ChooseMale_124(View view) {
        selectedAvatar=R.drawable.male_124;
    }

    public void ChooseMale_31(View view) {
        selectedAvatar=R.drawable.male_31;
    }

    public void ChooseFemale_25(View view) {
        selectedAvatar=R.drawable.female_25;
    }

    public void ChooseFemale_18(View view) {
        selectedAvatar=R.drawable.female_18;
    }


    public void ChooseAvatar(View view) {
        Intent intent=new Intent(this, RegistrUserActivity.class);
        intent.putExtra(INPUT_AVATAR,selectedAvatar);
        startActivity(intent);

    }

}