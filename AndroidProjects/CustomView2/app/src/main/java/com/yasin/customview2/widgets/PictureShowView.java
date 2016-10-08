package com.yasin.customview2.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.yasin.customview2.R;

/**
 * Project: com.yasin.customview2.widgets
 * Created by Yasin
 * Date: 2016-09-29.
 */
public class PictureShowView extends View {
    private Bitmap bitmap;
    private Rect showRect;
    private Rect srcRect;
    private int index = 0;
    private Paint shapePaint;

    public PictureShowView(Context context) {
        this(context, null);
    }

    public PictureShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //加载图片资源，完整加载原图
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sprit);
        showRect = new Rect();

        srcRect = new Rect();

        shapePaint = new Paint();
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        BitmapShader shader = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shapePaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //原图绘制，不进行缩放
        // canvas.drawBitmap(bitmap, 0, 0, null);
        //参数2：从原图上裁剪一个小的区域，null为原图
        //参数3：裁剪出来的图像，显示咋canvas的那个位置
        //right bottom 控制着 绘制图片的缩放情况
//        showRect.left = 0;
//        showRect.top = 0;
//        showRect.right = showRect.left + 400;
//        showRect.bottom = showRect.top + 600;
//
//        canvas.drawBitmap(bitmap, null, showRect, null);

        //裁剪绘制
        int bw = bitmap.getWidth();
        int bh = bitmap.getHeight();
        int numOfRow = 7;
        int numOfCol = 12;

        int perw = bw / numOfCol;
        int perh = bh / numOfRow;

        int column = index % 12;
        int row = index / 12;

        srcRect.left = perw * column;
        srcRect.top = perh * row;
        srcRect.right = srcRect.left + perw;
        srcRect.bottom = srcRect.bottom + perh;

        showRect.left = 0;
        showRect.top = 0;
        showRect.right = showRect.left + perw;
        showRect.bottom = showRect.top + perh;
        canvas.drawBitmap(bitmap, srcRect, showRect, null);

        //特定形状的图片
        //Paint 中可以指定图片渲染效果
        canvas.drawCircle(400,300,50,shapePaint);





    }
    public void showNext(){
        index++;
        if (index >= 64) {
            index =0;
        }
       //在主线程中刷新
     //   invalidate();
        //在子线程发起Handler,更新UI
        postInvalidate();
    }
}
