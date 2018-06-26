package com.yalematta.earcasts.data.models.podcast;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yalematta on 6/26/18.
 */

public class Episode {

    @SerializedName("title")
    public String title;
    @SerializedName("id")
    public Integer id;
    @SerializedName("guid")
    public String guid;
    @SerializedName("url")
    public String url;
    @SerializedName("enclosure")
    public String enclosure;
    @SerializedName("podcast_id")
    public Integer podcastId;
    @SerializedName("imgURL")
    public String imgURL;
    @SerializedName("pubdate")
    public String pubdate;
    @SerializedName("duration")
    public Integer duration;
    @SerializedName("status")
    public Integer status;
    @SerializedName("num_season")
    public Integer numSeason;
    @SerializedName("num_episode")
    public Integer numEpisode;
    @SerializedName("inserted")
    public String inserted;
    @SerializedName("url_fyyd")
    public String urlFyyd;
    @SerializedName("description")
    public String description;
    @SerializedName("content_type")
    public String contentType;
}
