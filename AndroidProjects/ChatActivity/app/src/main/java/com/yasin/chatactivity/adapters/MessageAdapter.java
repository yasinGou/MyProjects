package com.yasin.chatactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yasin.chatactivity.R;
import com.yasin.chatactivity.model.ChatMessage;

import java.util.List;

/**
 * Project: com.yasin.chatactivity.adapters
 * Created by Yasin
 * Date: 2016-09-18.
 */
public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List<ChatMessage> list;

    public MessageAdapter(Context context, List<ChatMessage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        boolean receive = list.get(position).isReceive();
        if (receive) {
            //接收
            return 0;
        } else {
            //发送
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                ret = getLeftView(position, convertView, parent);
                break;
            case 1:
                ret = getRightView(position, convertView, parent);
                break;
        }
        return ret;
    }

    private View getRightView(int position, View view, ViewGroup parent) {
        View ret = null;
        if (view==null) {
            ret= LayoutInflater.from(context).inflate(R.layout.chat_message_right,parent,false);
        }else {
            ret=view;
        }
        MessageTextHolder holder = (MessageTextHolder) ret.getTag();
        if (holder == null) {
             holder = new MessageTextHolder(ret);
            ret.setTag(holder);
        }
        holder.BindView(list.get(position));
        return ret;

    }

    private View getLeftView(int position, View view, ViewGroup parent) {
        View ret = null;
        if (view==null) {
            ret= LayoutInflater.from(context).inflate(R.layout.chat_message_left,parent,false);
        }else {
            ret=view;
        }
        MessageTextHolder holder = (MessageTextHolder) ret.getTag();
        if (holder == null) {
            holder = new MessageTextHolder(ret);
            ret.setTag(holder);
        }
        holder.BindView(list.get(position));
        return ret;
    }


    private static class MessageTextHolder {

        private final TextView userName;
        private final TextView message;

        public MessageTextHolder(View itemView) {
            userName = ((TextView) itemView.findViewById(R.id.user_name));
            message = ((TextView) itemView.findViewById(R.id.message));
        }

        public void BindView(ChatMessage chatMessage) {
            userName.setText(chatMessage.getName());
            message.setText(chatMessage.getMessage());
        }
    }
}
