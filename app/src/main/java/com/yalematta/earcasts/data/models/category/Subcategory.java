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
}
