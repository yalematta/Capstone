package com.yalematta.podable.data.models.category;

import com.google.gson.annotations.SerializedName;
import com.yalematta.podable.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 6/17/18.
 */

public class CatPodcasts {

    @SerializedName("category")
    public Category category;
    @SerializedName("podcasts")
    public List<Podcast> podcasts = null;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
