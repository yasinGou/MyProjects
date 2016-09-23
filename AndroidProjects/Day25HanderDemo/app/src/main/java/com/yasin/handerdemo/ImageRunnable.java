package com.yasin.handerdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Project: com.yasin.handerdemo
 * Created by Administrator
 * Date: 2016-08-30.
 */
public class ImageRunnable implements Runnable ,Handler.Callback{
    private static final String TAG =ImageRunnable.class.getSimpleName() ;
    private String url;
    private MyCallback callback;
    private Handler handler;

    public ImageRunnable(String url, MyCallback callback) {
        this.url = url;
        this.callback = callback;
        handler=new Handler(Looper.getMainLooper(),this);
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code==200) {
                InputStream is = connection.getInputStream();
               // Bitmap bitmap = BitmapFactory.decodeStream(is);
                int contentLength = connection.getContentLength();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int length;
                byte[] bytes=new byte[102400];
                while ((length=is.read(bytes))!=-1){
                    bos.write(bytes,0,length);
                    handler.obtainMessage(2,bos.size(),contentLength).sendToTarget();
                }
                byte[] byteArray = bos.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,byteArray.length);
                handler.obtainMessage(0,bitmap).sendToTarget();
            }else {
                RuntimeException exception = new RuntimeException("ResponseCode" + code);
                String msg="服务器异常";
                Bundle data=new Bundle();
                data.putString("msg",msg);
                data.putSerializable("exception",exception);
                Message message = handler.obtainMessage(1);
                message.setData(data);
                handler.sendMessage(message);
            }

        } catch (IOException e) {
            String msg="网络异常";
            RuntimeException exception = new RuntimeException("网络错误");
            Bundle data=new Bundle();
            data.putString("msg",msg);
            data.putSerializable("exception",exception);
            Message message = handler.obtainMessage(1);
            message.setData(data);
            handler.sendMessage(message);

        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                Log.d(TAG, "handleMessage: "+Thread.currentThread().getName());
                callback.onSuccess(((Bitmap) msg.obj));
                break;
            case 1:
                Bundle data = msg.getData();
                String string = data.getString("msg");
                Exception exception = (Exception) data.getSerializable("exception");
                callback.onFail(exception,string);
                break;
            case 2:
                Log.d(TAG, "handleMessage: "+Thread.currentThread().getName());
                float percent = msg.arg1 * 100.0f / msg.arg2;
                callback.onProgress(percent);
                break;
        }
        return true;
    }

    public interface MyCallback{
        void onSuccess(Bitmap bitmap);
        void onFail(Exception e, String msg);
        void onProgress(float percent);
    }
}
