package com.yalematta.podable.data.models.podcast;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yalematta on 6/15/18.
 */

public class Meta {

    @SerializedName("paging")
    public Paging paging;
    @SerializedName("duration")
    public int duration;
}
