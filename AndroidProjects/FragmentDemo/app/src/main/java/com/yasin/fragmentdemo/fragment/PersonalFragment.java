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
public class PersonalFragment extends Fragment {


    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("PersonalFragment.onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("PersonalFragment.onCreateView");
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("PersonalFragment.onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("PersonalFragment.onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("PersonalFragment.onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("PersonalFragment.onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("PersonalFragment.onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("PersonalFragment.onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("PersonalFragment.onDetach");
    }
}
