package com.yasin.chatactivity;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.yasin.chatactivity.adapters.MessageAdapter;
import com.yasin.chatactivity.model.ChatMessage;
import com.yasin.yasinlibray.HttpTool_Post;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable {
    private Thread thread;
    private Handler chatHandler;
    public static final int CODE_SEND=998;
    public static final int CODE_RECIEVE=999;
    private List<ChatMessage> messageList;
    private EditText textMessage;
    private MessageAdapter adapter;
    private ListView listView;
    private Handler receiveHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_RECIEVE:
                    String text = (String) msg.obj;
                    if (text != null) {
                        ChatMessage message = new ChatMessage();
                        message.setName("机器人");
                        message.setMessage(text);
                        message.isReceive(true);
                        messageList.add(message);
                        adapter.notifyDataSetChanged();
                        int count = adapter.getCount();
                        listView.setSelection(count-1);

                    }
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMessage = (EditText) findViewById(R.id.messsage_txt);
        listView = (ListView) findViewById(R.id.listview);
        messageList=new ArrayList<>();
        adapter = new MessageAdapter(this,messageList);
        listView.setAdapter(adapter);
        thread = new Thread(this);
        thread.start();
    }

    public void btnSend(View view) {
        if (chatHandler != null) {
            Message message = chatHandler.obtainMessage(998);
            String text = textMessage.getText().toString();
            message.obj= text;
            chatHandler.sendMessage(message);
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setName("我");
            chatMessage.isReceive(false);
            chatMessage.setMessage(text);
            messageList.add(chatMessage);
            adapter.notifyDataSetChanged();
        }
        textMessage.setText("");
        textMessage.clearComposingText();
    }

    @Override
    public void run() {
        //子线程使用Handler接收消息的步骤
        //1.调用Looper.prepare() 创建子线程的Looper对象
        //2.在Looper创建好之后，调用Handler的构造方法
        //3.调用Looper.loop();
        Looper.prepare();
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
              //  super.handleMessage(msg);
                switch (msg.what) {
                    case CODE_SEND:
                        //发送数据
                        JSONObject object = new JSONObject();
                        try {
                            object.put("key","92d71b06fbb3477ab3d844963a53ff8a");
                            object.put("info",msg.obj.toString());
                            object.put("loc","北京市海淀区");
                            object.put("userid", Build.SERIAL);
                           byte[] data=HttpTool_Post.doPostJson("http://www.tuling123.com/openapi/api",object.toString());
                            if (data != null) {
                                String jsonString = new String(data, "utf-8");
                                JSONObject jsonObject = new JSONObject(jsonString);
                                int code = jsonObject.getInt("code");
                                if (code==100000) {
                                    String text = jsonObject.getString("text");
                                    Message message = receiveHandler.obtainMessage(CODE_RECIEVE);
                                    message.obj=text;
                                    message.sendToTarget();
                                    receiveHandler.sendMessage(message);
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
        chatHandler = handler;
        Looper.loop();



    }
}
