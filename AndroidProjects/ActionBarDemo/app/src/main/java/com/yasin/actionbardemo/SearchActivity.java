package com.yasin.actionbardemo;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        //v4包方式设置搜索输入框
        MenuItem item = menu.findItem(R.id.action_search);
//        //设置搜索输入框
//        //1.查找指定的MenuItem
//        //2.设置SearchView
//        View view = SearchViewCompat.newSearchView(this);
//       // item.setActionView(view);
//        //兼容方法
//        MenuItemCompat.setActionView(item,view);

        //v7包方式

        View view = MenuItemCompat.getActionView(item);
        if (view != null) {
            searchView = (SearchView) view;
            //设置SearchView查询回调接口
            searchView.setOnQueryTextListener(this);
            //搜索输入框没有显示的时候，点击Acition回调这个接口，显示出输入框
            searchView.setOnSearchClickListener(this);
            //当自动补全的内容被选中的时候，回调这个接口
            //searchView.setOnSuggestionListener();
            //设置搜索的自动补全，或者实现搜索历史
          //  searchView.setSuggestionsAdapter();
        }
        return true;
    }

    /**
     * 当用户在输入法中点击搜索按钮时，或者输入回车的时候，
     * 调用这个方法，发起实际的搜索功能
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        //清除焦点
        searchView.clearFocus();
        return true;
    }
    /**
     *每一次输入字符都会回调这个方法，实现搜索的联想功能
     */
    @Override
    public boolean onQueryTextChange(String newText) {


        return true;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(SearchActivity.this, "点击出现搜索输入框", Toast.LENGTH_SHORT).show();
    }
}
