package com.example.cardgameapp.gamesCategorys;

import android.content.Intent;
import android.widget.ImageButton;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.Image;

public class DifferentGameObj {
    private Integer level;
    private String answer, category;
    private String picturePath1,picturePath2,picturePath3,picturePath4;
    private String name1,name2,name3,name4;



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImage1() {
        return name1;
    }

    public void setImage1(String name) {
        this.name1 = name;
    }
    public String getImage2() {
        return name2;
    }

    public void setImage2(String name) {
        this.name2 = name;
    }

    public String getImage3() {
        return name3;
    }

    public void setImage3(String name) {
        this.name3 = name;
    }

    public String getImage4() {
        return name4;
    }

    public void setImage4(String name) {
        this.name4 = name;
    }

    public String getDAnswer() {
        return answer;
    }

    public void setDAnswer(String answer) {
        this.answer = answer;
    }


    public String getCategory() {return category; }

    public String getPicturePath1() {
        return picturePath1;
    }

    public void setPicturePath1(String picturePath1) {
        this.picturePath1 = picturePath1;
    }

    public String getPicturePath2() {
        return picturePath2;
    }

    public void setPicturePath2(String picturePath2) {
        this.picturePath2 = picturePath2;
    }

    public String getPicturePath3() {
        return picturePath3;
    }

    public void setPicturePath3(String picturePath3) {
        this.picturePath3 = picturePath3;
    }

    public String getPicturePath4() {
        return picturePath4;
    }

    public void setPicturePath4(String picturePath4) {
        this.picturePath4 = picturePath4;
    }


    public void setCategory(String category) {
        this.category = category;
    }



    public DifferentGameObj(Integer level, String name1, String name2, String name3, String name4, String answer, String category
            , String picturePath1, String picturePath2, String picturePath3, String picturePath4) {
        this.answer = answer;
        this.level=level;
        this.picturePath1 = picturePath1;
        this.name1 = name1;
        this.picturePath2 = picturePath2;
        this.name2 = name2;
        this.picturePath3 = picturePath3;
        this.name3 = name3;
        this.picturePath4 = picturePath4;
        this.name4 = name4;
        this.answer = answer;
        this.category = category;

    }


    public DifferentGameObj() {
    }
}
