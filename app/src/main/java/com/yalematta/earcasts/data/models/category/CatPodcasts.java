package com.yalematta.earcasts.data.models.category;

import com.google.gson.annotations.SerializedName;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 6/17/18.
 */

public class CatPodcasts {

    @SerializedName("category")
    public Category category;
    @SerializedName("podcasts")
    public List<Podcast> podcasts = null;
}
