package com.yasin.loaderdemo.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.yasin.yasinlibray.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Project: com.yasin.loaderdemo.Loaders
 * Created by Administrator
 * Date: 2016-09-10.
 */
public class NetworkLoader extends AsyncTaskLoader<JSONObject> {
    private String url;

    public NetworkLoader(Context context,String url){
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        //这个方法必须要重写，如果没有，数据不会自动加载
        //会强制触发异步任务的加载，执行 loadInBackground
        forceLoad();

    }

    /**
     * 子线程加载数据的部分
     * @return
     */
    @Override
    public JSONObject loadInBackground() {
        JSONObject ret=null;
        byte[] data = HttpTool.doGet(url);
        //网络连接后，考虑cancel
        if (isLoadInBackgroundCanceled()) {
//            不处理，不返回
        }else {
            if (isReset()) {
                   //不返回数据，保存到本地
            }else {
                if (data != null) {
                    try {
                        String str = new String(data, 0, data.length, "utf-8");
                        ret = new JSONObject(str);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ret;
    }
}
