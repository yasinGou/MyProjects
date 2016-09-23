package com.yasin.day30moneyusage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yasin.day30moneyusage.database.DbHelper;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private SQLiteDatabase database;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper dbHelper = new DbHelper(this);
        //打开数据库，如果不存在就自动创建
        database = dbHelper.getWritableDatabase();
        // 参数2:获取列。null代表所有
        //参数3 selection  "money>?"    where语句条件部分，null代表无条件，获取所有记录
        //参数4 selectionArgs new String[]{"30"}
        //   database.query("cost",null,"type=?",new String[]{"1"},null,null,"money desc");
        Cursor cursor = database.query("cost", null, null, null, null, null, "money desc");

        if (cursor != null) {

//            while (cursor.moveToNext()){
//                //获取列名所对应的索引
//                int index = cursor.getColumnIndex("money");
//                if (index!=-1) {
//
//                    double money = cursor.getDouble(index);
//                    Log.d("SQLiteTest", "money="+money);
//                }
//            }
//
//
//            cursor.close();
            ListView listView = (ListView) findViewById(R.id.cost_list);
            adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"money"},
                    new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(this);
            listView.setOnItemClickListener(this);
        }

    }

    public void btnInsetData(View view) {

        ContentValues values = new ContentValues();
        values.put("money", 30.0);
        values.put("use_time", System.currentTimeMillis());
        values.put("type", 1);
        //如果values为空，参数2必须为一个列名，这个列可以为null
        long rowId = database.insert("cost", null, values);
        Toast.makeText(MainActivity.this, "添加记录" + rowId, Toast.LENGTH_SHORT).show();

        reQueryCursor();

    }

    @Override
    protected void onDestroy() {
        //释放adapter中占用的cursor，直接关闭
        adapter.changeCursor(null);
        database.close();
        database = null;
        super.onDestroy();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //返回删除的记录数
        int delNum=database.delete("cost","_id=?",new String[]{Long.toString(id)});
        reQueryCursor();
        return false;
    }

    private void reQueryCursor() {
        Cursor cursor = database.query("cost", null, null, null, null, null, "money desc");
        //切换cursor  会关闭旧的cursor
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("recordId",id);
        startActivity(intent);
    }
}
