package com.yasin.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.yasin.servicedemo.R;

public class MusicBindService extends Service {
    private MediaPlayer player;
    public MusicBindService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this, R.raw.nobody);
    }

    /*
        绑定服务的实现流程：
        1.服务onCreate, onBind ,onDestroy方法
        2.onBind方法需要返回一个IBinder 对象，
        3.如果Activity绑定，Activity就可以取到IBinder对象
     */

    //同一个应用内部不同组件绑定，可以使用内部类以及Binder对象返回

    public class MusicController extends Binder{

        public void play(){
            player.start();
        }

        public void pause(){
            player.pause();
        }

        public long getMusicDuration(){
            return player.getDuration();
        }
        public long getposition(){
            return player.getCurrentPosition();
        }
    }

    //R任意一次unbindService()方法都会触发这个方法
    //用于释放一些绑定时使用的资源
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 当绑定服务时，自动回调这个方法，返回的对象可以直接操作Service内部的内容
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {

        return new MusicController();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
        player=null;
    }
}
