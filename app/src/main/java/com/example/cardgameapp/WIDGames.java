package com.example.cardgameapp;

import android.widget.TextView;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.gamesCategorys.DifferentGameObj;
import com.example.cardgameapp.gamesCategorys.Games;
import com.example.cardgameapp.gamesCategorys.SameGameObj;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

public class WIDGames {
    private IDao db;
    private static WIDGames dInstance;
    private HashMap<Integer,DifferentGameObj>  differentGames;


    public WIDGames()
    {
        this.db= DaoFirebaseImpl.getInstance();
        this.differentGames=new HashMap<Integer, DifferentGameObj>();
    }



    public static WIDGames getInstance() {
        if (dInstance == null) {
            dInstance = new WIDGames();
        }
        return dInstance;
    }

    public HashMap<Integer, DifferentGameObj> GetDifferentGames() {
        return differentGames;
    }

    public void CreateDifferentGame(DifferentGameObj game)
    {
        differentGames.put(game.getLevel(),game);
    }

    public void WriteDifferentGames()
    {
        for(int level : differentGames.keySet())
        {
            db.writeNewDifferentGame(differentGames.get(level));
        }
    }
}
