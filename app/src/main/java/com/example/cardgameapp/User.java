package com.example.cardgameapp;

public class User {

        private String fullName ,email,userName,password,id;
        private int score,clu,image;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getClu() {
        return clu;
    }

    public void setClu(int clu) {
        this.clu = clu;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public User() {
    }

    public User(String fullName, String email, String userName, String password, int image){
        this.userName=userName;
        this.fullName = fullName;
        this.email = email;
        this.password=password;
        this.score=0;
        this.clu=5;
        this.image=image;

    }

}