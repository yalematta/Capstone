package com.yalematta.earcasts.data.models.podcast;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yalematta on 6/15/18.
 */

public class Paging {

    @SerializedName("count")
    public int count;
    @SerializedName("page")
    public int page;
    @SerializedName("first_page")
    public int firstPage;
    @SerializedName("last_page")
    public int lastPage;
    @SerializedName("next_page")
    public int nextPage;
}
