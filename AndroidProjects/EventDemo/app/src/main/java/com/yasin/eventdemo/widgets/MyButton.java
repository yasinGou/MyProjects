package com.yasin.eventdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Project: com.yasin.eventdemo
 * Created by Yasin
 * Date: 2016-09-27.
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    /**
     * 当控件本身没有子控件时，或者无被点击的控件
     * 就会调用View内部的dispatchTouchEvent
     * 调用此方法，处理触摸事件
     * @param event
     * @return  true 当前控件处理了触摸事件，false交给父容器处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Log.d("MainActivity", "onTouchEvent: "+ action);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MainActivity", "onTouchEvent: 被按下了");
                break;

        }
        return super.onTouchEvent(event);
    }
}
