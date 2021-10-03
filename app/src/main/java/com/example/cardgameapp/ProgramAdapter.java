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
    ArrayList<Integer> images;
    ArrayList<String> usernames;
    ArrayList<String> scores;


    public ProgramAdapter(@NonNull Context context, ArrayList<String> usernames,ArrayList<String> scores,ArrayList<Integer> images) {
        super(context, R.layout.single_item,R.id.title,usernames);
        this.context=context;
        this.images=images;
        this.usernames=usernames;
        this.scores=scores;
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
        holder.itemImage.setImageResource(images.get(position));
        holder.title.setText(usernames.get(position));
        holder.description.setText(scores.get(position));
        return singleItem;
    }
}
