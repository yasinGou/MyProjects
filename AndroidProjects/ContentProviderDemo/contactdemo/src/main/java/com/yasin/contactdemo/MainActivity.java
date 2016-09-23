package com.yasin.contactdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yasin.contactdemo.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.cotact_list);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null
        );
//        adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, new int[]{R.id.item_txt},
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        adapter = new MyAdapter(this, cursor);
        adapter.setOnClickListener(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void init() {
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null
        );
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                if (index != -1) {
                    String contactName = cursor.getString(index);
                    Log.d("Contact", "name=  " + contactName);
                }
            }
            cursor.close();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentResolver resolver = getContentResolver();
        //获取一个联系人的所有手机号，使用phone类
        Cursor cursor = resolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                new String[]{Long.toString(id)},
                null
        );
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                if (index != -1) {
                    String phoneNum = cursor.getString(index);
                    Toast.makeText(MainActivity.this, "PhoneNum:" + phoneNum, Toast.LENGTH_SHORT).show();
                }
            }
            cursor.close();
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        boolean b = false;
        ContentResolver resolver = this.getContentResolver();
        //删除联系人，必须使用RawContacts
        int num = resolver.delete(
                ContactsContract.RawContacts.CONTENT_URI,
                ContactsContract.RawContacts.CONTACT_ID + "=?",
                new String[]{Long.toString(id)}
        );
        if (num > 0) {
            requeryCursor();
            adapter.notifyDataSetChanged();
        }

        return b;
    }

    public void requeryCursor() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        adapter.changeCursor(cursor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_del:
                Object tag = v.getTag();
                Long id = (Long) tag;
                Toast.makeText(MainActivity.this, "删除成功" + id, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
