package com.yasin.day20demo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private static final String TAG =MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = (TextView) findViewById(R.id.view);
        view.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.view1);
        SpannableString string=new SpannableString("0123456789");
        string.setSpan(new BackgroundColorSpan(0xff00ff00),0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //flag 不起作用  一直是包括前面，不包括后面
        string.setSpan(new RelativeSizeSpan(2.0f),3,5,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //string.setSpan(new URLSpan("www.baidu.com"),17,string.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        Drawable drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,0,100,100);
        ImageSpan imageSpan=new ImageSpan(drawable);
        //flag 不起作用  一直是包括前面，不包括后面
        string.setSpan(imageSpan,7,9,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(string);
        EditText edit = (EditText) findViewById(R.id.edit);
        edit.setOnEditorActionListener(this);
    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (v.getId()) {
            case R.id.edit:
                Log.d(TAG, "onEditorAction: "+"hello");
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEND :
                        Log.d(TAG, "onEditorAction: "+"hello");
                        break;
                }
                break;
        }

        return true;
    }
}
