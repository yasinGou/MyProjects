package com.yasin.eventdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class SlideActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout menulayout;
    private LinearLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        menulayout = (LinearLayout) findViewById(R.id.menu_layout);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        contentLayout.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean ret = false;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //返回true，才可以继续接收move
                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }

        return ret;
    }
}
