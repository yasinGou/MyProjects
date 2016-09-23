package com.yasin.fragmentdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yasin.fragmentdemo.R;
import java.util.Calendar;
import java.util.Date;

/**
 * Project: com.yasin.fragmentdemo.fragment
 * Created by Yasin
 * Date: 2016-09-13.
 */

/**
 * Fragment 的创建步骤
 * 1.继承 v4包的Fragment
 * 2.必须有且只有一个无参的构造方法
 * 3.如果Fragment需要显示界面，需要重写onCreatView 方法
 * 4.指定布局资源
 */
public class TimeFragment extends Fragment implements Runnable {
    private TextView txt_date;
    private TextView txt_time;
    private boolean running;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 998:
                    txt_date.setText(((String) msg.obj));
                    break;
                case 999:
                    txt_time.setText(((String) msg.obj));
                    break;
            }

        }
    };

    public TimeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置控件的内容，开启线程，连接网络
        View ret = inflater.inflate(R.layout.time_fragment, container, false);
        txt_time = (TextView) ret.findViewById(R.id.txt_time);
        txt_date = (TextView) ret.findViewById(R.id.txt_date);
        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        txt_date.setText(format.format(date));
//
//        format.applyPattern("HH:mm:ss");
//        txt_time.setText(format.format(date));


        //启动与一个线程
        Thread thread = new Thread(this);
        thread.start();
        return ret;
    }

    @Override
    public void run() {
        running=true;
        try {
            Calendar calendar = Calendar.getInstance();
            StringBuilder builder = new StringBuilder();
            while (running) {
                Thread.sleep(1000);
                calendar.setTimeInMillis(System.currentTimeMillis());
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                builder.append(year).append("-").append(month+1).append("-").append(date);

               Message message = Message.obtain(handler, 998, builder.toString());
               message.sendToTarget();
                //不允许修改主线程的控件
                //清空builder
                builder.setLength(0);
                builder.append(hour).append(":").append(minute).append(":").append(second);
                message=Message.obtain(handler,999,builder.toString());
                message.sendToTarget();
                builder.setLength(0);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        running=false;
   //     handler=null;
   //     handler.removeCallbacksAndMessages(null);
    }
}
