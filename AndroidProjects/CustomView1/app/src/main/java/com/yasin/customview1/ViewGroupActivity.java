package com.yasin.customview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yasin.customview1.widgets.AlphaIndicator;

import java.util.ArrayList;

public class ViewGroupActivity extends AppCompatActivity implements AlphaIndicator.OnIndexSelectedListener {

    private ListView contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        contactList = (ListView) findViewById(R.id.contact_list);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; i++) {
                data.add("item"+i+" "+j);

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        contactList.setAdapter(adapter);
        AlphaIndicator indicator = (AlphaIndicator) findViewById(R.id.alpha_indicator);
        if (indicator != null) {
            indicator.setOnIndexSelectedListener(this);
        }
    }

    @Override
    public void onIndexSelected(int index) {
          contactList.setSelection(index);
    }
}
