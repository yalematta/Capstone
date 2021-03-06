package com.yalematta.podable.data.models.podcast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yalematta on 6/15/18.
 */

public class Podcast implements Parcelable {

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

    public Podcast() {

    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.xmlURL);
        dest.writeString(this.htmlURL);
        dest.writeString(this.imgURL);
        dest.writeInt(this.status);
        dest.writeString(this.slug);
        dest.writeString(this.layoutImageURL);
        dest.writeString(this.thumbImageURL);
        dest.writeString(this.smallImageURL);
        dest.writeString(this.microImageURL);
        dest.writeString(this.language);
        dest.writeString(this.lastpoll);
        dest.writeList(this.categories);
        dest.writeString(this.lastpub);
        dest.writeInt(this.rank);
        dest.writeString(this.urlFyyd);
        dest.writeString(this.description);
        dest.writeString(this.subtitle);
        dest.writeInt(this.episodeCount);
        dest.writeList(this.episodes);
    }

    protected Podcast(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.xmlURL = in.readString();
        this.htmlURL = in.readString();
        this.imgURL = in.readString();
        this.status = in.readInt();
        this.slug = in.readString();
        this.layoutImageURL = in.readString();
        this.thumbImageURL = in.readString();
        this.smallImageURL = in.readString();
        this.microImageURL = in.readString();
        this.language = in.readString();
        this.lastpoll = in.readString();
        this.categories = new ArrayList<Integer>();
        in.readList(this.categories, Integer.class.getClassLoader());
        this.lastpub = in.readString();
        this.rank = in.readInt();
        this.urlFyyd = in.readString();
        this.description = in.readString();
        this.subtitle = in.readString();
        this.episodeCount = in.readInt();
        this.episodes = new ArrayList<Episode>();
        in.readList(this.episodes, Episode.class.getClassLoader());
    }

    public static final Creator<Podcast> CREATOR = new Creator<Podcast>() {
        @Override
        public Podcast createFromParcel(Parcel source) {
            return new Podcast(source);
        }

        @Override
        public Podcast[] newArray(int size) {
            return new Podcast[size];
        }
    };
}
