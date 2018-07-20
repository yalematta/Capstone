package com.yalematta.podable.data.models.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.yalematta.podable.data.models.podcast.Podcast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public class Category implements Parcelable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("slug")
    public String slug;
    @SerializedName("name")
    public String name;
    @SerializedName("name_de")
    public String nameDe;
    @SerializedName("subcategories")
    public List<Category> subcategories = null;
    @SerializedName("podcasts")
    public  List<Podcast> podcasts = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.slug);
        dest.writeString(this.name);
        dest.writeString(this.nameDe);
        dest.writeList(this.subcategories);
        dest.writeTypedList(this.podcasts);
    }

    protected Category(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.slug = in.readString();
        this.name = in.readString();
        this.nameDe = in.readString();
        this.subcategories = new ArrayList<Category>();
        in.readList(this.subcategories, Subcategory.class.getClassLoader());
        this.podcasts = in.createTypedArrayList(Podcast.CREATOR);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
