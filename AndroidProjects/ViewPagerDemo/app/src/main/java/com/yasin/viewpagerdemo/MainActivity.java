package com.yasin.viewpagerdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yasin.viewpagerdemo.adapters.PicturePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);

        List<Integer> imageIds = new ArrayList<>();
        imageIds.add(R.mipmap.ic_launcher);
        imageIds.add(android.R.drawable.ic_delete);

        PicturePageAdapter adapter = new PicturePageAdapter(this,imageIds);
        //设置预加载的page
        //当前索引页面，  左侧保留两个页面，右侧预加载两个页面
        viewPager.setOffscreenPageLimit(2);

        viewPager.setAdapter(adapter);
    }
}
