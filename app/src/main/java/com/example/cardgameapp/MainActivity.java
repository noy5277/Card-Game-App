package com.example.cardgameapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.userLogIn.RegistrUserActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int SIGN_IN_FROM_CREATE = 1;
    private static final int SIGN_IN_FROM_END = 2;
    private Intent signInUp;
    private Intent Settings;

    //------------NEW ----------
    private TextView registerBtn;
    private Button signInBtn;
    private EditText editTextEmail ,editTextPassWord;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        registerBtn = (TextView) findViewById(R.id.registerLayoutBtn);
        registerBtn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.logInEmailText);
        editTextPassWord = (EditText) findViewById(R.id.LogInPasswordText);

        signInBtn = (Button) findViewById(R.id.loginB);
        signInBtn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerLayoutBtn:
                startActivity(new Intent(this, RegistrUserActivity.class));
                break;
            case R.id.loginB:
                UserLogIn();

        }
    }
    private void UserLogIn(){
        String email =editTextEmail.getText().toString().trim();
        String password =editTextPassWord.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email is not vaild");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextEmail.setError("password is required!");
            editTextEmail.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,MainGame.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Faild to login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    //-----------------------------------------------------------------------
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
            //logOut();
        if(item.getItemId()==R.id.settings)
        {
            openSettings();
        }
        return true;
    }

    private void openSettings()
    {
        Intent settings=new Intent(this,Settings.class);
        startActivity(settings);
    }


}