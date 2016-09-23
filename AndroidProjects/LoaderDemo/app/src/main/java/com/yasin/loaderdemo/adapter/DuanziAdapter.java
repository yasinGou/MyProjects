package com.yasin.loaderdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yasin.loaderdemo.R;
import com.yasin.loaderdemo.model.Duanzi;
import com.yasin.yasinlibray.ImageTask;

import java.util.List;

/**
 * Project: com.yasin.loaderdemo.adapter
 * Created by Administrator
 * Date: 2016-09-10.
 */
public class DuanziAdapter extends BaseAdapter {
    private Context context;
    private List<Duanzi> list;

    public DuanziAdapter(Context context, List<Duanzi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            ret= LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        }
        ViewHolder holder = ((ViewHolder) ret.getTag());
        if (holder== null) {
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        holder.bindView(position, list.get(position));


        return ret;
    }

    private static class ViewHolder {
        private TextView mTxtName;
        private TextView mTxtText;
        private ImageView mImage;

        public ViewHolder(View itemView) {
            mTxtName = ((TextView) itemView.findViewById(R.id.username));
            mTxtText = ((TextView) itemView.findViewById(R.id.duanzi_text));
            mImage= ((ImageView) itemView.findViewById(R.id.image));
        }

        public void bindView(int position, Duanzi duanzi) {
            mTxtName.setText(duanzi.getUserName());
            mTxtText.setText(duanzi.getText());
            if (duanzi.getUrl() != null) {
                new ImageTask(mImage).execute(duanzi.getUrl());
            }else {
                mImage.setImageBitmap(null);
            }
        }
    }
}
