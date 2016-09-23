package com.yasin.notificationdemo;

import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        NotificationManagerCompat.from(this).cancel(998);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

    }
}
