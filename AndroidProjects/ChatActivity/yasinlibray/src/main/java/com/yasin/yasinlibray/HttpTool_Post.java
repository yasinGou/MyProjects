package com.yasin.yasinlibray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Project: com.yasin.yasinlibray
 * Created by Yasin
 * Date: 2016-09-18.
 */
public final class HttpTool_Post {
    public HttpTool_Post() {
    }
    public static byte[] doPostJson(String url,String jsonString){
        byte[] ret=null;
        try {
             ret = doPost(url, jsonString.getBytes("utf-8"), "application/json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static byte[] doPost(String url,byte[] data,String contentType){
        byte[] ret=null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Encoding","gzip");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(60000);
            //POST 特有的
            if (data!=null&& data.length>0) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type",contentType);
                //输出数据到服务器
                OutputStream out = connection.getOutputStream();
                out.write(data);
               StreamUtil.close(out);
            }
            int code = connection.getResponseCode();
            if (code== HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                String encoding = connection.getContentEncoding();
                if ("gzip".equals(encoding)){
                    in=new GZIPInputStream(in);
                }
                ret = StreamUtil.readStream(in);
                StreamUtil.close(in);
           }
        }catch (IOException e){
            e.printStackTrace();
        }
     return ret;

    }
}
