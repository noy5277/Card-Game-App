package com.example.cardgameapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {

    ImageView itemImage;
    TextView title;
    TextView description;
    ProgramViewHolder(View v)
    {
        itemImage=v.findViewById(R.id.imageView);
        title=v.findViewById(R.id.title);
        description=v.findViewById(R.id.description);
    }

}
