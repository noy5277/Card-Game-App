package com.example.cardgameapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    PriorityQueue<ScoreItem> pq;

    public ProgramAdapter(@NonNull Context context, PriorityQueue<ScoreItem> pq,ArrayList<String> Usernames) {
        super(context, R.layout.single_item,R.id.title,Usernames);
        this.context=context;
        this.pq =pq;
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
        ScoreItem scores= pq.poll();
        holder.itemImage.setImageResource(scores.getImages());
        holder.title.setText(scores.getUsernames());
        holder.description.setText("Score: "+Integer.toString(scores.getScores()));
        return singleItem;
    }
}
