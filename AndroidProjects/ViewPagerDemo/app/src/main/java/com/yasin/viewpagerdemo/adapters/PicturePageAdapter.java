package com.yasin.viewpagerdemo.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Project: com.yasin.viewpagerdemo.adapters
 * Created by Yasin
 * Date: 2016-09-14.
 */
public class PicturePageAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> list;

    public PicturePageAdapter(Context context,List<Integer> list) {
        this.context = context;
        this.list=list;
    }

    /**
     * PagerAdapter实现：
     * 1.必须要重写/实现四个方法，不能只有两个
     *
     * @return
     */
    @Override
    public int getCount() {
        int ret=0;
        if (list != null) {
            ret=list.size();
        }
        return ret;
    }

    /**
     * View 参数是否和object有关系
     * <p/>
     * ViewPager会进行对象的管理，利用对象来管理和定位View
     *
     * @param view
     * @param object instantiateItem()方法的返回值
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        //TODO:说明
        return view == object;
    }

    /**
     * 当ViewPager需要显示一页的时候，会调用这个方法，传递指定的页数
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret=null;
        //加载布局，或创建控件，添加到ViewGroup
        if (position==list.size()-1){

            //最后一个、，加载一个布局  有button

            //button 点击  传进一个监听器

        }else {
            ImageView view = new ImageView(context);
            view.setImageResource(list.get(position));
            ret=view;
        }
        //必须把创建，加载的视图，添加到viewGroup

        container.addView(ret);

        return ret;
    }

    /**
     * 当ViewPager 把一个页面左右移动的时候，达到一个限制的位置就会删除
     * 调用这个方法
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //必须手动的把创建的View，从ViewGroup删除

        container.removeView(((View) object));
    }
}
