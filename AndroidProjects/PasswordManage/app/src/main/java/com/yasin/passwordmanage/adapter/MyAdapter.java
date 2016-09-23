package com.yasin.passwordmanage.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.yasin.passwordmanage.R;
import com.yasin.passwordmanage.database.DataContracs;

/**
 * Project: com.yasin.passwordmanage.adapter
 * Created by Administrator
 * Date: 2016-09-08.
 */
public class MyAdapter extends CursorAdapter {
    private LayoutInflater inflater;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MyAdapter(Context context, Cursor cursor){
        super(context,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        inflater = LayoutInflater.from(context);

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = ((TextView) view.findViewById(R.id.title));
        TextView user = (TextView) view.findViewById(R.id.user);
            int index = cursor.getColumnIndex(DataContracs.Password.TITLE);
            if (index!=-1) {
                title.setText(cursor.getString(index));
            }
            index = cursor.getColumnIndex(DataContracs.Password.USER);
            if (index!=-1) {
                user.setText(cursor.getString(index));
            }
        }

}
