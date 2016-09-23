package com.yasin.propertyanimatordemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.txt_hello);
    }

    public void btnPropertyMove(View view) {
//        float x = textView.getTranslationX();
//        textView.setTranslationX(x+10);
        //属性动画的加载
        //1.加载xml文件
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_move);
        //2.将要修改的对象传递给属性动画
        animator.setTarget(textView);
        animator.start();
    }

    public void btnPropertyColor(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_color);
        animator.setTarget(textView);
        animator.start();
    }

    public void btnScale(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_scale);
        animator.setTarget(textView);
        animator.start();
    }

    public void btnPropertyCustom(View view) {
        //自定义对象的属性修改
        //1.自定义的类对象，确认属性名
        TextShower shower = new TextShower(textView);
        //2.加载xml
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_textshow);
        //3.设置目标
        animator.setTarget(shower);
        animator.start();
    }

    public void btnPropertyCode(View view) {
        //代码属性动画
        //所有的属性的数值，如果是位置以像素为单位
        //ObjectAnimator.ofFloat(textView,"translationX",0,200).setDuration(2000).start();
        //利用set 可以实现多个属性同时修改
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationX", 0, 400).setDuration(2000);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "rotation", 0, 360).setDuration(2000);
        set.playTogether(animator,animator1);
        set.start();




    }
}
