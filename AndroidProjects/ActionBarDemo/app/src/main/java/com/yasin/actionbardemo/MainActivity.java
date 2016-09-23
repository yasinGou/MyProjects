package com.yasin.actionbardemo;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, ActionBar.OnNavigationListener {
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar 的基本使用

        //1、获取ActionBar ，可能为空
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //2.设置
            //设置后退,当后退箭头被点击，会调用菜单的点击方法 OptionsSelectedItem
            //id=android.R.id .home
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置后退图标
            // actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            //不显示标题
            // actionBar.setDisplayShowTitleEnabled(false);


            //导航模式   Tab模式
            //1.设置模式
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            //2.创建Tab  ,设置监听  ，添加Tab
            ActionBar.Tab tab = actionBar.newTab();
            tab.setTabListener(this);
            tab.setText("首页");
            actionBar.addTab(tab);

            ActionBar.Tab tab1 = actionBar.newTab();
            tab1.setTabListener(this);
            tab1.setText("发现");
            actionBar.addTab(tab1);

            ActionBar.Tab tab2 = actionBar.newTab();
            tab2.setTabListener(this);
            tab2.setText("我");
            actionBar.addTab(tab2);


            //ActionBar导航下拉列表
            //1.设置导航模式为列表
            //2.设置下拉列表导航的Adapter
            //3.列表选中接口回调


//                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//                ArrayList<String> data = new ArrayList<>();
//                data.add("option");
//                data.add("day");
//                data.add("week");
//                data.add("month");
//                data.add("year");
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        this,
//                        android.R.layout.simple_spinner_dropdown_item,
//                        data
//                );
//                actionBar.setListNavigationCallbacks(adapter, this);
            }


        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //FragmentTransaction 不允许 调用commit方法提交事务
        int position = tab.getPosition();
        Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //FragmentTransaction 不允许 调用commit方法提交事务

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //FragmentTransaction 不允许 调用commit方法提交事务

    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Toast.makeText(MainActivity.this, ""+itemPosition, Toast.LENGTH_SHORT).show();
        return true;
    }
}
