package mobi.vhly.messengerdemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessageService extends Service {

    /**
     * 用于接收其他应用程序传递过来的消息,
     * 信使可以封装Handler, 封装之后就可以接收数据
     */
    private Handler mReceiveHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 998: // 打印数据
                    // !!! 如果Handler用于信使的消息接收,
                    //     那么 obj 对象必须是 Parcelable对象 !!!
                    Object obj = msg.obj;
                    Log.d("Service", "obj = " + obj);

                    if (obj instanceof Bundle) {
                        Bundle bundle = (Bundle) obj;
                        int id = bundle.getInt("id");
                        Log.d("Service", "id = " + id);
                    }

                    Messenger replyTo = msg.replyTo;
                    if (replyTo != null) {
                        // 消息处理完成,需要返回结果和状态
                        Message message = Message.obtain();
                        message.what = 777;
                        try {
                            replyTo.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }
        }
    };

    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 使用信使 Messenger 来返回就可以了
        return new Messenger(mReceiveHandler).getBinder();
    }
}
