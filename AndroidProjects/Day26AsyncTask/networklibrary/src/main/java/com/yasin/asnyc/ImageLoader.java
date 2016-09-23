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
public class ImageLoader extends AsyncTask<ImageView,String,Bitmap>{
    private String url;
    private ImageView image;
    private TextView textView;

    public ImageLoader(String url, TextView textView) {
        this.url = url;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // image.setImageResource(R.drawable.ic_launcher);
    }

    @Override
    protected Bitmap doInBackground(ImageView... params) {
        image=params[0];
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
                int sum=0;
                float percent;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] bytes=new byte[102400];
                while ((length=is.read(bytes))!=-1){
                    bos.write(bytes,0,length);
                    sum+=length;
                    percent=sum*100.0f/contentLength;
                    publishProgress(String.format(Locale.CHINA,"%.2f%%",percent));
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
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        textView.setText(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap!=null) {
            image.setImageBitmap(bitmap);
        }else {
            image.setImageResource(R.drawable.ic_launcher);
        }
    }
}
