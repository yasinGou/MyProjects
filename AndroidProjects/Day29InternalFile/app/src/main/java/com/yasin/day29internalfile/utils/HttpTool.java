package com.yasin.day29internalfile.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Project: com.yasin.day29internalfile.utils
 * Created by Administrator
 * Date: 2016-09-06.
 */
public final class HttpTool {
    private HttpTool(){}
    public static byte[] doGet(String url){
        byte[] ret=null;
        if (url!=null){
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setRequestProperty("Accept-Encoding","gizp");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
