package com.example.cardgameapp.userLogIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    EditText mFullName,mEmail,mPassword;
    Button mRegister;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr_user);

        mFullName = (EditText)findViewById(R.id.registerNameInput);
        mEmail = (EditText)findViewById(R.id.registerEmailInput);
        mPassword = (EditText)findViewById(R.id.registerPasswordInput);

        mRegister = (Button)findViewById(R.id.registerBtn);
        mRegister.setOnClickListener(this);
        fAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registerBtn:
                registerUser();
                break;
        }
    }
    private void registerUser(){
        String  email = mEmail.getText().toString().trim();
        String  fullName = mFullName.getText().toString().trim();
        String  passWord = mPassword.getText().toString().trim();

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
        fAuth.createUserWithEmailAndPassword(email,passWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegistrUserActivity.this,"User has been registered",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegistrUserActivity.this, MainGame.class));

                                    }
                                    else{
                                        Toast.makeText(RegistrUserActivity.this,"Faild to registered user ",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegistrUserActivity.this,"Faild to registered user ",Toast.LENGTH_LONG).show();

                        }

                    }
                });


    }
}