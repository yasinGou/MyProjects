package com.yasin.contentproviderdemo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //所有常量类中的CONTENT_URI就可以用于内容提供者的fangw
        ContentResolver resolver=this.getContentResolver();

        //动态权限检查
        if (Build.VERSION.SDK_INT >=16) {

            int state = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
            if (state== PackageManager.PERMISSION_GRANTED) {

                getCallLogs();

            }else {
                //申请权限
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_CALL_LOG},
                        998);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==998) {
            //参数2 每一个元素代表一个权限，对于参数3中的权限设置
            if (permissions[0].equals(Manifest.permission.READ_CALL_LOG)) {
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    //获取通话记录
                    getCallLogs();
                }
            }
        }
    }

    private void getCallLogs() {
        ContentResolver resolver=this.getContentResolver();
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        Cursor cursor = resolver.query(
                CallLog.Calls.CONTENT_URI,
                null,//需要返回的列
                null,//where语句
                null,//条件参数
                null
        );
        if (cursor != null) {
            while (cursor.moveToNext()){
                int index= cursor.getColumnIndex(CallLog.Calls.NUMBER);
                if (index!=-1) {
                    cursor.getString(index);
                }
            }
            cursor.close();
        }
    }
}
