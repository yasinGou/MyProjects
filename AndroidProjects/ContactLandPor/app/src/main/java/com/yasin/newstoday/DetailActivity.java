package com.yasin.newstoday;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yasin.newstoday.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d("TAG", "onCreate: "+savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getBundleExtra("bundle");
            if (extras != null) {
                DetailFragment detailFragment = new DetailFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction tx = manager.beginTransaction();
                detailFragment.setArguments(extras);
                tx.replace(R.id.detail_fragment_container,detailFragment);
                tx.commit();
            }
        }
    }
}
