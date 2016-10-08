package com.yasin.hitballoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yasin.hitballoon.widgets.BalloonView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private BalloonView mBalloon;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBalloon = (BalloonView) findViewById(R.id.balloon_view);
        new Thread(this).start();

    }

    @Override
    public void run() {
        running=true;
        try {
            while (running){
                mBalloon.balloonMove();
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        running=false;
    }
}
