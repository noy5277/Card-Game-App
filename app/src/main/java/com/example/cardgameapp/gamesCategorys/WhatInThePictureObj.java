package com.example.cardgameapp.gamesCategorys;

public class WhatInThePictureObj {
    private String picturePath;
    private int level;
    private String answer;

    public WhatInThePictureObj(String picturePath, String answer,int level) {
        this.picturePath = picturePath;
        this.level = level;
        this.answer = answer;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public WhatInThePictureObj() {
    }
}
