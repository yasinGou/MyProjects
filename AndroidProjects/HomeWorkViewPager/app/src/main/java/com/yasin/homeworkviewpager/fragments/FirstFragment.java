package com.yasin.homeworkviewpager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.yasin.homeworkviewpager.R;
import com.yasin.homeworkviewpager.adapters.CommonFragmentAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private RadioGroup group;

    public FirstFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_first, container, false);
        viewPager = (ViewPager) ret.findViewById(R.id.first_pager);
        fragments = new ArrayList<>();
        fragments.add(new CommonFragment());
        fragments.add(new CommonFragment());
        fragments.add(new CommonFragment());
        fragments.add(new CommonFragment());
        fragments.add(new CircleFragment());
        FragmentManager manager = getChildFragmentManager();
        CommonFragmentAdapter adapter = new CommonFragmentAdapter(manager, fragments);
        viewPager.setAdapter(adapter);
        group = (RadioGroup) ret.findViewById(R.id.first_tab);
        group.setOnCheckedChangeListener(this);
        group.check(R.id.recommend);
        viewPager.addOnPageChangeListener(this);
        return ret;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Bundle bundle = new Bundle();
        switch (checkedId) {
            case R.id.recommend:
                viewPager.setCurrentItem(0);
                bundle.putString("message", "recommend");
                fragments.get(1).setArguments(bundle);
                break;
            case R.id.video:
                viewPager.setCurrentItem(1);
                bundle.putString("message", "recommend");
                fragments.get(2).setArguments(bundle);
                break;
            case R.id.duanzi:
                viewPager.setCurrentItem(2);
                bundle.putString("message", "recommend");
                fragments.get(3).setArguments(bundle);
                break;
            case R.id.picture:
                viewPager.setCurrentItem(3);
//                bundle.putString("message","recommend");
//                fragments.get(0).setArguments(bundle);
                break;
            case R.id.circle:
                viewPager.setCurrentItem(4);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                group.check(R.id.recommend);
                break;
            case 1:
                group.check(R.id.video);
                break;
            case 2:
                group.check(R.id.duanzi);
                break;
            case 3:
                group.check(R.id.picture);
                break;
            case 4:
                group.check(R.id.circle);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
