package com.yasin.spotifystreamer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.TracksPager;

/**
 * Project: com.yasin.spotifystreamer.loader
 * Created by Yasin
 * Date: 2016-10-07.
 */
public class TracksLoader extends AsyncTaskLoader<Object> {
    private String name;
    public TracksLoader(Context context,String name) {
        super(context);
        this.name=name;

    }

    @Override
    public Object loadInBackground() {
        SpotifyApi api=new SpotifyApi();
        SpotifyService service = api.getService();
        TracksPager pager = service.searchTracks(name);
        return pager;
    }
}
