package com.example.cardgameapp;

public class User {

        private String fullName ,email,userName,password,id;
        private int score,clu,image;


    public User(String fullName,String email,String userName,String password,int image){
        this.userName=userName;
        this.fullName = fullName;
        this.email = email;
        this.password=password;
        this.score=0;
        this.clu=5;
        this.image=image;

    }

    public int getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setClu(int clu) {
        this.clu = clu;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }


    public int getScore() {
        return score;
    }

    public int getClu() {
        return clu;
    }
}