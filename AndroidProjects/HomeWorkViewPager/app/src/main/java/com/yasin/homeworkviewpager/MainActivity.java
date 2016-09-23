package com.yasin.homeworkviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.yasin.homeworkviewpager.adapters.CommonFragmentAdapter;
import com.yasin.homeworkviewpager.fragments.AuditingFragment;
import com.yasin.homeworkviewpager.fragments.FindingFragment;
import com.yasin.homeworkviewpager.fragments.FirstFragment;
import com.yasin.homeworkviewpager.fragments.MessageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentList = new ArrayList<>();
        fragmentList.add(new FirstFragment());
        fragmentList.add(new FindingFragment());
        fragmentList.add(new AuditingFragment());
        fragmentList.add(new MessageFragment());
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        FragmentManager manager = getSupportFragmentManager();
        CommonFragmentAdapter adapter = new CommonFragmentAdapter(manager, fragmentList);
        viewPager.setAdapter(adapter);
        group = (RadioGroup) findViewById(R.id.main_tabar);
        group.setOnCheckedChangeListener(this);
        group.check(R.id.first);
        viewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.first:
                viewPager.setCurrentItem(0);
                break;
            case R.id.finding:
                viewPager.setCurrentItem(1);
                break;
            case R.id.auditing:
                viewPager.setCurrentItem(2);
                break;
            case R.id.message:
                viewPager.setCurrentItem(3);
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
                group.check(R.id.first);
                break;
            case 1:
                group.check(R.id.finding);
                break;
            case 2:
                group.check(R.id.auditing);
                break;
            case 3:
                group.check(R.id.message);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
