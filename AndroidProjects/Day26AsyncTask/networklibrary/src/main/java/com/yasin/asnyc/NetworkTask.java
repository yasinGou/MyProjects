package com.yasin.asnyc;
import android.os.AsyncTask;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Project: com.yasin.asnyc
 * Created by Administrator
 * Date: 2016-08-31.
 */
public class NetworkTask<T> extends AsyncTask<NetworkTask.myCallback<T>, Void,Object> {
    private myCallback<T> callback;
    private String url;
    private  Class<T> t;

    public NetworkTask(Class<T> t, String url) {
        this.t = t;
        this.url = url;
}

    @Override
    protected Object doInBackground(myCallback<T>... params) {
        callback=params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code==200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int length;
                byte[] bytes=new byte[102400];
                while ((length=is.read(bytes))!=-1){
                    bos.write(bytes,0,length);
                }
                Gson gson = new Gson();
                return  gson.fromJson(bos.toString("utf-8"), t);
            }else {
                return new RuntimeException("ResponseCode"+code);
            }
        } catch (IOException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (t.isInstance(o)) {
            callback.onSuccess(((T) o));
        }else if (o instanceof Exception){
            callback.onFaile(((Exception) o));
        }
    }
    public interface myCallback<T>{
        void onSuccess(T t);
        void onFaile(Exception e);
    }
}
