package com.yasin.homeworkviewpager.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Project: com.yasin.homeworkviewpager.adapters
 * Created by Yasin
 * Date: 2016-09-17.
 */
public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public CommonFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment ret=null;
        if (fragmentList != null) {
           ret=fragmentList.get(position);
        }
        return ret;
    }

    @Override
    public int getCount() {
        int ret=0;
        if (fragmentList != null) {
           ret=fragmentList.size();
        }
        return ret;
    }
}
