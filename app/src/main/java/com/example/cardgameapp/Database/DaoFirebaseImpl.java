package com.example.cardgameapp.Database;

import androidx.annotation.NonNull;

import com.example.cardgameapp.User;
import com.example.cardgameapp.gamesCategorys.SameGameObj;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.cardgameapp.gamesCategorys.DifferentGameObj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaoFirebaseImpl implements IDao {

    public DatabaseReference mUsersTable;
    public DatabaseReference mSameGameTable;
    public FirebaseAuth mAuthDB;
    public DatabaseReference mDifferenTable;
    private static DaoFirebaseImpl mInstance;
    private String userId;

    public DaoFirebaseImpl() {
        this.mUsersTable = FirebaseDatabase.getInstance().getReference("Users");
        this.mSameGameTable=FirebaseDatabase.getInstance().getReference("SameGame");
        this.mAuthDB=FirebaseAuth.getInstance();
        this.mDifferenTable=FirebaseDatabase.getInstance().getReference("Different");
        this.userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
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

    public void writeNewDifferentGame(DifferentGameObj obj) {
        mDifferenTable.child(String.valueOf(obj.getDAnswer())).setValue(obj);
    }
    @Override
    public void writeNewSameGame(SameGameObj game) {
        mSameGameTable.child(Integer.toString(game.getLevel())).setValue(game);
    }



    @Override
    public void UpdateUser(Integer score) {
    String key=mUsersTable.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getKey();
    Map<String, Object> childUpdates = new HashMap<>();
    childUpdates.put(   key+"/score/",score);
    mUsersTable.updateChildren(childUpdates);
    }

    @Override
    public void SignOut() {
        mAuthDB.signOut();
    }

    @Override
    public Query orderByScore() {
        ArrayList<User> list=new ArrayList<User>();
        Query query = mUsersTable.orderByChild("Score");
        return query;
    }


}
