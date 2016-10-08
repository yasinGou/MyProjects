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
import com.yasin.spotifystreamer.model.ArtistEntity;

import java.util.List;

/**
 * Project: com.yasin.spotifystreamer.adapter
 * Created by Yasin
 * Date: 2016-10-05.
 */
public class MyArtistAdapter extends BaseAdapter {
    private Context mContext;
    private List data;
    LayoutInflater inflater;

    public MyArtistAdapter(Context context, List data) {
        mContext = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        Object ret = null;
        if (data != null) {
            ret = data.get(position);
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_artist, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        ArtistEntity entity = (ArtistEntity) data.get(position);
        if (entity.getUrl() != null) {
            Picasso.with(mContext).load(entity.getUrl()).into(holder.image);
        }
        holder.mTextView.setText(entity.getName());
        return convertView;
    }
    public class ViewHolder {
        private ImageView image;
        private TextView mTextView;
        public ViewHolder(View itemView) {
            mTextView = ((TextView) itemView.findViewById(R.id.name));
            image = ((ImageView) itemView.findViewById(R.id.image_view));
        }
    }

}
