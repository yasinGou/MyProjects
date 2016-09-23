package com.yasin.networklibrary;

import com.google.gson.Gson;
import com.yasin.asnyc.NetworkTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 * Project: com.yasin.networklibrary
 * Created by Administrator
 * Date: 2016-08-31.
 */
public class Tools {
    public static <T> T getInstance(Class<T> type){
        Object o = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MyHandler());
        return ((T) o);
    }

    private static class MyHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            UrlString annotation = method.getAnnotation(UrlString.class);
            if (annotation!=null) {
            String url = String.format(Locale.CHINA, annotation.value(), args);
            Class<?> returnType = method.getReturnType();
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("GET");
//            connection.setDoInput(true);
//            int code = connection.getResponseCode();
//            if (code==200) {
//                InputStream is = connection.getInputStream();
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                int length;
//                byte[] bytes=new byte[102400];
//                while ((length=is.read(bytes))!=-1){
//                    bos.write(bytes,0,length);
//                }
//                Gson gson = new Gson();
//                return gson.fromJson(bos.toString("utf-8"), Menu.class);
//            }
            if (returnType.equals(NetworkTask.class)) {
                ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
                Type genericType = type.getActualTypeArguments()[0];
                return new NetworkTask<>(((Class) genericType),url);
            }
            }
            return null;
        }
    }
}
