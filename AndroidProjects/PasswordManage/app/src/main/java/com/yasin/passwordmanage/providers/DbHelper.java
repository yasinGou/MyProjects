package com.yasin.passwordmanage.providers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project: com.yasin.passwordmanage.providers
 * Created by Administrator
 * Date: 2016-09-09.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE="create table password (" +
            "_id integer primary key autoincrement," +
            "title text ," +
            "website text, " +
            "user text, " +
            "password text ," +
            "note text )";

    public DbHelper(Context context){
        super(context,"appData",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
