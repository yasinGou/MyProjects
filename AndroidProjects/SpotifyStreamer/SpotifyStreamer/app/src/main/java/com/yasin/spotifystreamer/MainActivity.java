package com.yasin.spotifystreamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yasin.spotifystreamer.model.ArtistEntity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,SearchView.OnQueryTextListener {

    private ListView mListView;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemSelectedListener(this);
        mSearchView = (SearchView) findViewById(R.id.search_name);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setFocusable(false);
        mSearchView.setOnQueryTextListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArtistEntity item = (ArtistEntity) parent.getAdapter().getItem(position);
        String name = item.getName();
        Intent intent = new Intent(this, TracksActivity.class);
        intent.putExtra("name",name);
        startActivity(intent);
        Log.d("MainActivity", "onItemSelected: ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        NetworkAsync async = new NetworkAsync(this,query);
        async.execute(mListView);
        mSearchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
