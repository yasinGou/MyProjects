package com.yasin.customprovider.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MoneyProvider extends ContentProvider {
    private DbHelper mDbHelper;
    public static final int CODE_COST = 1;
    public static final int CODE_INCOME = 2;
    public static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(0);
        //添加Urid的匹配规则，实现uri 对应判断哪个表的操作
        uriMatcher.addURI("*", "/cost", CODE_COST);
        uriMatcher.addURI("*", "/income", CODE_INCOME);
    }

    public MoneyProvider() {

    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        //必须返回true
        return true;
    }

    /**
     * 添加方法 ，对外提供接口，可以让其他应用程序向当前程序的数据库添加内容
     *
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri ret = null;
//        uri.getPath();
//        uri.getLastPathSegment();
        //所有的数据库打开操作，都应该咋我增删改查的方法中完成，不允许在onCreat方法中完成
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case CODE_COST:
                long id = database.insert("cost", null, values);

                //数据库添加完成后返回的id必须和uri参数拼接在一起，再返回

                ret = ContentUris.withAppendedId(uri, id);

                break;
            case CODE_INCOME:
                break;
        }

        return ret;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int ret = 0;
//        uri.getPath();
//        uri.getLastPathSegment();
        //所有的数据库打开操作，都应该咋我增删改查的方法中完成，不允许在onCreat方法中完成
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case CODE_COST:

                ret = database.delete("cost", null, null);
                break;
            case CODE_INCOME:
                break;
        }

        return ret;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor ret = null;


        return ret;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {

        return null;
    }
}
