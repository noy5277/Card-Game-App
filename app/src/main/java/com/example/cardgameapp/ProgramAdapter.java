package com.example.cardgameapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<ScoreItem> scores;


    public ProgramAdapter(@NonNull Context context,ArrayList<ScoreItem> scores,ArrayList<String> UserNames) {
        super(context, R.layout.single_item,R.id.title, UserNames);
        this.context=context;
       this.scores =scores;
    }


    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {

        View singleItem=convertView;
        ProgramViewHolder holder= null;
        if(singleItem==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            singleItem=layoutInflater.inflate(R.layout.single_item,parent,false);
            holder=new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else
        {
            holder= (ProgramViewHolder) singleItem.getTag();
        }

        holder.itemImage.setImageResource(scores.get(position).images);
        holder.title.setText(scores.get(position).usernames);
        holder.description.setText(scores.get(position).scores);
        return singleItem;
    }
}
