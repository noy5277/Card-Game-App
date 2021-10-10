package com.example.cardgameapp;

import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreItem> {
    @Override
    public int compare(ScoreItem score1, ScoreItem score2) {
        if (score1.getScores() < score2.getScores())
            return 1;
        else if (score1.getScores() > score2.getScores())
            return -1;
        return 0;
    }
}
