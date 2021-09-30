package com.example.cardgameapp.gamesCategorys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgameapp.Category;
import com.example.cardgameapp.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Different_Game extends AppCompatActivity {

    private Button backCategory;
    private TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_game);

        timer=findViewById(R.id.timerActivity);
        long duration= TimeUnit.MINUTES.toMillis(1);

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d", TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                timer.setText(sDuration);
            }

            @Override
            public void onFinish() {

                //when finish, hide text view
                timer.setVisibility(View.GONE);

                // Display toast
                Toast.makeText(getApplicationContext(), "Countdown timer has ended", Toast.LENGTH_LONG).show();
            }
        }.start();

        backCategory = findViewById(R.id.back_category);
        backCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCategory ();
            }
        });
    }
    public void OpenCategory() {
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

}