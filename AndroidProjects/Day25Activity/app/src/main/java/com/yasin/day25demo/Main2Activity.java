package com.yasin.day25demo;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private long time=0;

    private static final String TAG =Main2Activity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView text = (TextView) findViewById(R.id.text2);
        BaseApplication application = (BaseApplication) getApplication();
        String text1 = application.getText();
        text.setOnClickListener(this);
        text.setText(text1);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (SystemClock.uptimeMillis()-time<3000) {
//          //  finishAffinity();
//            ActivityCompat.finishAffinity(this);
//        }else {
//            Toast.makeText(Main2Activity.this, "在按一次退出", Toast.LENGTH_SHORT).show();
//            time=SystemClock.uptimeMillis();
//        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra("data","Main2Activity返回");
        setResult(RESULT_OK,intent);
        startActivity(intent);
       // finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
    
}
