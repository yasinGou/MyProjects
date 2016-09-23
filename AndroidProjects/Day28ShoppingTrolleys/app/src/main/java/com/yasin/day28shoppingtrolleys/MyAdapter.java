package com.yasin.day28shoppingtrolleys;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: com.yasin.day28shoppingtrolleys
 * Created by Administrator
 * Date: 2016-09-05.
 */
public class MyAdapter extends BaseAdapter {
    private static final String TAG =MyAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<String> list;
    private ArrayList<String> listCheck=new ArrayList<>();
    private ArrayList<CheckBox> listbox=new ArrayList<>();
    LayoutInflater inflater;
    private boolean showBox;

    public ArrayList<String> getListCheck() {
        return listCheck;
    }

    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.text.setText(list.get(position));
        if (showBox){
            holder.box.setVisibility(View.VISIBLE);
            holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listbox.add(((CheckBox) buttonView));
                }
            });
        }else {
            //view.gone  没有占位符
            holder.box.setVisibility(View.GONE);
        }
        return convertView;
    }
    public void setBoxFalse(){
        if (listbox != null) {
            for (CheckBox box : listbox) {
                box.setChecked(false);
            }
        }
    }
    public void setShowBox(){
        showBox=true;
        notifyDataSetChanged();
    }
    public void setShowBoxNo(){
        showBox=false;
        notifyDataSetChanged();
    }

    public void cleanAll(){
        list.clear();
        notifyDataSetChanged();
    }
    public void add(String data){
        list.add(data);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private CheckBox box;
        private TextView text;

        public ViewHolder(View view) {
            box=(CheckBox) view.findViewById(R.id.item_box);
            text= ((TextView) view.findViewById(R.id.item_text));
        }
    }
}
