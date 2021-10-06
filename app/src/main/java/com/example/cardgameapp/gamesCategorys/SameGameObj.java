package com.example.cardgameapp.gamesCategorys;

public class SameGameObj {
    private int piq1,piq2,piq3,piq4;
    private String answer;
    private int level;

    public SameGameObj(int piq1,int piq2,int piq3,int piq4, String answer, int level)
    {
        this.piq1=piq1;
        this.piq2=piq2;
        this.piq3=piq3;
        this.piq4=piq4;
        this.answer=answer;
        this.level=level;
    }


    public void setPiq1(int piq1) {
        this.piq1 = piq1;
    }

    public void setPiq2(int piq2) {
        this.piq2 = piq2;
    }

    public void setPiq3(int piq3) {
        this.piq3 = piq3;
    }

    public void setPiq4(int piq4) {
        this.piq4 = piq4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPiq1() {
        return piq1;
    }

    public int getPiq2() {
        return piq2;
    }

    public int getPiq3() {
        return piq3;
    }

    public int getPiq4() {
        return piq4;
    }

    public String getAnswer() {
        return answer;
    }

    public int getLevel() {
        return level;
    }
}
