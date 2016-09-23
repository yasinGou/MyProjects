package com.yasin.chatactivity.model;

import android.widget.ImageView;

/**
 * Project: com.yasin.chatactivity.adapters.model
 * Created by Yasin
 * Date: 2016-09-18.
 */
public class ChatMessage {
    private String name;
    private String message;
    private long time;
    private boolean isReceive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isReceive() {
        return isReceive;
    }

    public void isReceive(boolean receive) {
        isReceive = receive;
    }
}
