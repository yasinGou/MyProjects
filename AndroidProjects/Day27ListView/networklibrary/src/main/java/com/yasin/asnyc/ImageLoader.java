package com.yasin.asnyc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.yasin.networklibrary.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 * Project: com.yasin.asnyc
 * Created by Administrator
 * Date: 2016-08-31.
 */
public class ImageLoader extends AsyncTask<String,Void,Bitmap>{
    private ImageView image;
    private String url;

    public ImageLoader(ImageView image) {
        this.image = image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //加载图片的时候，给图片设置Tag  (view 的Tag专门携带一些用户需要的信息)
        //当此异步任务正在进行时，即loader不为空时，打断此异步任务。
        ImageLoader loader = (ImageLoader) image.getTag();
        if (loader != null) {
            //参数为false时  允许完成此次任务，即会执行doInBackground（）方法
            loader.cancel(false);
        }
        image.setTag(this);
        image.setImageResource(R.drawable.coming);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
       url=params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (code==200) {
                InputStream is = connection.getInputStream();
     //           Bitmap bitmap = BitmapFactory.decodeStream(is);
                int length;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] bytes=new byte[102400];
                while ((length=is.read(bytes))!=-1){
                    bos.write(bytes,0,length);
                }
                byte[] data = bos.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap!=null) {
            image.setImageBitmap(bitmap);
            ImageUtil.cache.put(url,bitmap);
        }else {
            image.setImageResource(R.drawable.ic_launcher);
        }
        image.setTag(null);
    }

    /**
     * 打断异步任务，但允许完成此次任务后调用此方法
     * @param bitmap
     */
    @Override
    protected void onCancelled(Bitmap bitmap) {
        if (bitmap != null) {
            ImageUtil.cache.put(url,bitmap);
        }
    }
   //即时打断异步任务
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
