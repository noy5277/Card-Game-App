package com.example.cardgameapp.gamesCategorys;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Games {

    private HashMap<Integer,SameGameObj>  sameGames;
    private IDao db;
    private static Games mInstance;
    private HashMap<Integer, TextView> letters;


    public Games()
    {
        this.db= DaoFirebaseImpl.getInstance();
        this.sameGames=new HashMap<Integer,SameGameObj>();

    }

    public static Games getInstance() {
        if (mInstance == null) {
            mInstance = new Games();
        }
        return mInstance;
    }

    public HashMap<Integer, SameGameObj> GetSameGames() {
        return sameGames;
    }

    public void CreateSameGame(SameGameObj game)
    {
        sameGames.put(game.getLevel(),game);
    }

    public void WriteSameGames()
    {
        for(int level : sameGames.keySet())
        {
            db.writeNewSameGame(sameGames.get(level));
        }
    }



}
