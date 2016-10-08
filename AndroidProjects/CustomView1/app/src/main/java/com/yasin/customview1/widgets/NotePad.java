package com.yasin.customview1.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.yasin.customview1.R;

/**
 * Project: com.yasin.customview1.widgets
 * Created by Yasin
 * Date: 2016-09-28.
 */
public class NotePad extends EditText {
    private Paint paint;

    public NotePad(Context context) {
        this(context, null);
    }

    public NotePad(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    //初始化
    private void init(Context context, AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //  paint.setColor(Color.BLUE);
        float strokeWidth = 5;
        int lineColor = Color.GREEN;
        if (attrs != null) {
            //找出NotePad 自定义属性集合
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NotePad);
            lineColor = array.getColor(R.styleable.NotePad_lineColor, lineColor);
            strokeWidth = array.getDimension(R.styleable.NotePad_strokeWidth, strokeWidth);
            array.recycle();

        }
        paint.setColor(lineColor);
        //Paint 绘制的样式，可以指定颜色，填充，线的宽度，特效

        paint.setStrokeWidth(strokeWidth);//像素
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件的属性：宽度
        int width = getWidth();
        //获取左右padding
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int paddingTop = getPaddingTop();
        int lineHeight = getLineHeight();
        int lineCount = getLineCount();

        int height = getHeight();
        int paddingBottom = getPaddingBottom();
        int lines = (height - paddingBottom - paddingTop) / lineHeight;
        //计算最终的行数
        int finalLineCount = Math.max(lineCount, lines);
        //TODO:可见区域 进行内容绘制

        for (int i = 1; i <= finalLineCount; i++) {
            int lineY = lineHeight * i + paddingTop;
            canvas.drawLine(left, lineY, width - right, lineY, paint);
        }
        int y = getScrollY();
        Log.d("NotePad", "onDraw: " + y);
    }
}
