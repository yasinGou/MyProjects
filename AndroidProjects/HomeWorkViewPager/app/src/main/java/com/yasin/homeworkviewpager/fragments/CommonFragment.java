package com.yasin.homeworkviewpager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yasin.homeworkviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommonFragment extends Fragment {

    private TextView text;
    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public CommonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_common, container, false);
        ListView listView = (ListView) ret.findViewById(R.id.common_list);
        text = (TextView) ret.findViewById(R.id.test_txt);
        if (bundle != null) {
            String message = (String) bundle.get("message");
            switch (message) {
                case "recommend":
                    text.setText("recommend");
                    break;
                case "duanzi":
                    text.setText("duanzi");
                    break;
                case "video":
                    text.setText("video");
                    break;
                case "picture":
                    text.setText("picture");
                    break;
            }
        }
        return ret;
    }
    public static CommonFragment newInstance(String text){

        return null;
    }


}
