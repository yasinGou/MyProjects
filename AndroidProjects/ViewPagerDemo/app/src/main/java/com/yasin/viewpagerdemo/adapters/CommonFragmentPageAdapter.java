package com.yasin.viewpagerdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Project: com.yasin.viewpagerdemo.adapters
 * Created by Yasin
 * Date: 2016-09-14.
 */
public class CommonFragmentPageAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;

    public CommonFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * 返回指定的Fragment， 用于显示
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * 返回ViewPager 的Fragment的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        int ret = 0;
        if (fragments != null) {
            ret = fragments.size();
        }
        return ret;
    }
}
