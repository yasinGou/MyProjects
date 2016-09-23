package com.yasin.broadcastreceivedemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;

/**
 * Project: com.yasin.broadcastreceivedemo.receivers
 * Created by Yasin
 * Date: 2016-09-20.
 */

/**
 * 继承广播接收者，内部只有一个方法来接收数据
 */
public class NetworkReceiver extends BroadcastReceiver{
    private static final String TAG = "NetworkReceiver";
    
    /**
     * 当广播接收到的时候，回调这个方法，Intent内部包含了广播信息
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //广播接收者可以同时注册多个广播类型的接收
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            //1.intent中包含了广播的各种信息
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info!=null) {
                //有网络
                int type = info.getType();
                switch (type) {
                    case ConnectivityManager.TYPE_WIFI:
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        break;
                }
            }else {

            }

            //2.网络状态的检查 ，通常使用ConnectivityManager直接获取
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //找到系统正在连接的网络信息，返回null代表飞行模式
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null) {
                //有网络
                int type = networkInfo.getType();
                switch (type) {

                }
            }else {
                //无网络
            }


        }

    }
}
