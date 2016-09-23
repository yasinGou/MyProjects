package com.yasin.asnyc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Project: com.yasin.asnyc
 * Created by Administrator
 * Date: 2016-09-01.
 */
public class ImageDownloder implements Runnable{
    private String url;
    private File file;

    public ImageDownloder(File file, String url) {
        this.file = file;
        this.url = url;
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
                int length;
                byte[] bytes=new byte[102400];
                FileOutputStream fos = new FileOutputStream(file);
                while ((length=is.read(bytes))!=-1){
                    fos.write(bytes,0,length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
