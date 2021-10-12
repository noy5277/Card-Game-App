package com.example.cardgameapp.gamesCategorys;

import android.content.Intent;
import android.widget.ImageButton;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.Image;

public class DifferentGameObj {
    private int level;
    private int answer;
    private int picturePath1,picturePath2,picturePath3,picturePath4;



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getDAnswer() {
        return answer;
    }

    public void setDAnswer(int answer) {
        this.answer = answer;
    }



    public int getPicturePath1() {
        return picturePath1;
    }

    public void setPicturePath1(int picturePath1) {
        this.picturePath1 = picturePath1;
    }

    public int getPicturePath2() {
        return picturePath2;
    }

    public void setPicturePath2(int picturePath2) {
        this.picturePath2 = picturePath2;
    }

    public int getPicturePath3() {
        return picturePath3;
    }

    public void setPicturePath3(int picturePath3) {
        this.picturePath3 = picturePath3;
    }

    public int getPicturePath4() {
        return picturePath4;
    }

    public void setPicturePath4(int picturePath4) {
        this.picturePath4 = picturePath4;
    }


    public DifferentGameObj(int answer,int picturePath1, int picturePath2, int picturePath3, int picturePath4,int level) {
        this.answer = answer;
        this.level=level;
        this.picturePath1 = picturePath1;
        this.picturePath2 = picturePath2;
        this.picturePath3 = picturePath3;
        this.picturePath4 = picturePath4;
        this.answer = answer;
    }

    public DifferentGameObj() {
    }
}