package com.yasin.fragmentdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.yasin.fragmentdemo.fragment.DmFragment;
import com.yasin.fragmentdemo.fragment.HomeFragment;
import com.yasin.fragmentdemo.fragment.PersonalFragment;
import com.yasin.fragmentdemo.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private HomeFragment homeFragment;
    private DmFragment dmFragment;
    private SearchFragment searchFragment;
    private PersonalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity.onCreate");
        RadioGroup group = (RadioGroup) findViewById(R.id.main_tab_bar);
        if (group != null) {
            group.setOnCheckedChangeListener(this);


        }
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("home");

        //创建所有的Fragment对象，便于切换
        if (fragment != null) {
            homeFragment= ((HomeFragment) fragment);
        }else {
            homeFragment = new HomeFragment();
        }
        fragment = manager.findFragmentByTag("dm");
        if (fragment != null) {
            dmFragment= ((DmFragment) fragment);
        }else {
            dmFragment = new DmFragment();
        }
        fragment = manager.findFragmentByTag("search");
        if (fragment != null) {
            searchFragment= ((SearchFragment) fragment);
        }else{
            searchFragment = new SearchFragment();

        }
        fragment = manager.findFragmentByTag("personal");
        if (fragment != null) {
            personalFragment=((PersonalFragment) fragment);
        }else {

            personalFragment = new PersonalFragment();

        }
        group.check(R.id.main_tab_item_home);


        //每一个Fragment 都可以通过两种方式添加到Activity
        //1.使用<fragment>标签直接添加
        //2.使用代码添加Fragment，使用FragmentManager ，FragmentTransaction
//
//        FragmentManager manager = getSupportFragmentManager();
//
//        //内部添加，删除 替换Fragment，必须使用FragmentManager.beginTransaction
//        FragmentTransaction tx = manager.beginTransaction();
//        //事务每次操作之前必须重新开启，不允许使用成员变量的方式
//
//        tx.add(R.id.fragment_container, homeFragment);
//
//        tx.commit();


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
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity.onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("MainActivity.onRestart");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("MainActivity.onConfigurationChanged");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        switch (checkedId) {
            case R.id.main_tab_item_home:
                //如果之前有Fragment 删除之后再添加，如果没有直接添加
             //   tx.replace(R.id.fragment_container,homeFragment);
                tx.replace(R.id.fragment_container,homeFragment,"home");
                break;
            case R.id.main_tab_item_dm:
                tx.replace(R.id.fragment_container,dmFragment,"dm");
                break;
            case R.id.main_tab_item_search:
                tx.replace(R.id.fragment_container,searchFragment,"search");
                break;
            case R.id.main_tab_item_personal:
                tx.replace(R.id.fragment_container,personalFragment,"personal");
                break;
        }
        tx.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
