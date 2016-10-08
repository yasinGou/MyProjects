package com.yasin.hitballoon.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.yasin.hitballoon.R;

import java.util.Random;

/**
 * Project: com.yasin.hitballoon.widgets
 * Created by Yasin
 * Date: 2016-09-29.
 */
public class BalloonView extends View {
    private Bitmap mBitmap;
    private Random mRandom;
    private int index = 0;
    private int mHeight;
    private int mWidth;
    private int mBitmapHeight;
    private int mBitmapWidth;

    public BalloonView(Context context) {
        this(context, null);
    }

    public BalloonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attts) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.balloon);
        mRandom = new Random();
        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        mHeight = getHeight();
        int height = mHeight - index;

        // int bX=mRandom.nextInt()
        canvas.drawBitmap(mBitmap, mWidth, height, null);

    }

    public void balloonMove() {
        index += 50;
        if (index >= mHeight + mBitmapHeight/2) {
            index = 0;
            if (getWidth() != 0) {
                mWidth = mRandom.nextInt(getWidth()-mBitmapWidth);
            }
        }
        postInvalidate();
    }
}
