package com.yasin.day29externalfile;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testSdcard();
    }
    public void testSdcard(){
        //监测手机是否有存储卡
        //所有外部存储需要在清单文件中声明权限

        //获取存储卡的状态
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //外部存储已经挂载，可以访问和使用
            //获取外部存储的根目录
            File directory = Environment.getExternalStorageDirectory();
            Log.d(TAG, "testSdcard: 外部存储目录："+directory.getAbsolutePath());
            //获取外部存储，公共目录
            File dcmDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            if (dcmDir.exists()) {
                File[] files = dcmDir.listFiles();
                for (File file : files) {

                }
            }
            //外部存储可以获取应用程序特定的目录
            //  /外部根目录/Android/data/包名/
            //使用上下文Context来获取

            //获取外部缓存目录
            getExternalCacheDir();
            //参数为null  返回files
            // 否则 返回 files/xxx目录
            getExternalFilesDir(null);

            //getExternalFilesDir::/storage/emulated/0/Android/data/com.yasin.contactbackups/files
            // getFilesDir::/data/user/0/com.yasin.contactbackups/files
            // getDataDirectory:: /data


        }
    }
}
