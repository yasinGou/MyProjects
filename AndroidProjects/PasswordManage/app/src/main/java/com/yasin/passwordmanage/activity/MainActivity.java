package com.yasin.passwordmanage.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yasin.passwordmanage.R;
import com.yasin.passwordmanage.adapter.MyAdapter;
import com.yasin.passwordmanage.database.DataContracs;
import com.yasin.passwordmanage.database.DatabaseTools;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private long time;
    private MyAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ContentValues values = new ContentValues();
//        values.put(DataContracs.Password.TITLE,"我的QQ密码");
//        values.put(DataContracs.Password.WEBSITE,"http://www.qq.com/");
//        values.put(DataContracs.Password.USER,"gouyazhou");
//        values.put(DataContracs.Password.PASSWOERD,"123456");
//        values.put(DataContracs.Password.NOTE,"");
//        tools.insertData(values);
//        DatabaseTools tools = DatabaseTools.getInstance(this);
//        Cursor cursor = tools.queryAllData();

        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://yasin.passwordmanage.providers.PassContentProvider/password");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        listView = (ListView) findViewById(R.id.main_list);
       // SimpleCursorAdapter ad = new SimpleCursorAdapter(this, R.layout.item, cursor, new String[]{DataContracs.Password.TITLE, DataContracs.Password.USER}, new int[]{R.id.title, R.id.user}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.adapter = new MyAdapter(this, cursor);
        listView.setAdapter(this.adapter);
       // requery();
        listView.setOnItemClickListener(this);
        listView.setOnCreateContextMenuListener(this);


    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        ContentResolver resolver = getContentResolver();
//        Uri uri = Uri.parse("content://yasin.passwordmanage.providers.PassContentProvider/password");
//        Cursor cursor = resolver.query(uri, null, null, null, null);
////        DatabaseTools tools = DatabaseTools.getInstance(this);
////        Cursor cursor = tools.queryAllData();
//        adapter = new MyAdapter(this, cursor);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
//        listView.setOnCreateContextMenuListener(this);
//    }

    private void requery(){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://abc/password");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        adapter.changeCursor(cursor);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("recordId", id);
        startActivity(intent);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actions_del:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                if (menuInfo != null) {
                    long id = menuInfo.id;
                    DatabaseTools.getInstance(this).deleteData(id);
                    Cursor cursor = DatabaseTools.getInstance(this).queryAllData();
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    adapter.changeCursor(cursor);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actions_add:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("add", "addData");
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (SystemClock.uptimeMillis() - time < 3000) {
            ActivityCompat.finishAffinity(this);
        } else {
            Toast.makeText(MainActivity.this, "请再按一次退出", Toast.LENGTH_SHORT).show();
            time = SystemClock.uptimeMillis();
        }
    }
}
