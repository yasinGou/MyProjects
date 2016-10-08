package com.yasin.customview1.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Project: com.yasin.customview1.widgets
 * Created by Yasin
 * Date: 2016-09-28.
 */
public class AlphaIndicator extends LinearLayout {
    public OnIndexSelectedListener onIndexSelectedListener;

    public interface OnIndexSelectedListener {
        void onIndexSelected(int index);
    }

    private int lastIndex;

    public AlphaIndicator(Context context) {
        this(context, null);
    }

    public AlphaIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setOnIndexSelectedListener(AlphaIndicator.OnIndexSelectedListener onIndexSelectedListener) {
        this.onIndexSelectedListener = onIndexSelectedListener;
    }

    private void init(Context context, AttributeSet attrs) {
        //代码方式添加控件到自身
        lastIndex = -1;
        TextView textView = new TextView(context);
        for (int i = 0; i < 26; i++) {
            textView.setText(Character.toString((char) ('A' + i)));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            addView(textView);
        }
    }

    //处理手指拖拽，形成不同字母的选择
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = true;
        int action = event.getAction();
        float eventX = event.getX();
        float eventY = event.getY();
        int index = -1;
        switch (action) {
            //按下的时候必须返回true
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                //TODO:根据事件的x,y，来查找在哪一个控件
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    int childTop = child.getTop();
                    int childBottom = child.getBottom();
                    if (eventY >= childTop && eventY <= childBottom) {
                        //在当前控件上
                        index = i;
                        break;
                    }
                }
                if (index != lastIndex) {
                    //Log.d("AI", "onTouchEvent: "+index);
                    onIndexSelectedListener.onIndexSelected(index);
                    lastIndex = index;
                }

                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                break;
        }

        return ret;
    }
}
