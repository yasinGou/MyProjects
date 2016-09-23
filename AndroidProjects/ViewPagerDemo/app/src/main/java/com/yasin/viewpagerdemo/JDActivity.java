package com.yasin.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.yasin.viewpagerdemo.adapters.CommonFragmentPageAdapter;
import com.yasin.viewpagerdemo.fragments.FirstFragment;
import com.yasin.viewpagerdemo.fragments.FourthFragment;
import com.yasin.viewpagerdemo.fragments.SecondFragment;
import com.yasin.viewpagerdemo.fragments.ThirdFragment;

import java.util.ArrayList;

public class JDActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private RadioGroup tabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd);
         viewPager = (ViewPager) findViewById(R.id.jd_pager);
        tabBar = (RadioGroup) findViewById(R.id.pager_tab_bar);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
        fragmentList.add(new FourthFragment());

        //FragmentManager： 如果在Activity中，必须使用 getSupportFragmentManager（）
        //                 如果是在Fragment中，必须使用getChildFragmentManager()

        FragmentManager manager = getSupportFragmentManager();
        CommonFragmentPageAdapter adapter = new CommonFragmentPageAdapter(manager, fragmentList);
        viewPager.setAdapter(adapter);

        tabBar.setOnCheckedChangeListener(this );
        viewPager.addOnPageChangeListener(this);
    }

    /**
     * 当页面在持续滚动的时候，会自动回调
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    /**
     * 页面滚动完成后
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
