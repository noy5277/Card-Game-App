package com.example.cardgameapp;

public class Image {
    private String picturePath;
    private String name;
    private String answer;
    private String category;

    public Image(String picturePath, String name, String answer, String category) {
        this.picturePath = picturePath;
        this.name = name;
        this.answer = answer;
        this.category = category;
    }
    public Image(){};

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
