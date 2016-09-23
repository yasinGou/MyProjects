package com.yasin.fragmentdemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasin.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {

    }

    /**当Fragment 创建之后，交给Activity的时候
     * 这个方法调用的时候，Fragment还没有布局
     *
     * 通常这方法用于设置Fragment接口回调
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("HomeFragment.onAttach");
    }
    //创建期间初始数据
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("HomeFragment.onCreate");
    }

    //当Fragment，需要向Activity添加界面时
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("HomeFragment.onCreateView");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    //当前Fragment通过<fragment>添加到Activity，当Activity
    //onCreat方法执行返回后自动调用
    //如果是代码方式添加到Activity  onCreateView之后，会自动调用这个方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("HomeFragment.onActivityCreated");
    }
    @Override
    public void onStart() {
        super.onStart();
        System.out.println("HomeFragment.onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("HomeFragment.onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("HomeFragment.onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("HomeFragment.onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("HomeFragment.onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("HomeFragment.onDestroy");
    }

    /**
     * Fragmenthe Activity 没有任何关系
     * Fragment的最后一个生命周期方法
     *
     * 当replace（）时，旧的Fragment会进入Detach   Avtivity 还在
     */
    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("HomeFragment.onDetach");
    }
}
