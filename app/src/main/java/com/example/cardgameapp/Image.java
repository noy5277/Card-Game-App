package com.example.cardgameapp;

public class Image {
    private String picturePath;
    private String name;
    private String answer;
    private String category;

    //different game
    private String picturePath1,picturePath2,picturePath3,picturePath4;
    private String name1,name2,name3,name4;

    public Image(String picturePath1,String picturePath2,String picturePath3,String picturePath4,
                 String name1,String name2, String name3, String name4, String category,String answer) {
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

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }




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
