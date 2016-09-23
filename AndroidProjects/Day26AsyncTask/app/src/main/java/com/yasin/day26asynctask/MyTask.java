package com.yasin.day26asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Project: com.yasin.day26asynctask
 * Created by Administrator
 * Date: 2016-08-31.
 */
public class MyTask extends AsyncTask<TextView,Integer,String> {
    private TextView text;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 唯一执行在子线程中的方法 不能进行UI的更新
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(TextView... params) {
        text = params[0];
        for (int i = 0; i <20 ; i++) {
            publishProgress(i); //执行的onProgessUpdate()
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return "已完成";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        text.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        text.setText(s);
    }
}
