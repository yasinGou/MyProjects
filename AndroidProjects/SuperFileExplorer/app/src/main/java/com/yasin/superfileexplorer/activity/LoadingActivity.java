package com.yasin.superfileexplorer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yasin.superfileexplorer.R;

public class LoadingActivity extends AppCompatActivity {
    private Handler handler;
    private Intent intentSet;
    private Intent intentStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
        intentSet = new Intent(this, SetPassActivity.class);
        intentStart = new Intent(this, StartActivity.class);
        handler=new Handler(new Handler.Callback() {
           @Override
           public boolean handleMessage(Message msg) {
               if (msg.obj != null) {
                   if (msg.what==0) {
                       Boolean aBoolean = (Boolean) msg.obj;
                       if (aBoolean) {
                           startActivity(intentSet);
                           finish();
                       }else {
                           startActivity(intentStart);
                           finish();
                       }
                   }
               }
               return true;
           }
       });
        Thread myThread = new Thread(new MyThread());
        myThread.start();
        }

    public class MyThread implements Runnable{

        @Override
        public void run() {
            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            if (sp != null) {
                boolean isFirst = sp.getBoolean("isFirst", true);
                Message message = Message.obtain();
                message.what=0;
                message.obj= isFirst;
                handler.sendMessageDelayed(message,3000);
            }else{
                Message.obtain(handler,0,true).sendToTarget();
            }
        }
    }
}
