package com.yasin.customprovider.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project: com.yasin.customprovider.providers
 * Created by Administrator
 * Date: 2016-09-09.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String CREAT_TABLE_COST="create table cost(" +
            "_id integer primary autoincrement," +
            "money double not null default 0" +
            ")";
    public DbHelper(Context context){
        super(context,"myApp",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE_COST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
