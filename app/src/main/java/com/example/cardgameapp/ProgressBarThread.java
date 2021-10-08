package com.example.cardgameapp;

import android.content.Intent;
import android.widget.ProgressBar;

public class ProgressBarThread extends Thread{

    private ProgressBar progressBar;
    private int status;
    private int seconds;
    public ProgressBarThread(ProgressBar progressBar,int sec)
    {
        this.progressBar=progressBar;
        this.seconds=sec;
        this.status=100;
    }

    @Override
    public void run()
    {
        while(status>0)
        {
            status=status-(seconds*2/100);
            progressBar.setProgress(status);
            try
            {

                Thread.sleep(500);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        progressBar.setProgress(100);

    }
}
