package com.yasin.eventdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Project: com.yasin.eventdemo
 * Created by Yasin
 * Date: 2016-09-27.
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    // 控件在布局中使用，就需要这个构造
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("ED", "MyLinearLayoutdispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}
