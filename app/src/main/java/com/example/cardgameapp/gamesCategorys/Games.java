package com.example.cardgameapp.gamesCategorys;

import androidx.annotation.NonNull;

import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
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

    public HashMap<Integer, SameGameObj> getSameGames() {
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

    public void InitSameGames()
    {
        FirebaseDatabase.getInstance().getReference().child("SameGame")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if(snapshot.exists())
                        {
                            for(DataSnapshot dss:snapshot.getChildren())
                            {
                                int level=dss.child("level").getValue(Integer.class);
                                SameGameObj game=dss.getValue(SameGameObj.class);
                                sameGames.put(level,game);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}
