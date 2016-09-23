package com.yasin.superfileexplorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yasin.superfileexplorer.R;

import java.io.File;
import java.util.List;

/**
 * Project: com.yasin.superfileexplorer.adapter
 * Created by Administrator
 * Date: 2016-09-06.
 */
public class FileListAdapter extends BaseAdapter{
    private Context context;
    private List<File> mfiles;

    public FileListAdapter(Context context, List<File> mfiles) {
        this.context = context;
        this.mfiles = mfiles;
    }

    @Override
    public int getCount() {
        int ret=0;
        if (mfiles!=null) {
            ret=mfiles.size();
        }
        return ret;
    }

    /**
     * 获取指定条目的数据，只有getCount>0才可以执行
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mfiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret=null;
        if (convertView != null) {
            ret=convertView;
        }else {
            ret= LayoutInflater.from(context).inflate(R.layout.item_file,parent,false);
        }
        ViewHolder holder= ((ViewHolder) ret.getTag());
        if (holder==null) {
            holder= new ViewHolder(ret);
            ret.setTag(holder);
        }
        File file = mfiles.get(position);
        holder.bindView(position,file);
        return ret;
    }
    private static class ViewHolder{
        private ImageView mImg;
        private TextView mTxtName;
        public ViewHolder(View itemview){
            mImg= ((ImageView) itemview.findViewById(R.id.item_file_icon));
            mTxtName= ((TextView) itemview.findViewById(R.id.item_file_name));
        }
        public void bindView(int position,File file){
            if (file.isDirectory()) {
                mImg.setImageResource(R.mipmap.ico_folder);
            }else {
                mImg.setImageResource(R.mipmap.ico_file);
            }
            mTxtName.setText(file.getName());
        }
    }
}
