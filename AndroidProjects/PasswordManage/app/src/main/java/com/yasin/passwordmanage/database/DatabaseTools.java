package com.yasin.passwordmanage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Project: com.yasin.passwordmanage.database
 * Created by Administrator
 * Date: 2016-09-08.
 */
public final  class DatabaseTools {
    private Context context;
    private static DbHelper helper;
    private static SQLiteDatabase database;

    private DatabaseTools(Context context) {
        this.context = context;
        helper = new DbHelper(context);
    }

    public static DatabaseTools getInstance(Context context) {
        return new DatabaseTools(context) ;
    }

    public int deleteAll() {
        int delete = database.delete(DataContracs.Password.TABLE_NAME, null, null);
        return delete;
    }

    public long insertData(ContentValues vaules) {
        long rowId;
        database = helper.getWritableDatabase();
        rowId = database.insert(DataContracs.Password.TABLE_NAME, null, vaules);
        return rowId;
    }

    public int deleteData(Long  id) {
        int returnNum;
        database = helper.getWritableDatabase();
        returnNum = database.delete(DataContracs.Password.TABLE_NAME, "_id=?", new String[]{Long.toString(id)});
        return returnNum;
    }

    public int updateData(Long id, ContentValues values) {
        int returnNums;
        database = helper.getWritableDatabase();
        returnNums = database.update(DataContracs.Password.TABLE_NAME, values, "_id=?", new String[]{Long .toString(id)});
        return returnNums;
    }

    public Cursor queryData(Long  id) {
        Cursor cursor;
        database = helper.getWritableDatabase();
        cursor = database.query(DataContracs.Password.TABLE_NAME, null, "_id=?", new String[]{Long.toString(id)}, null, null, null);
        return cursor;
    }

    public Cursor queryAllData() {
        Cursor cursor;
        database = helper.getWritableDatabase();
        cursor = database.query(DataContracs.Password.TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }


}
