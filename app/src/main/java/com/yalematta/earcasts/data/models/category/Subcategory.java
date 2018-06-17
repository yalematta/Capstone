package com.yalematta.earcasts.data.models.category;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yalematta on 6/16/18.
 */

public class Subcategory {

    @SerializedName("id")
    public Integer id;
    @SerializedName("slug")
    public String slug;
    @SerializedName("name")
    public String name;
    @SerializedName("name_de")
    public String nameDe;
}
