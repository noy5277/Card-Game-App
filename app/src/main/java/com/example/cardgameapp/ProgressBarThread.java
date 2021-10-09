package com.example.cardgameapp;

import android.content.Intent;
import android.widget.ProgressBar;

public class ProgressBarThread extends Thread implements IObservable{

    private ProgressBar progressBar;
    private int status;
    private int seconds;
    private boolean exit=false;
    private IObserver observer;

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
            if(exit==false)
                Notify();
    }


    public void Exit()
    {
        this.status=-500;
        this.exit=true;
    }

    @Override
    public void Add(IObserver o) {
        this.observer=o;
    }

    @Override
    public void Remove(IObserver o) {
        this.observer=null;
    }

    @Override
    public void Notify() {
        this.observer.Update();
    }
}
