package com.yasin.appclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yasin.remoteservicedemote.services.ICalcInterface;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private ICalcInterface calcInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定服务
        Intent intent = new Intent();
        intent.setClassName("com.yasin.remoteservicedemote","com.yasin.remoteservicedemote.services.CalcService");
        bindService(intent,this,BIND_AUTO_CREATE);

    }

    public void btnStartService(View view) {
        Intent intent = new Intent();

        //使用设置类名的形式可以调用其他程序的服务
        //参数1：远程程序的applicationId
        //参数2：要启动服务的类的全名
        intent.setClassName("com.yasin.remoteservicedemote","com.yasin.remoteservicedemote.services.CalcService");
        startService(intent);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        //AIDL生成的接口，提供了一个特定的，可以将IBinder装换为接口对象
        calcInterface = ICalcInterface.Stub.asInterface(service);
        try {
            int sum = calcInterface.add(2, 3);
            Log.d("Service", "onServiceConnected: "+sum);
            Toast.makeText(MainActivity.this, ""+sum, Toast.LENGTH_SHORT).show();
 
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
