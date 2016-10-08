package com.yasin.spotifystreamer;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yasin.spotifystreamer.adapter.MyTracksAdapter;
import com.yasin.spotifystreamer.loader.TracksLoader;
import com.yasin.spotifystreamer.model.TrackEntity;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;

public class TracksActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {

    private ListView mListView;
    private MyTracksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);
        LoaderManager manager = getSupportLoaderManager();
        String name = getIntent().getStringExtra("name");
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        manager.initLoader(998,bundle,this);
        mListView = (ListView) findViewById(R.id.list_tracks);
        mAdapter = new MyTracksAdapter(this,null);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        String name = args.getString("name");
        return  new TracksLoader(this,name);
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        TracksPager pager = (TracksPager) data;
        List<Object> list=new ArrayList<>();
        Pager<Track> tracks = pager.tracks;
        List<Track> items = tracks.items;
        for (Track item : items) {
            TrackEntity entity = new TrackEntity();
            entity.setTrackName(item.name);
            entity.setAlbumName(item.album.name);
            List<Image> images = item.album.images;
            if (images.size()!=0) {
                entity.setUrl(images.get(2).url);
            }
           list.add(entity);
        }
        mAdapter.addAll(list);
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
