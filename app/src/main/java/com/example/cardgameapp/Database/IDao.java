package com.example.cardgameapp.Database;

import com.example.cardgameapp.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public interface IDao {

    void writeNewUser(User user);

    User getUser(String userId);

    void UpdateUser(User user);

    Query orderByScore();


}

