package com.yasin.loaderdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project: com.yasin.loaderdemo.database
 * Created by Administrator
 * Date: 2016-09-10.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String CRETA_TALBE="create table duanzi (" +
            "_id integer primary key," +
            "username text," +
            "content text" +
            ")";

    public DbHelper(Context context){
        super(context,"myapp",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRETA_TALBE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
