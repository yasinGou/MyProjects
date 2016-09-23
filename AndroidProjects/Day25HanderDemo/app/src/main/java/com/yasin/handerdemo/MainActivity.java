package com.yasin.handerdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 生产者和消费者模型
 * //产品  Message
 //仓库  MessageQueue  正常编程中永远用不到
 //循环对象 Looper
 //handler 将产品放入仓库
 //Handler.Callback 静态内部类  回调接口 传递方法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView text;

//  private Handler handler=new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//           //多个message的时候
//            switch (msg.what) {
//                case 0:
//           //         text.setText(((String) msg.obj));
////                    text.setText(Integer.toString(msg.arg1));
////                    msg.arg1++;
////                    if ((msg.arg1<15)) {
////                       handler.sendMessageDelayed(Message.obtain(msg),1000);
////                    }
//                    text.setText(((String) msg.obj));
//                    break;
//            }
//            return true;
//        }
//    });
    @Override
    //onCreate执行完成后，activity才能显示在屏幕上
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.main_text);
        HandlerRunnable runnable = new HandlerRunnable();
        new Thread(runnable).start();
//        Message message = handler.obtainMessage(0, 0, 0);
//        handler.sendMessage(message);

        // text.setText("0");
        // text.setOnClickListener(this);

        //不可以在子线程中操作主线程的控件（绝大多数的控件）
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <100 ; i++) {
////                    Message message=new Message();不推荐使用
//                    //handler使用后将message放入池中，通过此方法，可以将闲置的message拿出来使用
//                    //第一种方式：
////                    Message message = Message.obtain();
////                    message.what=0;
////                    message.obj=Integer.toString(i+1);
////                   //发送消息
////                    handler.sendMessage(message);
////                    Log.d(TAG, "run: "+Thread.currentThread().getName());
////                    //传递的参数
////                    message.arg1=;
////                    message.arg2=;
////                    message.setData();
////                    message.getTarget();
////                    message.getWhen();
//
//
//
//                    //第二种方式
//                    Message message = Message.obtain(handler, 0, Integer.toString(i + 1));
//                    message.sendToTarget();
//                    //第三种方式
//                    Message message1 = handler.obtainMessage(0, 0, 0);
//                    handler.sendMessage(message);
//                    Log.d(TAG, "run: Target  :"+message.getTarget());
//                  //  text.setText(Integer.toString(i+1));
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//        }
//        }).start();
//          handler.post(new Runnable() {
//              @Override
//              public void run() {
//
//              }
//          });
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

    }

    @Override
    public void onClick(View v) {
//        Intent intent=new Intent(this,Main2Activity.class);
//        startActivity(intent);
    }
}
