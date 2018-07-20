package com.yalematta.podable.data.models.podcast;

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

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getGuid() { return guid; }

    public void setGuid(String guid) { this.guid = guid; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getEnclosure() { return enclosure; }

    public void setEnclosure(String enclosure) { this.enclosure = enclosure; }

    public Integer getPodcastId() { return podcastId; }

    public void setPodcastId(Integer podcastId) { this.podcastId = podcastId; }

    public String getImgURL() { return imgURL; }

    public void setImgURL(String imgURL) { this.imgURL = imgURL; }

    public String getPubdate() { return pubdate; }

    public void setPubdate(String pubdate) { this.pubdate = pubdate; }

    public Integer getDuration() { return duration; }

    public void setDuration(Integer duration) { this.duration = duration; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Integer getNumSeason() { return numSeason; }

    public void setNumSeason(Integer numSeason) { this.numSeason = numSeason; }

    public Integer getNumEpisode() { return numEpisode; }

    public void setNumEpisode(Integer numEpisode) { this.numEpisode = numEpisode; }

    public String getInserted() { return inserted; }

    public void setInserted(String inserted) { this.inserted = inserted; }

    public String getUrlFyyd() { return urlFyyd; }

    public void setUrlFyyd(String urlFyyd) { this.urlFyyd = urlFyyd; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getContentType() { return contentType; }

    public void setContentType(String contentType) { this.contentType = contentType; }
}
