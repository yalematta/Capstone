package com.yalematta.earcasts.data.models.podcast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yalematta on 6/15/18.
 */

public class Podcast {

    @SerializedName("title")
    public String title;
    @SerializedName("id")
    public int id;
    @SerializedName("xmlURL")
    public String xmlURL;
    @SerializedName("htmlURL")
    public String htmlURL;
    @SerializedName("imgURL")
    public String imgURL;
    @SerializedName("status")
    public int status;
    @SerializedName("slug")
    public String slug;
    @SerializedName("layoutImageURL")
    public String layoutImageURL;
    @SerializedName("thumbImageURL")
    public String thumbImageURL;
    @SerializedName("smallImageURL")
    public String smallImageURL;
    @SerializedName("microImageURL")
    public String microImageURL;
    @SerializedName("language")
    public String language;
    @SerializedName("lastpoll")
    public String lastpoll;
    @SerializedName("categories")
    public List<Integer> categories = null;
    @SerializedName("lastpub")
    public String lastpub;
    @SerializedName("rank")
    public int rank;
    @SerializedName("url_fyyd")
    public String urlFyyd;
    @SerializedName("description")
    public String description;
    @SerializedName("subtitle")
    public String subtitle;
    @SerializedName("episode_count")
    public int episodeCount;
    @SerializedName("episodes")
    public List<Episode> episodes = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXmlURL() {
        return xmlURL;
    }

    public void setXmlURL(String xmlURL) {
        this.xmlURL = xmlURL;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLayoutImageURL() {
        return layoutImageURL;
    }

    public void setLayoutImageURL(String layoutImageURL) {
        this.layoutImageURL = layoutImageURL;
    }

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public void setThumbImageURL(String thumbImageURL) {
        this.thumbImageURL = thumbImageURL;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getMicroImageURL() {
        return microImageURL;
    }

    public void setMicroImageURL(String microImageURL) {
        this.microImageURL = microImageURL;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastpoll() {
        return lastpoll;
    }

    public void setLastpoll(String lastpoll) {
        this.lastpoll = lastpoll;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public String getLastpub() {
        return lastpub;
    }

    public void setLastpub(String lastpub) {
        this.lastpub = lastpub;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUrlFyyd() {
        return urlFyyd;
    }

    public void setUrlFyyd(String urlFyyd) {
        this.urlFyyd = urlFyyd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public List<Episode> getEpisodes() { return episodes; }

    public void setEpisodes(List<Episode> episodes) { this.episodes = episodes; }
}
