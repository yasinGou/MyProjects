package com.yasin.asnyc;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

/**
 * Project: com.yasin.asnyc
 * Created by Administrator
 * Date: 2016-09-02.
 */
public class ImageUtil {
    static LruCache<String,Bitmap> cache=new LruCache<String,Bitmap>(5<<20){
        @Override
        protected int sizeOf(String key, Bitmap value) {
        //    return super.sizeOf(key, value);
       //     int byteCount = value.getByteCount(); api 12 之后才有
            int bytecount = value.getRowBytes() * value.getHeight();
            return  bytecount;
        }

    };
        public static void loadImage(String url,ImageView image){
            Bitmap bitmap = cache.get(url);
            if (bitmap != null) {
                image.setImageBitmap(bitmap);
            }else {
                new ImageLoader(image).execute(url);
            }

        }
}
