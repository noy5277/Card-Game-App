package com.example.cardgameapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public void PopupMenu(View view)
    {
        PopupMenu popup= new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.language_menu,popup.getMenu());
        popup.setOnMenuItemClickListener(menuItem ->
            {
                if(menuItem.getItemId()==R.id.english)
                {

                }
                if(menuItem.getItemId()==R.id.hebrew)
                {

                }
                return true;
            }

        );
    }
}