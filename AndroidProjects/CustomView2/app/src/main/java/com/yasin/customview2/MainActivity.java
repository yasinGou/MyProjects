package com.yasin.customview2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yasin.customview2.widgets.PictureShowView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private PictureShowView showView;
    private boolean runing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showView = (PictureShowView) findViewById(R.id.show_view);
        Thread thread = new Thread(this);
        thread.start();

    }

    public void btnSwitch(View view) {

        // showView.showNext();
    }

    @Override
    public void run() {
        runing = true;
        try {
            while (runing) {
                showView.showNext();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        runing=false;
    }
}
