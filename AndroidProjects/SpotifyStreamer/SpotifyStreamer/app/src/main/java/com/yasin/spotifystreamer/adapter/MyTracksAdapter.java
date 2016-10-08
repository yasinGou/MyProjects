package com.yasin.spotifystreamer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yasin.spotifystreamer.R;
import com.yasin.spotifystreamer.model.TrackEntity;

import java.util.Collection;
import java.util.List;

/**
 * Project: com.yasin.spotifystreamer.adapter
 * Created by Yasin
 * Date: 2016-10-07.
 */
public class MyTracksAdapter extends BaseAdapter {
    private final LayoutInflater inflate;
    private Context mContext;
    private List<Object> mList;

    public MyTracksAdapter(Context context, List<Object> list) {
        mContext = context;
        mList = list;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        Object ret = null;
        if (mList != null) {
            ret=mList.get(position);
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView=inflate.inflate(R.layout.item_tracks,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        TrackEntity entity = (TrackEntity) mList.get(position);
        holder.albumName.setText(entity.getAlbumName());
        holder.trackName.setText(entity.getTrackName());
        Picasso.with(mContext).load(entity.getUrl()).into(holder.mImage);
        return convertView;
    }
    public static class ViewHolder{
        private ImageView mImage;
        private TextView albumName;
        private TextView trackName;
        public ViewHolder(View itemView) {
            trackName = ((TextView) itemView.findViewById(R.id.track_name));
            albumName = ((TextView) itemView.findViewById(R.id.album_name));
            mImage = ((ImageView) itemView.findViewById(R.id.image_track));
        }
    }
    public void addAll(Collection<Object> list){
        mList.addAll(list);
    }
}
