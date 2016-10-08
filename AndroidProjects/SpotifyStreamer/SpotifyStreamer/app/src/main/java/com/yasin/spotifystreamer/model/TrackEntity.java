package com.yasin.spotifystreamer.model;

/**
 * Project: com.yasin.spotifystreamer.model
 * Created by Yasin
 * Date: 2016-10-07.
 */
public class TrackEntity {
    private String mTrackName;
    private String mAlbumName;
    private String url;

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String trackName) {
        mTrackName = trackName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
