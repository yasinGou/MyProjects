package com.yasin.networklibrary;

import com.google.gson.annotations.SerializedName;

/**
 * Project: com.yasin.networklibrary
 * Created by Administrator
 * Date: 2016-08-31.
 */
public class Menu {
    @SerializedName("name")
    private String name;
    @SerializedName("food")
    private String food;
    @SerializedName("message")
    private String message;
    @SerializedName("img")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
