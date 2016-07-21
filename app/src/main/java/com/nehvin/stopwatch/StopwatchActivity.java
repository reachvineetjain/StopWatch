package com.nehvin.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends Activity {

    private int seconds=0;
    private boolean running=false;
    private boolean wasRunning = running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        System.out.println("exiting onCreate");
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        System.out.println("exiting onSavedInstanceState");
    }

//    @Override
//    public void onStart()
//    {
//        super.onStart();
//        if(wasRunning)
//            running = true;
//        System.out.println("exiting onStart");
//    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(wasRunning)
            running = true;

    }
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        wasRunning = running;
//        running = false;
//        System.out.println("exiting onStop");
//    }

    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }
    //start the stopwatch when the start button is clicked
    public void onClickStart ( View view )
    {
        running = true;
    }
    //stop the stopwatch when the stop button is clicked
    public void onClickStop ( View view )
    {
        running = false;
    }

    public void onClickReset (View view)
    {
        running = false;
        seconds=0;
    }

    private void runTimer()
    {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}