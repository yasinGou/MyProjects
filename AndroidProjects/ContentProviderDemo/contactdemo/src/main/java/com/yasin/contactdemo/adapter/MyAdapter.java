package com.yasin.contactdemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yasin.contactdemo.R;

/**
 * Project: com.yasin.contactdemo.adapter
 * Created by Administrator
 * Date: 2016-09-09.
 */
public class MyAdapter extends CursorAdapter {
    private View.OnClickListener  onClickListener;

    public MyAdapter(Context context, Cursor cursor) {
        super(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View ret=null;
        ret= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ViewHolder holder = new ViewHolder(ret);
        ret.setTag(holder);
        return ret;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        int index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        String name = cursor.getString(index);
        holder.mTxtName.setText(name);
        index=cursor.getColumnIndex(ContactsContract.Contacts._ID);
        long id = cursor.getLong(index);
        holder.mBtnDel.setTag(id);
        holder.mBtnDel.setOnClickListener(onClickListener);
        
    }
    private static class ViewHolder{
        private TextView mTxtName;
        private Button mBtnDel;

        public ViewHolder(View itemView) {
            mTxtName=((TextView) itemView.findViewById(R.id.item_txt));
            mBtnDel=((Button) itemView.findViewById(R.id.item_del));
        }

    }
}
