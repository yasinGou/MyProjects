package com.yasin.customview2.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Project: com.yasin.customview2.widgets
 * Created by Yasin
 * Date: 2016-09-29.
 */
public class DrawPadView extends View {
    private Paint linePaint;
    private Paint rectPaint;
    private Paint textPaint;
    private Paint circlePaint;
    private Random random;
    private RectF arcRect;
    private Paint arcPaint;

    public DrawPadView(Context context) {
        this(context, null);
    }

    public DrawPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //初始化对象
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(2);

        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);

//      textPaint = new Paint();
//      textPaint.setColor(Color.GREEN);
        textPaint = new TextPaint();
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(100);

        circlePaint = new Paint();

        random = new Random();
        arcRect = new RectF();
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 绘制控件自身
     * onDraw方法，不允许创建对象
     * super.onDraw 代表控件原有的显示内容
     * onDraw方法是在排版、测量尺寸之后执行的，可以直接获取getWidth getHeight
     *
     * @param canvas
     */

    //Activity  在事件中才可以获取到控件的宽高
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int r = 0, g = 0, b = 0;
        int step = (255 - r) / 150;

        for (int i = 0; i < 150; i++) {
            int lineY = 50 + i;
            linePaint.setColor(Color.rgb(r + step * i, g + step * i, b + step * i));
            canvas.drawLine(20, lineY, 500, lineY, linePaint);
        }
        //  清屏 以特定颜色绘制整个屏幕
        canvas.drawColor(0xFFb5d7b5);

        //  指定Clip区域，实现绘制的裁剪
        //   设置区域之前，需要保存
        canvas.save();

        canvas.clipRect(200, 100, 400, 130);

        for (int i = 0; i < 150; i++) {
            linePaint.setColor(Color.rgb(r + step * i, g + step * i, b + step * i));
            canvas.drawLine(200 - i, 50 + i, 600 - i, 150 + i, linePaint);
        }
        //清除Clip
        canvas.restore();

        //  清屏 以特定颜色绘制整个屏幕
        canvas.drawColor(0xFFb5d7b5);

        //画矩形
        //Paint 可以控制style属性，来控制是否填充
        rectPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; ; i++) {
            int span = i * 5;
            int left = 50 - span;
            int top = 50 + span;
            int right = 200 - span;
            int bottom = 200 - span;
            if (left >= right || top >= bottom) {
                break;
            }
            canvas.drawRect(left, top, right, bottom, rectPaint);

        }

        canvas.drawColor(0xFFb5d7b5);
        int startTop = 280;

        for (int i = 0; i < 20; i++) {
            int span = i * 15;
            int left = 50 + span;
            int top = 50;
            int right = startTop - span;
            int bottom = startTop + 80 + span;

            canvas.drawRect(left, top, right, bottom, rectPaint);

        }

        canvas.drawColor(Color.BLACK);
        int width = getWidth();
        int height = getHeight();
        //需要注意对其方式，默认x,y都是文本的左下角
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("89", width >> 1, height >> 1, textPaint);


        //绘制圆形
        for (int i = 0; i < 300; i++) {
            int r1 = random.nextInt(256);
            int g1 = random.nextInt(256);
            int b1 = random.nextInt(256);
            int cx = random.nextInt(getWidth());
            int cy = random.nextInt(getHeight());
            circlePaint.setColor(Color.argb(0x66, r1, g1, b1));
            canvas.drawCircle(cx, cy, 50, circlePaint);
        }

        //饼图
        canvas.drawColor(Color.WHITE);
        //参数1 ：RecF包含浮点数的Rect
        arcRect.left = 50;
        arcRect.right = 50;
        arcRect.top = 150;
        arcRect.bottom = 150;
        //参数2：起始角度， 按照顺时针三点钟方向为0度
        //参数3：弧线经历的角度
        //参数4：两端点是否与中心点连接
        arcPaint.setColor(Color.GREEN);
        canvas.drawArc(arcRect, 45, 90, true, arcPaint);
        arcPaint.setColor(Color.RED);
        canvas.drawArc(arcRect, 135, 120, true, arcPaint);
        arcPaint.setColor(Color.RED);
        canvas.drawArc(arcRect, 255, 150, true, arcPaint);

        arcPaint.setColor(Color.BLUE);
        canvas.drawCircle(arcRect.centerX(), arcRect.centerY(), 25, arcPaint);

        float a1 = 360 * 0.64f;
        float b1 = 360 * 0.32f;
        float c1 = 360 - a1 - b1;
        arcPaint.setColor(Color.GREEN);
        canvas.drawArc(arcRect, 0, a1, true, arcPaint);
        arcPaint.setColor(Color.RED);
        canvas.drawArc(arcRect, a1, b1, true, arcPaint);
        arcPaint.setColor(Color.BLUE);
        canvas.drawArc(arcRect, a1 + b1, c1, true, arcPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
        }
        return true;
    }
}
