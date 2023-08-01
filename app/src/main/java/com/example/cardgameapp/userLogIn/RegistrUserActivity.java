package com.example.cardgameapp.userLogIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardgameapp.Avatar;
import com.example.cardgameapp.Database.DaoFirebaseImpl;
import com.example.cardgameapp.Database.IDao;
import com.example.cardgameapp.MainActivity;
import com.example.cardgameapp.MainGame;
import com.example.cardgameapp.R;
import com.example.cardgameapp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrUserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mFullName,mEmail,mPassword,mUserName;
    Button mRegister;
    FirebaseAuth fAuth;
    private User user;
    private static IDao DB;
    private ProgressBar progressBar;
    public static final String INPUT_AVATAR="Avatar.INPUT_AVATAR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr_user);

        mFullName = (EditText)findViewById(R.id.registerNameInput);
        mEmail = (EditText)findViewById(R.id.registerEmailInput);
        mPassword = (EditText)findViewById(R.id.registerPasswordInput);
        mUserName=(EditText)findViewById(R.id.registerUserNameInput);
        mRegister = (Button)findViewById(R.id.registerBtn);
        progressBar=findViewById(R.id.progressBar2);
        mRegister.setOnClickListener(this);
        fAuth = FirebaseAuth.getInstance();
        DB= DaoFirebaseImpl.getInstance();

    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        switch (v.getId())
        {
            case R.id.registerBtn:
                registerUser();
                break;
        }
    }
    private void registerUser(){

        String  email = mEmail.getText().toString();
        String  fullName = mFullName.getText().toString();
        String  passWord = mPassword.getText().toString();
        String  userName=mUserName.getText().toString();
        Bundle extras=getIntent().getExtras();
        Integer avatar=extras.getInt(INPUT_AVATAR);
        user=new User(fullName,email,userName,passWord,avatar);

        if (fullName.isEmpty()){
            mFullName.setError("Full name is requried");
            mFullName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            mEmail.setError("email is requried");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("please provide vaild email");
            mEmail.requestFocus();
            return;
        }
        if (passWord.isEmpty()) {
            mPassword.setError("mPassword is requried");
            mPassword.requestFocus();
            return;
        }
        if (userName.isEmpty()) {
            mUserName.setError("mPassword is requried");
            mUserName.requestFocus();
            return;
        }

        try{
            DB.writeNewUser(user);
            Toast.makeText(RegistrUserActivity.this,"User has been registered",Toast.LENGTH_LONG).show();
            Thread.sleep(9000);
            startActivity(new Intent(RegistrUserActivity.this, MainGame.class));
        }catch (Exception e)
        {
            Toast.makeText(RegistrUserActivity.this,"register failed",Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegistrUserActivity.this, MainActivity.class));
        }

    }
}