package com.yasin.servicedemo.services;

import android.app.IntentService;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownLoadIntentService extends IntentService {

    public DownLoadIntentService() {
        super("DownLoadIntentService");
    }

    /**
     * 内部包含一个线程，线程会一直处理Intent参数
     * 每一次startService,传递的intent，都自动传递给线程，队列中保存，依次处理
     * 这个方法在子线程中执行
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        //任务只能依次执行，不能同时

        String url = intent.getStringExtra("url");
        if (url != null) {
            //TODO: 下载文件
        }
    }

    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
