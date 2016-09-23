package com.yasin.day27menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    private List<String> mStrings;
    private ArrayAdapter<String> mAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        mStrings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mStrings.add("android" + i);

        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);

        ListView listview = (ListView) findViewById(R.id.main_listview);
        if (listview != null) {
            listview.setAdapter(mAdapter);
            //设置上下文菜单创建的监听器，内部需要上下文时，自动创建
            listview.setOnCreateContextMenuListener(this);
            //android 6.0 以上使用
//            if (Build.VERSION.SDK_INT >= 23) {
//                listview.setOnContextClickListener();
//            }
        }
    }

    /**
     * 创建上下文菜单，每次显示时，这个方法都会被调用
     *
     * @param menu
     * @param v        需要显示上下文菜单的控件
     * @param menuInfo
     */
    //上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.help_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actions_copy:
                //上下文菜单的附加信息，只有在listview GridView中这个变量才有用
                ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
                if (menuInfo != null) {
                    AdapterView.AdapterContextMenuInfo adapterMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    int position = adapterMenuInfo.position;
                    Toast.makeText(HelpActivity.this, mStrings.get(position), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

}
