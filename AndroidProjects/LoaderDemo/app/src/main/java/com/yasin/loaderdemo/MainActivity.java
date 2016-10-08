package com.yasin.loaderdemo;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity.onCreate");
        ListView listView = (ListView) findViewById(R.id.main_list);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        listView.setAdapter(adapter);

        LoaderManager manager = getSupportLoaderManager();
         //初始化Loader，如果已经存在，就不创建
        //参数1:将要创建的Loader的id
        //参数2:Loader 在创建的时候，传递的参数
        //参数3：回调接口

        manager.initLoader(998,null,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity.onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity.onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity.onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("MainActivity.onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity.onDestroy");
    }

    /**
     * 创建特定的Loader
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> ret=null;
        System.out.println("MainActivity.onCreateLoader");
        switch (id) {
            case 998:
                //创建CursorLoader，直接自动加载内容提供者的数据asasy
               ret= new CursorLoader(
                        this,
                        ContactsContract.Contacts.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );
                break;
        }
        return ret;
    }

    /**
     * Loader数据加载完成后
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //刷新数据的部分
        System.out.println("MainActivity.onLoadFinished");
        adapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //释放关闭Cursor
        int id = loader.getId();
        switch (id) {
            case 998:
                adapter.changeCursor(null);
                break;
        }

    }
}
