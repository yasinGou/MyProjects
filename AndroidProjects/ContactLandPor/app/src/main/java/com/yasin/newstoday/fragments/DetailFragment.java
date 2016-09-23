package com.yasin.newstoday.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yasin.newstoday.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private TextView name;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_detail, container, false);
        name = ((TextView) ret.findViewById(R.id.detail_name));
        Bundle bundle = getArguments();
        if (bundle != null) {
            name.setText(bundle.getString("name"));
        }
        return ret;
    }

    public void setMessage(Bundle bundle) {
        name.setText(bundle.getString("name"));

    }

}
