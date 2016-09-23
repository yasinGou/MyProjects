package com.yasin.baseadapter;

import com.google.gson.annotations.SerializedName;

/**
 * Project: com.yasin.baseadapter
 * Created by Administrator
 * Date: 2016-09-02.
 */
public class Entry {
    @SerializedName("title")
    private String text;
    @SerializedName("img")
    private String imageUrl;

    public Entry(String imageUrl, String text) {
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public String getImageUrl() {
        return "http://tnfs.tngou.net/image"+imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
