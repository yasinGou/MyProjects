package com.yasin.spotifystreamer.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: com.yasin.spotifystreamer.model
 * Created by Yasin
 * Date: 2016-10-07.
 */
public class Test {
    private List<Object> mList=new ArrayList<>();
    public class Inner{
        public Inner(){
            mList.add("hello");
        }
    }
    public void test(){
        int size = mList.size();
        Log.d("Test", "test: "+size);
    }
}
