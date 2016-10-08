package com.yasin.eventdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoll_test);
        ListView listView = (ListView) findViewById(R.id.list_view);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            data.add("android test -"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        listView.setAdapter(adapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("ED", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        Log.d("MainActivity", "onClick: 点击");
    }

//    @Override
//    public void onClick(View v) {
//        Log.d("MainActivity", "onClick: 点击了一下");
//
//    }


}
