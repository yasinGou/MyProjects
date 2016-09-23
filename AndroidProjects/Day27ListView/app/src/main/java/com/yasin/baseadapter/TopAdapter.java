package com.yasin.baseadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yasin.asnyc.ImageUtil;
import com.yasin.day27listview.R;

import java.util.List;

/**
 * Project: com.yasin.baseadapter
 * Created by Administrator
 * Date: 2016-09-02.
 */
public class TopAdapter extends CommonAdapter<Entry,TopAdapter.MyViewHolder> {

    public TopAdapter(Context context, int layoutId, List<Entry> list) {
        super(context, layoutId, list);
    }

    @Override
    public void onBinderView(Entry data, MyViewHolder holer) {
        holer.text.setText(data.getText());
        ImageUtil.loadImage(data.getImageUrl(),holer.image);
    }

    public static class MyViewHolder extends CommonAdapter.ViewHolder{

        private final TextView text;
        private final ImageView image;

        public MyViewHolder(View view) {
            super(view);
            text = ((TextView) view.findViewById(R.id.title));
            image = ((ImageView) view.findViewById(R.id.img));
        }
    }
}
