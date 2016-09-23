package com.yasin.newstoday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;

import com.yasin.newstoday.fragments.DetailFragment;
import com.yasin.newstoday.fragments.NewListFragment;

public class MainActivity extends AppCompatActivity implements NewListFragment.OnNewSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用FragmentManager来查找相应的Fragment，实现回调接口

    }




    @Override
    public void OnNewsSelected(Bundle bundle) {
        //监测 横屏还是竖屏
        //查找特有的Fragment，来区分
        //如果Fragment存在，并且显示的状态
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_detail);
        if (fragment != null && fragment.isVisible()) {
            DetailFragment detailFragment = (DetailFragment) fragment;
            detailFragment.setMessage(bundle);
        }else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("bundle",bundle);
            startActivity(intent);
        }
    }
}
