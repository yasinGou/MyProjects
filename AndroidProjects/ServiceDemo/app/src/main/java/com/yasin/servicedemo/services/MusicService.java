package com.yasin.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yasin.servicedemo.R;

import java.io.IOException;

/**
 * Project: com.yasin.servicedemo.services
 * Created by Yasin
 * Date: 2016-09-21.
 */
public class MusicService extends Service implements MediaPlayer.OnPreparedListener {
    //播放因为，视频
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //必须释放此音乐播放器
        MediaPlayer mediaPlayer = new MediaPlayer();
        //设置远程，文件网址
        try {
            mediaPlayer.setDataSource(this,Uri.parse("http://10.0.153.160:8080/nobody.mp3"));
            //设置准备歌曲资源，开启线程，加载网络数据
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**系统检测到已经有服务了，则不会调用onCreate()方法，只会调用此方法
     * 服务启动后，调用StartService（）时，都会调用这个方法
     * 接收传递的参数
     *
     * @param intent  startService（）中的参数
     * @param flags
     * @param startId
     * @return   int 返回值能够控制服务是否自动复活，在程序意外终止的时候
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //粘性服务，复活时 intent为空
        // 检测Intent是否为空
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        String action = intent.getStringExtra("action");
        if ("play".equals(action)){
            mediaPlayer.start();
        }else if("pause".equals(action)){
            mediaPlayer.pause();
        }

        //START_NOT_STICKY  服务不会被复活
        //START_STICKY  粘性  意外停止后服务会复活  intent会等于空。所有要判断intent是否为空
        //START_REDELIVER_INTENT  意外停止后，服务复活会传递之前的intent
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放各种资源
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer.release();
        mediaPlayer=null;

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
