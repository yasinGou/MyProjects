package com.yasin.passwordmanage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project: com.yasin.passwordmanage.database
 * Created by Administrator
 * Date: 2016-09-08.
 */
public class DbHelper  extends SQLiteOpenHelper{
    public Context context;
    public static final String CREATE_TABLE="create table codeTable (" +
            "_id integer primary key autoincrement," +
            "title text ," +
            "website text, " +
            "user text, " +
            "password text ," +
            "note text )";
    public DbHelper(Context context){
        super(context,"AppData",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
