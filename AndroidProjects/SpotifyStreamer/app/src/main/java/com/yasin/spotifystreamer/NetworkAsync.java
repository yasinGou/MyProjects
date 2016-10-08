package com.yasin.spotifystreamer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.yasin.spotifystreamer.adapter.MyArtistAdapter;
import com.yasin.spotifystreamer.model.ArtistEntity;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Pager;

/**
 * Project: com.yasin.spotifystreamer
 * Created by Yasin
 * Date: 2016-10-05.
 */
public class NetworkAsync extends AsyncTask<ListView,Void,List> {

    private ListView mListView;
    private Context mContext;
    private String  name;
    private final List<Object> infoList=new ArrayList<>();

    public NetworkAsync(Context context,String name) {
        mContext = context;
        this.name=name;
    }

    @Override
    protected List doInBackground(ListView... params) {
        mListView=params[0];
        SpotifyApi api = new SpotifyApi();
        SpotifyService spotifyService = api.getService();
        if (name != null) {
            ArtistsPager pager = spotifyService.searchArtists(name);
            if (pager != null) {
                Pager<Artist> artists = pager.artists;
                List<Artist> items = artists.items;
                for (Artist item : items) {
                    ArtistEntity entity = new ArtistEntity();
                    entity.setName(item.name);
                    int size = item.images.size();
                    if (size!=0) {
                        entity.setUrl(item.images.get(2).url);
                    }
                    infoList.add(entity);
                }
            }
        }
//        spotifyService.searchArtists("Beyond", new Callback<ArtistsPager>() {
//                    @Override
//                    public void success(ArtistsPager pager, Response response) {
//                        Pager<Artist> artists = pager.artists;
//                        List<Artist> items = artists.items;
//                        for (Artist item : items) {
//                            ArtistEntity entity = new ArtistEntity();
//                            entity.setName(item.name);
//                            int size = item.images.size();
//                            if (size != 0) {
//                                entity.setUrl(item.images.get(2).url);
//                            }
//                            infoList.add(entity);
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                    }
//                }
//        );
//        Log.d("TEST", "success: "+infoList.size());
//        for (int i = 0; i < infoList.size(); i++) {
//            ArtistEntity o = (ArtistEntity)infoList.get(i);
//            Log.d("TEST", "success: "+o.getName());
//
//        }

        return infoList;
    }

    @Override
    protected void onPostExecute(List list) {
        MyArtistAdapter adapter = new MyArtistAdapter(mContext, list);
        mListView.setAdapter(adapter);
    }
}
