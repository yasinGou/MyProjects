package com.yasin.notificationdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnNormalNotify(View view) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        Notification notification = builder.setContentTitle("通知").setContentText("内容").setSmallIcon(R.mipmap.ic_launcher).build();
        builder.setDefaults(Notification.DEFAULT_ALL);
        //builder.setLights()
        //声音可以自定义
        //Uri.fromFile()
//        builder.setSound();

        //设置通知的点击
        //pendingIntent 可以包裹Intent，指定的是xxx.class ,action
        Intent intent = new Intent(this, Main2Activity.class);
        //使用通知的PendingIntent 时，必须要设置Intent的Flag，针对于Activity.

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("url","http://www.baidu.com");

        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pi);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(998,notification);
    }

    public void btnActionNotify(View view) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("通知").setContentText("内容").setSmallIcon(R.mipmap.ic_launcher).build();

        builder.setDefaults(Notification.DEFAULT_ALL);
        //builder.setLights()
        //声音可以自定义
        //Uri.fromFile()
//        builder.setSound();

        //设置通知的点击
        //pendingIntent 可以包裹Intent，指定的是xxx.class ,action
        Intent intent = new Intent(this, Main2Activity.class);
        //使用通知的PendingIntent 时，必须要设置Intent的Flag，针对于Activity.

      //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("url","http://www.baidu.com");
        Intent intent1 = new Intent("yasin.ACTION_TEST");
        intent1.setClassName(this,MyReceiver.class.getName());
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.addAction(android.R.drawable.ic_media_play,"播放",pi);


        Notification notification = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(998,notification);

    }
}
