package com.example.cardgameapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final int SIGN_IN_FROM_CREATE = 1;
    private static final int SIGN_IN_FROM_END=2;
    private Intent signInUp;
    private Intent Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInUp= AuthUI.getInstance().createSignInIntentBuilder().build();
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startActivityForResult(signInUp,SIGN_IN_FROM_CREATE);
        }
        else
        {
            sessionDetails(true);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==SIGN_IN_FROM_CREATE)
        {
            if(resultCode==RESULT_OK)
            {
                sessionDetails(true);
            }
            else
            {
                sessionDetails(false);
                finish();
            }
        }
        if(requestCode==SIGN_IN_FROM_END)
        {
            if(resultCode==RESULT_OK)
            {
                sessionDetails(true);
            }
            else
            {
                sessionDetails(false);
                finish();
            }
        }


    }

    private void sessionDetails(boolean success)
    {
        FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
        if(success)
        {
            String userDetails= "Connection successfully."+
                    " User: "+currentUser.getEmail();
            Toast.makeText(this,userDetails,Toast.LENGTH_LONG).show();
        }
        else
        {
            String userDetails= "Connection failed. Username: "+
                    currentUser.getDisplayName()+
                    ", id: "+currentUser.getUid()+ ", email: "+currentUser.getEmail();
            Toast.makeText(this,"Sign-in failed",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.log_out_item)
            logOut();
        if(item.getItemId()==R.id.settings)
        {
            openSettings();
        }
        return true;
    }

    private void logOut()
    {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(task -> {Toast.makeText(this,"Logged-out "
                +"successfully",Toast.LENGTH_SHORT).show();
                    startActivityForResult(signInUp,SIGN_IN_FROM_END);
                });
    }

    private void openSettings()
    {

    }

}