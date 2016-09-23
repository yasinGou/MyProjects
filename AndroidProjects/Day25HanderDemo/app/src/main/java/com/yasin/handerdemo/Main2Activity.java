package com.yasin.handerdemo;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements ImageRunnable.MyCallback{

    private TextView text;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (TextView) findViewById(R.id.main2_text);
//        NetworkRunnable runnable=new NetworkRunnable("http:\\www.baidu.com",this);
        image = (ImageView) findViewById(R.id.main_img);
        ImageRunnable imageRunnabel = new ImageRunnable("http://wallpaperswide.com/download/egelsee_lake_in_carinthia_austria-1600x900.html?dw_url=download%2Fegelsee_lake_in_carinthia_austria-&dw_ratio=hd&dlw_block_wide=960x600&dlw_block_hd=1600x900&dlw_block_standard=800x600&dlw_block_mobile=320x480", this);
        new Thread(imageRunnabel).start();

        
    }

    @Override
    public void onSuccess(Bitmap bitmap) {
      //  this.text.setText(text);
        image.setImageBitmap(bitmap);

    }

    @Override
    public void onFail(Exception e, String msg) {

    }


    @Override
    public void onProgress(float percent) {
        text.setText(String.format(Locale.CHINA,"%.2f%%",percent));
    }
}
