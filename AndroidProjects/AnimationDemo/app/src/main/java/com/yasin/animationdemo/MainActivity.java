package com.yasin.animationdemo;

import android.animation.Animator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    private TextView txt_zan;
    private TextView txt_Inc;
    private int zanCount=0;
    public AnimationDrawable animationDrawable;

    // 补间动画  没有操作控件的属性，而是把控件的显示调整了
    //动画完成后，还原状态
    // 控件本身不会移动
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_zan = (TextView) findViewById(R.id.txt_zan);
        txt_Inc = (TextView) findViewById(R.id.txt_inc);
        txt_zan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //TODO: +1 向上移动
        // 1.加载动画
        // 2.播放动画
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_zan_inc);
        // 3.设置动画的监听器 当动画开始，完成，重复时，进行回调
        anim.setAnimationListener(this);
        txt_Inc.setVisibility(View.VISIBLE);
        txt_Inc.startAnimation(anim);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        txt_Inc.setVisibility(View.INVISIBLE);
        zanCount++;
        txt_zan.setText("赞 "+"("+zanCount+")");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
//图片旋转
    public void btnDeleteClik(View view) {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        //设置插值器，平滑旋转
        anim.setInterpolator(new LinearInterpolator());
        view.startAnimation(anim);

    }

    public void btnMoveInterpolator(View view) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_move);
        anim.setDuration(1000);
        anim.setInterpolator(new OvershootInterpolator());
        view.startAnimation(anim);
    }

    public void btnScale(View view) {
        ImageView image = (ImageView) findViewById(R.id.image);
        if (image != null) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_heartbeat);
            animation.setDuration(1000);
            //对AnimationSet 设置重复，不起作用。
            //两种方式：
            //1.xml中不要Set,直接写动画种类
            //2.或者在xml中，设置重复
            animation.setRepeatCount(Animation.INFINITE);
          //  animation.setRepeatMode(Animation.REVERSE);
            image.startAnimation(animation);
        }
    }

    public void btnPlayFrame(View view) {
        //TODO:播放动画
        //1、逐帧动画，是一个drawable对象，从imageview中取出来
        //2.播放
        ImageView image =  (ImageView) findViewById(R.id.image_gift);
        //获取src属性
        Drawable drawable = image.getDrawable();

        if (drawable!=null && drawable instanceof AnimationDrawable) {
            animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.start();
        }


    }

    public void btnStopFrame(View view) {
        //TODO:停止动画
        //会将动画暂停，在下一次start，会从头开始播放
        animationDrawable.stop();
        //gif动画怎么显示   GifDrawable库, webView

    }
}
