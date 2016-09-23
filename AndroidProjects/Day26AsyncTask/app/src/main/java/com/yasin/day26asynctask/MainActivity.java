package com.yasin.day26asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yasin.asnyc.*;
import com.yasin.networklibrary.Menu;
import com.yasin.networklibrary.NetIml;
import com.yasin.networklibrary.Tools;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NetworkTask.myCallback<Menu>{

    private WebView web;
    private static final String CSS="<style>img{max-width:100%}</style>";
    private ImageView image;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new 一个线程池 控制同时进行的异步任务
//        ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(2);

//        TextView text1 = (TextView) findViewById(R.id.main_text1);
//        new MyTask().execute(text1);
//        new MyTask().executeOnExecutor(executor,text1);
        web = (WebView) findViewById(R.id.main_web1);
        image = (ImageView) findViewById(R.id.main_img);
        ImageView img1 = (ImageView) findViewById(R.id.main_img1);
        textView = (TextView) findViewById(R.id.main_text);
        new MyTask().execute(textView,textView);
        NetIml instance = Tools.getInstance(NetIml.class);
        com.yasin.asnyc.NetworkTask<Menu> show = instance.getShow(10);
        show.execute(this);
//        File file = new File("/mnt/sdcard/image");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
        ImageDownloder downloade = new ImageDownloder(new File("/mnt/sdcard","image1.jpg"),
                "http://tnfs.tngou.net/image/cook/150802/79b05ad0532a0ef682ae920ff9b67764.jpg");
        new Thread(downloade).start();
        Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/image1.jpg");
        img1.setImageBitmap(bitmap);

    }
    @Override
    public void onSuccess(Menu menu) {
           // text.setText(menu.getMessage());
        new ImageLoader("http://tnfs.tngou.net/img"+menu.getImg(), textView).execute(image);
        web.loadDataWithBaseURL("http://www.tngou.net",CSS+menu.getMessage(),"text/html;charset=utf-8","utf-8",null);
    }
    @Override
    public void onFaile(Exception e) {
        Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
    }
}
