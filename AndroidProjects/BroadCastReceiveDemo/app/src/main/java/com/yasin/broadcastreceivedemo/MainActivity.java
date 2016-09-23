package com.yasin.broadcastreceivedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//BroadCastReceiver 动态注册部分:
    //1.创建一个BroadCastReceiver对象
    //2.通过代码注册需要接收哪些广播信息
    //3.不需要接收的时候，直接取消注册就可以了。

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        //onReceiver方法的特点
        //1.默认执行在主线程 ，希望运行在子线程，使用Handler
        //2.此方法在主线程中执行时，允许执行最长为10s，超过则被系统强制销毁
        //3.静态注册的广播接收者，只要onReceive方法完成，那么创建的对象被系统删除

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                //BatteryManager 内部定义了Intent中包含的各种Extra
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = this;
        IntentFilter filter = new IntentFilter();
        //电量变化的广播只能使用动态注册
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        context.registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消注册
        unregisterReceiver(receiver);
    }

    public void btnSenBroadCast(View view) {
        Intent intent = new Intent("gyz.ACTION_TEST_0");
        //发送的信息
        intent.putExtra("name", "admin");
        //发送无序广播，不分先后.无法终止
        this.sendBroadcast(intent);
    }

    public void btnSendOrderedBroadCast(View view) {
        Intent intent = new Intent("gyz.ACTION_TEST_0");
        //有序广播可以被终止
        // sendOrderedBroadcast(intent,null);
        //参数2是定义的权限，只有声明使用这个权限的广播接受者才可以接收到广播消息

        sendOrderedBroadcast(intent, "yasin.broadcastreceiver.permission.TEST_1");


    }

    public void btnSendLocalBroadCast(View view) {
        //本地广播管理器
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("com.yasin.ACTION_TEST");
        manager.sendBroadcast(intent);
        //发送广播并且等待所有的广播发送给接受者，当所有广播接收者执行完成，此方法结束
        manager.sendBroadcastSync(intent);

    }
}
