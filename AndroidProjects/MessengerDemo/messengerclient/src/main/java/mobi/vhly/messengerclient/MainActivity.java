package mobi.vhly.messengerclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    // 发送消息用的
    private Messenger mSendMessenger;

    private Handler mReceiveHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            Toast.makeText(MainActivity.this, "结果: " + what, Toast.LENGTH_SHORT).show();
        }
    };

    // 等待服务返回数据,接收
    private Messenger mReplyMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReplyMessenger = new Messenger(mReceiveHandler);

        // 绑定远程服务
        Intent intent = new Intent();
        intent.setClassName(
                "mobi.vhly.messengerdemo",  // 程序ID
                "mobi.vhly.messengerdemo.services.MessageService" // 服务类全名
        );
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        // 绑定的服务使用信使来返回 IBinder对象;
        // 接收到IBinder对象,可以使用 Messenger信使封装, 就可以发送消息了
        mSendMessenger = new Messenger(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mSendMessenger = null;
    }

    public void btnSendMessage(View view) {
        if (mSendMessenger != null) {
            Message msg = Message.obtain();
            msg.what = 998;
            msg.arg1 = 999;
            // 使用信使发送的必须是Parcelable接口的对象,不能够是基本数据类型
            Bundle bundle = new Bundle();
            bundle.putInt("id", 6);
            msg.obj = bundle;

            // 消息可以设置 replyTo 成员,实现 A -> B ,B收到之后,通过 replyTo对象,可以
            // 把数据返回给 A
            msg.replyTo = mReplyMessenger; // 用于接收消息

            // 使用信使发送消息
            try {
                mSendMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
