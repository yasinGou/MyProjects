package com.yasin.newstoday.fragments;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yasin.newstoday.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewListFragment extends Fragment implements AdapterView.OnItemClickListener {


    private Cursor cursor;
    private String name;

    /**
     * Fragment定义的接口，用于给Activity传递一个ListView点击事件调用
     */

    public interface OnNewSelectedListener {
        void OnNewsSelected(Bundle bundle);
    }

    private OnNewSelectedListener onNewSelectedListener;

    public NewListFragment() {
        // Required empty public constructor
    }
//这种set方式不建议使用
//    public void setOnNewSelectedListener(OnNewSelectedListener onNewSelectedListener) {
//        this.onNewSelectedListener = onNewSelectedListener;
//
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //内部设置 接口回调

        if (context instanceof OnNewSelectedListener) {
            onNewSelectedListener = ((OnNewSelectedListener) context);
        } else {
            throw new IllegalArgumentException("Activity must implements OnNewSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_new_list, container, false);
        ListView listView = (ListView) ret.findViewById(R.id.news_list);
        ContentResolver resolver = getContext().getContentResolver();

        //使用SimpleCursorAdapter  cursor 必须要查询到_id.
        cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return ret;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
       if (cursor.moveToPosition(position)) {
            int index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            if (index != -1) {
                name = cursor.getString(index);
            }
        }
        bundle.putString("name", name);
        bundle.putLong("id", id);
        onNewSelectedListener.OnNewsSelected(bundle);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onNewSelectedListener = null;
    }
}
