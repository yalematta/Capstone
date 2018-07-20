package com.yalematta.podable.data.models;

import com.google.gson.annotations.SerializedName;
import com.yalematta.podable.data.models.podcast.Meta;

/**
 * Created by yalematta on 6/16/18.
 */

public class BaseResponse<T> {

    @SerializedName("status")
    public Integer status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("meta")
    public Meta meta;
    @SerializedName("data")
    public T data = null;
}
