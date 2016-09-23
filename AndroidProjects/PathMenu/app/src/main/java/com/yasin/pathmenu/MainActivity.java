package com.yasin.pathmenu;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Animator.AnimatorListener {
    private boolean isOpen;
    private ImageView menu1;
    private ImageView menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu1 = (ImageView) findViewById(R.id.menu1);
        menu2 = (ImageView) findViewById(R.id.menu2);
    }


    public void btnPathMenu(View view) {
        float y = view.getY();
        double angle = 90 / 4d;
        if (!isOpen) {
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 135).setDuration(400);
            menu1.setVisibility(View.VISIBLE);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(menu1, "translationY", -100, -500).setDuration(900);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(menu1, "rotation", 0, 3600).setDuration(800);

            menu2.setVisibility(View.VISIBLE);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(menu2, "translationY", -100,-(float) (Math.sin(Math.toRadians(angle*3)) * 500)).setDuration(900);
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(menu2, "translationX", 0,(float) (Math.cos(Math.toRadians(angle*3)) * 500)).setDuration(900);
            ObjectAnimator animator5 = ObjectAnimator.ofFloat(menu2, "rotation", 0, 3600).setDuration(800);

            set.playTogether(animator,animator1,animator2,animator3,animator4,animator5);
            set.start();
            isOpen=true;
        }else {
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 135, 0).setDuration(400);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(menu1, "translationY", -500, -100).setDuration(900);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(menu1, "rotation", 0, 3600).setDuration(800);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(menu2, "translationY", -(float) (Math.sin(Math.toRadians(angle*3)) * 500),-100).setDuration(900);
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(menu2, "translationX",(float) (Math.cos(Math.toRadians(angle*3)) * 500), 0).setDuration(900);
            ObjectAnimator animator5 = ObjectAnimator.ofFloat(menu2, "rotation", 0, 3600).setDuration(800);

            set.playTogether(animator,animator1,animator2,animator3,animator4,animator5);
            set.addListener(this);
            set.start();
            isOpen=false;
        }


    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        menu1.setVisibility(View.INVISIBLE);
        menu2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
