package com.yasin.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yasin.asnyc.ImageLoader;
import com.yasin.asnyc.ImageUtil;
import com.yasin.day27listview.R;

import java.util.Collection;
import java.util.List;

/**
 * Project: com.yasin.baseadapter
 * Created by Administrator
 * Date: 2016-09-02.
 */
public class MyAdapter extends BaseAdapter {
    private static final String TAG = MyAdapter.class.getSimpleName();
    private final LayoutInflater inflater;
    private Context context;
    private List<Entry> list;

    public MyAdapter(Context context, List<Entry> list) {
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
        //如果数据有id属性 就返回id 没有就返回position
        return position;
    }

    /**
     *
     * @param position
     * @param convertView  滑动的时候，上面消失的view，可重复利用，减少加载
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Entry entry = list.get(position);
        holder.text.setText(entry.getText());
        ImageUtil.loadImage(entry.getImageUrl(),holder.image);
       // new ImageLoader(holder.image).execute(entry.getImageUrl());
        return convertView;
    }
    public void addAll(Collection<? extends Entry> collection){
        list.addAll(collection);
        notifyDataSetChanged();

    }
    public static class ViewHolder{
        public  ImageView image;
        public  TextView text;

        public ViewHolder(View view) {
            text=(TextView) view.findViewById(R.id.title);
            image= ((ImageView) view.findViewById(R.id.img));
        }
    }
}
