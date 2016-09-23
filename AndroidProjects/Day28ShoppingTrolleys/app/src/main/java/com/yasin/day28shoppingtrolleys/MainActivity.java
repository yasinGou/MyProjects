package com.yasin.day28shoppingtrolleys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    public MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.shopping));
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(String.format(Locale.CHINA, "item %02d", i));
        }
        adapter = new MyAdapter(this, strings);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actions_edit:
                adapter.setBoxFalse();
                adapter.setShowBox();
                break;
            case R.id.actions_clear:
                adapter.cleanAll();
                adapter.setShowBoxNo();
                break;
            case R.id.actions_add:
                adapter.add("item 添加");
                adapter.setShowBoxNo();
                break;
            case R.id.actions_ok:
                adapter.setShowBoxNo();
                ArrayList<String> listCheck = adapter.getListCheck();
                StringBuilder builder = new StringBuilder();
                if (listCheck != null) {
                    for (String s : listCheck) {
                        builder.append(s + "  ");
                    }
                    Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
                }
                break;


        }
        return true;
    }


}
