package com.yalematta.earcasts.data.models;

import com.google.gson.annotations.SerializedName;
import com.yalematta.earcasts.data.models.podcast.Meta;

import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public class BaseListResponse<T> {

    @SerializedName("status")
    public Integer status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("meta")
    public Meta meta;
    @SerializedName("data")
    public List<T> data = null;
}
