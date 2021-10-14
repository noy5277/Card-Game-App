package com.example.cardgameapp;

public class ScoreItem {

        private String usernames;
        private Integer scores;
        private  Integer images;

    public ScoreItem(String usernames,Integer scores, Integer images) {
        this.usernames = usernames;
        this.scores = scores;
        this.images = images;
    }

    public String getUsernames() {
        return usernames;
    }

    public Integer getScores() {
        return scores;
    }

    public Integer getImages() {
        return images;
    }
}
