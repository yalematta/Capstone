package com.yalematta.earcasts.data.models.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public class Category {

    @SerializedName("id")
    public Integer id;
    @SerializedName("slug")
    public String slug;
    @SerializedName("name")
    public String name;
    @SerializedName("name_de")
    public String nameDe;
    @SerializedName("subcategories")
    public List<Subcategory> subcategories = null;

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

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
