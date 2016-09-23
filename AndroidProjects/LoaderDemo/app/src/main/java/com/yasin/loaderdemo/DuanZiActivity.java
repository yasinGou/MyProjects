package com.yasin.loaderdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.yasin.loaderdemo.Loaders.NetworkLoader;
import com.yasin.loaderdemo.adapter.DuanziAdapter;
import com.yasin.loaderdemo.model.Duanzi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DuanZiActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<JSONObject>, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private Bundle args;
    private SwipeRefreshLayout refresh;
    private List<Duanzi> items;
    public DuanziAdapter adapter;
    public int firstVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duan_zi);
        ListView listView = (ListView) findViewById(R.id.duanzi_list);
        //下拉刷新
        items = new ArrayList<>();

        adapter = new DuanziAdapter(this, items);
        listView.setAdapter(adapter);

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refresh.setOnRefreshListener(this);
        LoaderManager manager = getSupportLoaderManager();
        manager.initLoader(998, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new CursorLoader(DuanZiActivity.this, Uri.parse("content://yasin.duanzi.provider.MyContentProvider/duanzi"), null, null, null, null);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                items.clear();
                if (data != null) {

                    while (data.moveToNext()) {
                        Duanzi cursor = Duanzi.createFromCursor(data);
                        items.add(cursor);
                    }
                }
                data.close();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        });
        args = new Bundle();
        args.putString("url", "http://ic.snssdk.com/neihan/stream/mix/v1/?content_type=-102&message_cursor=-1&loc_time=1432654641&latitude=40.0522901291784&longitude=116.23490963616668&city=%E5%8C%97%E4%BA%AC&count=30&screen_width=800&iid=2767929313&device_id=2757969807&ac=wifi&channel=baidu2&aid=7&app_name=joke_essay&version_code=400&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6");
        manager.initLoader(199, args, this);
        listView.setOnScrollListener(this);
    }


    @Override
    public Loader<JSONObject> onCreateLoader(int id, Bundle args) {
        String url = null;
        if (args != null) {
            url = args.getString("url");
        }
        return new NetworkLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<JSONObject> loader, JSONObject data) {
        //代表数据加载完成，还原刷新状态
        refresh.setRefreshing(false);
        if (data != null) {

            try {
                JSONObject dataObect = data.getJSONObject("data");
                JSONArray jsonArray = dataObect.getJSONArray("data");
                int length = jsonArray.length();
                items.clear();
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://yasin.duanzi.provider.MyContentProvider/duanzi");

                for (int i = 0; i < length; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    int type = object.getInt("type");
                    if (type == 1) {
                        Duanzi duanzi = Duanzi.createFromJson(object);
                        items.add(duanzi);
                        resolver.insert(uri, duanzi.generateContentValues());
                    }
                }

                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<JSONObject> loader) {

    }

//    public void btnReload(View view) {
//        LoaderManager manager = getSupportLoaderManager();
//        //如果loader存在，先reset loader ，调用接口之后再执行intitLoader
//        manager.restartLoader(998,args,this);
//    }

    @Override
    public void onRefresh() {
        LoaderManager manager = getSupportLoaderManager();
        //如果loader存在，先reset loader ，调用接口之后再执行intitLoader
        manager.restartLoader(998, args, this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
//                View view0 = view.getChildAt(0);
//                if (view0 != null) {
//                    if (firstVisibleItem==0&&view0.getTop()==view.getPaddingTop()) {
//                        refresh.setRefreshing(true);
//                    }else {
//                        refresh.setRefreshing(false);
//                    }
//                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        View view0 = view.getChildAt(0);
        if (view0 != null) {
            if (firstVisibleItem == 0 && view0.getTop() == view.getPaddingTop()) {
                refresh.setEnabled(true);
             //   refresh.setRefreshing(true);
            } else {
                refresh.setEnabled(false);
            //    refresh.setRefreshing(false);
            }
        }
    }
}
