package com.yasin.loaderdemo.provides;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.yasin.loaderdemo.database.DbHelper;

public class MyContentProvider extends ContentProvider {
    public DbHelper helper;
    public static final UriMatcher urimatcher;

    public static final int CODE_DUANZI = 1;

    static {
        urimatcher=new UriMatcher(0);
        urimatcher.addURI("*","/duanzi", CODE_DUANZI);

//        urimatcher.addURI("*","duanzi/limit/#",2);
//
//
//        urimatcher.addURI("*","duazi/#",3);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri ret=null;
        int code = urimatcher.match(uri);
        SQLiteDatabase database = helper.getWritableDatabase();
        switch (code) {
            case CODE_DUANZI:
                long id = database.insert("duanzi", null, values);
                ret= ContentUris.withAppendedId(uri,id);
                break;
        }
        database.close();
        return ret;

    }

    @Override
    public boolean onCreate() {
        helper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor ret=null;
        int code = urimatcher.match(uri);
        SQLiteDatabase database = helper.getWritableDatabase();
        switch (code) {
            case CODE_DUANZI:
                ret=database.query(
                        "duanzi",
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
        }
        return ret;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
