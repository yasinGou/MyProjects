package com.yasin.loaderdemo.model;

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Project: com.yasin.loaderdemo.model
 * Created by Administrator
 * Date: 2016-09-10.
 */
public class Duanzi {
    private long id;
    private String text;
    private String userName;
    private String url;


    public Duanzi() {

    }

    public static Duanzi createFromJson(JSONObject json) throws JSONException {
        Duanzi ret = new Duanzi();
        if (json != null) {
            JSONObject group = json.getJSONObject("group");
            ret.id = group.getLong("id");
            ret.text = group.getString("text");
            JSONObject user = group.getJSONObject("user");
            ret.userName = user.getString("name");
            JSONObject large_image = group.getJSONObject("large_image");
            JSONArray url_list = large_image.getJSONArray("url_list");
            JSONObject url_json = url_list.getJSONObject(0);
            ret.url=url_json.getString("url");
        }
        return ret;
    }

    public static Duanzi createFromCursor(Cursor cursor) {
        Duanzi ret = new Duanzi();
        int index = cursor.getColumnIndex("_id");
        ret.id = cursor.getLong(index);
        index = cursor.getColumnIndex("username");
        ret.userName = cursor.getString(index);
        index = cursor.getColumnIndex("content");
        ret.text = cursor.getString(index);

        return ret;
    }

    public ContentValues generateContentValues() {
        ContentValues ret = new ContentValues();
        ret.put("_id", id);
        ret.put("username", userName);
        ret.put("content", text);
        return ret;

    }
    public String getUrl() {
        return url;
    }


    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }
}
