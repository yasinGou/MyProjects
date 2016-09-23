package com.yasin.day27listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.yasin.asnyc.ImageLoader;
import com.yasin.asnyc.NetworkTask;
import com.yasin.baseadapter.Entry;
import com.yasin.baseadapter.MyAdapter;
import com.yasin.baseadapter.Result;
import com.yasin.baseadapter.TopAdapter;
import com.yasin.baseadapter.TopServer;
import com.yasin.networklibrary.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,NetworkTask.myCallback<Result>, AbsListView.OnScrollListener {

   // private MyAdapter adapter;
    private TopAdapter adapter;
    private int firstVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        List<String> list=new ArrayList<>();
//        for (int i = 0; i <100 ; i++) {
//            list.add(String.format(Locale.CHINA,"Item %03d",i));
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = (ListView) findViewById(R.id.listview);
//        listView.setAdapter(adapter);
        List<Map<String, ?>> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("title", String.format(Locale.CHINA, "title %03d", i + 1));
//           // map.put("body", String.format(Locale.CHINA, "body %03d", i + 1));
//          //  map.put("img", "http://d.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=42160088b4fd5266b3263446ca71fc4e/b219ebc4b74543a941fbf6af1c178a82b80114f4.jpg");
//            list.add(map);
//        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"title"}, new int[]{R.id.title});
        listView.setAdapter(adapter);
//        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"img", "title"},
//                new int[]{R.id.img, R.id.title});
//        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                switch (view.getId()) {
//                    case R.id.img:
//                        ImageView image = (ImageView) view;
//                        String url = (String) data;
//                        new ImageLoader(image).execute(url);
//                        return true;
//                }
//                return false;
//            }
//        });
 //       adapter = new MyAdapter(this, new ArrayList<Entry>());
//        adapter=new TopAdapter(this,R.layout.item,new ArrayList<Entry>());
//        listView.setAdapter(adapter);
//        listView.setOnScrollListener(this);
//        listView.setOnItemClickListener(this);
//        TopServer server = Tools.getInstance(TopServer.class);
//        server.getList(0, 2, 20).execute(this);
//        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //不一定和set的adapter相同。当listview上添加了一个view后，就会改变
       // Adapter adapter = parent.getAdapter();
        Object item = parent.getAdapter().getItem(position);
        if (item instanceof Entry) {
            Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Result result) {
        adapter.addAll(result.getEntries());

    }

    @Override
    public void onFaile(Exception e) {
        e.printStackTrace();
        Toast.makeText(MainActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                View childAt = view.getChildAt(0);
                Toast.makeText(MainActivity.this, "开始刷新"+childAt.getTop(), Toast.LENGTH_SHORT).show();
//                if (childAt!=null) {
//                    if (firstVisibleItem==0&&childAt.getTop()==view.getPaddingTop()) {
//                        Toast.makeText(MainActivity.this, "开始刷新", Toast.LENGTH_SHORT).show();
//                    }
//                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        // 判断listview滑动到顶，，。第一个条目完全显示
//        if (firstVisibleItem==0) {
//            int paddingTop = view.getPaddingTop();
//            View childAt = view.getChildAt(0);
//            if (childAt.getTop()==paddingTop) {
//
//            }
//        }
    }
}
