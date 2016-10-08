package com.yasin.eventdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Project: com.yasin.eventdemo.widgets
 * Created by Yasin
 * Date: 2016-09-27.
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //ListView 的测量方法
        int mode = MeasureSpec.getMode(heightMeasureSpec);//高度规则


        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        int size = MeasureSpec.getSize(heightMeasureSpec);//高度
        Log.d("MyListView", "onMeasure: "+size);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                requestDisallowInterceptTouchEvent(true);
//                break;
//        }
        return super.onTouchEvent(ev);
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        if (firstVisibleItem + visibleItemCount >= totalItemCount) {
//            requestDisallowInterceptTouchEvent(false);
//        }
//    }
}
