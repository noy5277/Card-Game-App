package com.example.cardgameapp.Database;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.cardgameapp.User;
import com.example.cardgameapp.gamesCategorys.SameGameObj;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaoFirebaseImpl extends Application implements IDao {

    public DatabaseReference mUsersTable;
    public DatabaseReference mSameGameTable;
    private FirebaseUser mUser;
    public User user;
    public FirebaseAuth mAuthDB;
    private static DaoFirebaseImpl mInstance;
    private String userId;
    public DaoFirebaseImpl() {
    }
    public void build(){
        this.mUsersTable = FirebaseDatabase.getInstance().getReference("Users");
        this.mSameGameTable=FirebaseDatabase.getInstance().getReference("SameGame");
        this.mAuthDB = FirebaseAuth.getInstance();
        this.mUser = mAuthDB.getCurrentUser();
    }
    public static DaoFirebaseImpl getInstance() {
        if (mInstance == null) {
            mInstance = new DaoFirebaseImpl();
        }
        return mInstance;
    }

    @Override
    public void writeNewUser(User user) {
        mAuthDB.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete())
                {
                    mUsersTable.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                }
            }
        });
    }

    @Override
    public void writeNewSameGame(SameGameObj game) {
        mSameGameTable.child(Integer.toString(game.getLevel())).setValue(game);
    }

    @Override
    public User getUser(String userId) {
        Task<DataSnapshot> task = mUsersTable.child(userId).get();
        User user = task.getResult().getValue(User.class);
        return user;
    }

    private Map<String, Object> userToMap(User user) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("clu", user.getClu());
        result.put("score", user.getScore());
        return result;
    }

    @Override
    public void UpdateUser(User user) {
        Map<String, Object> userValues = userToMap(user);
        Map<String, Object> childUpdates = new HashMap<>();

        for (Map.Entry<String, Object> entry : userValues.entrySet()) {
            childUpdates.put("/"+mUser.getUid() +"/" + entry.getKey(), entry.getValue());
        }

        mUsersTable.updateChildren(childUpdates);
    }

    @Override
    public Query orderByScore() {
        ArrayList<User> list=new ArrayList<User>();
        Query query = mUsersTable.orderByChild("Score");
        return query;
    }
    @Override
    public String getCurrentUserId(){
        return mUser != null? mUser.getUid() :"";
    }


}
