package com.yasin.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

/**
 * Project: com.yasin.baseadapter
 * Created by Administrator
 * Date: 2016-09-02.
 */
public abstract class CommonAdapter<D,VH extends CommonAdapter.ViewHolder>extends BaseAdapter {
    private final LayoutInflater inflater;
    private Context context;
    private List<D> list;
    private int layoutId;

    public CommonAdapter(Context context, int layoutId, List<D> list) {
        this.context = context;
        this.layoutId = layoutId;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView=inflater.inflate(layoutId,parent,false);
            Class type = (Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor constructor = type.getConstructor(View.class);
                Object o = constructor.newInstance(convertView);
                convertView.setTag(o);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        onBinderView(list.get(position), ((VH) convertView.getTag()));

        return convertView;
    }
    public abstract void onBinderView (D data,VH holer);
    public void addAll(Collection<? extends D > collections){
        list.addAll(collections);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private View view;
        public ViewHolder(View view){
            this.view=view;
        }
    }
}
