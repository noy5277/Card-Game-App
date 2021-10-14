package com.example.cardgameapp.Database;

import com.example.cardgameapp.User;
import com.example.cardgameapp.gamesCategorys.SameGameObj;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.Query;
import com.example.cardgameapp.gamesCategorys.DifferentGameObj;

import java.util.ArrayList;

public interface IDao {
    public  User user = null;

    void writeNewUser(User user);

    void writeNewSameGame(SameGameObj game);

    void UpdateUser(Integer score);

    void writeNewDifferentGame(DifferentGameObj obj);

    Query orderByScore();


}

