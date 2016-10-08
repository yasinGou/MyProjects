package com.yasin.customview1.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project: com.yasin.customview1.widgets
 * Created by Yasin
 * Date: 2016-09-28.
 */

/**
 * 流式布局，从左到右，从上到下，排版控件
 * 通常就是标签列表和个人印象 功能
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    protected LayoutParams generateDefaultLayoutParams() {
//        return new MarginLayoutParams();
//    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    /**
     * 需要测量自身的尺寸，如果是ViewGroup,先测量子控件
     * 然后测量自己
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //所有子控件按照最大容器控件来计算尺寸
        //这个方法会在onlayout之前就调用
        //可以通过子控件的 getMeasureWidth()/getMeasureHeight()来获取控件宽高
        // measureChildren(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
        }


        //计算自身的宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * @param changed 代表布局被修改了
     * @param l       当前容器在父容器中的left位置
     * @param t       当前容器在父容器中的top位置
     * @param r       当前容器在父容器中的right位置
     * @param b       当前容器在父容器中的bottom位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历所有的控件，进行位置摆放
        int count = getChildCount();
        int lastLeft = 20;
        int lastBottom = 20;
        //每一行最高的尺寸，换行时处理
        int maxHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            int right = lastLeft + measuredWidth;

            if (right > r - l) {//r-l 容器的宽度
                lastLeft = 20;
                lastBottom += maxHeight;
                right = lastLeft + measuredWidth;
                maxHeight = measuredHeight;
            } else {
                maxHeight = Math.max(maxHeight, measuredHeight);
            }
            child.layout(lastLeft, lastBottom, right, lastBottom + measuredHeight);
            lastLeft += measuredWidth;
        }

    }
}
