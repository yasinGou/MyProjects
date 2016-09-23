package com.yasin.passwordmanage.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PassContentProvider extends ContentProvider {
    public PassContentProvider() {
    }

    private DbHelper helper;
    public static final int COED_PASS = 1;

    public static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(0);
        uriMatcher.addURI("*", "/password", COED_PASS);
    }

    @Override
    public boolean onCreate() {
        helper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri ret = null;
        SQLiteDatabase database = helper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case COED_PASS:
                long id = database.insert("password", null, values);
                ret = ContentUris.withAppendedId(uri,id);
                break;
        }
        return ret;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int ret = 0;
        SQLiteDatabase database = helper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case COED_PASS:
                ret = database.delete("password", selection, selectionArgs);
                break;
        }
        return ret;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor ret = null;
        SQLiteDatabase database = helper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case COED_PASS:
                ret=database.query("password",projection,selection,selectionArgs,null,null,sortOrder);
                break;
        }
        return ret;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int ret = 0;
        SQLiteDatabase database = helper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case COED_PASS:
                ret=database.update("password",values,selection,selectionArgs);
                break;
        }
        return ret;
    }
}
