package com.example.cardgameapp.gamesCategorys;

import com.example.cardgameapp.Image;

public class DifferentGameObj {
    private int level;
    private Image image;
    private String answer;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public DifferentGameObj(int level, Image image) {
        this.level = level;
        this.image = image;
    }
    public DifferentGameObj() {
    }
}
