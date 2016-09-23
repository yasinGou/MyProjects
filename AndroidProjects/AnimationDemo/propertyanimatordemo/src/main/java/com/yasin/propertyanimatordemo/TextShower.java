package com.yasin.propertyanimatordemo;

import android.util.Log;
import android.widget.TextView;

/**
 * Project: com.yasin.propertyanimatordemo
 * Created by Yasin
 * Date: 2016-09-12.
 */
public class TextShower {
private TextView textView;
    private String[] strArray={"你好","我好","大家好"};
    public TextShower(TextView view) {
        textView=view;
    }

    public void setIndex(int index){
        Log.d("TextShower", "setIndex: "+index);
        textView.setText(strArray[index % strArray.length]);
    }

}
