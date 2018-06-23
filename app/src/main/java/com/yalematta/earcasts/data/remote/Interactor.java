package com.yalematta.earcasts.data.remote;

import android.util.Log;

import com.yalematta.earcasts.data.models.BaseListResponse;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.ui.main.categories.CategoriesContract;
import com.yalematta.earcasts.ui.main.featured.FeaturedContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yalematta on 6/17/18.
 */

public class Interactor implements FeaturedContract.Interactor, CategoriesContract.Interactor {

    private FeaturedContract.onGetDataListener mOnGetPodcastslistener;
    private CategoriesContract.onGetDataListener mOnGetCategorieslistener;

    List<Podcast> allPodcasts = new ArrayList<>();
    List<Category> allCategories = new ArrayList<>();
    HashMap<Integer,String> categoriesMap = new HashMap<>();

    public Interactor(FeaturedContract.onGetDataListener onGetPodcastslistener){
        this.mOnGetPodcastslistener = onGetPodcastslistener;
    }

    public Interactor(CategoriesContract.onGetDataListener onGetCategorieslistener){
        this.mOnGetCategorieslistener = onGetCategorieslistener;
    }

    @Override
    public void getAllPodcasts(int currentPage, int podcastCount) {
        Call<BaseListResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getAllPodcasts(currentPage, podcastCount);
        call.enqueue(new Callback<BaseListResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Podcast>> call, Response<BaseListResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    allPodcasts = response.body().data;
                    Log.d("Data", "Refreshed");
                    mOnGetPodcastslistener.onSuccess("List Size: " + allPodcasts.size(), allPodcasts);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetPodcastslistener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getFeaturedPodcasts(int podcastCount, String podcastLanguage) {
        Call<BaseListResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getFeaturedPodcasts(podcastCount, podcastLanguage);
        call.enqueue(new Callback<BaseListResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Podcast>> call, Response<BaseListResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    allPodcasts = response.body().data;
                    Log.d("Data", "Refreshed");
                    mOnGetPodcastslistener.onSuccess("List Size: " + allPodcasts.size(), allPodcasts);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetPodcastslistener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getCategories() {
        Call<BaseListResponse<Category>> call = ApiClient.getClient().getApiInterface().getCategories();
        call.enqueue(new Callback<BaseListResponse<Category>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Category>> call, Response<BaseListResponse<Category>> response) {
                if (response.isSuccessful()) {

                    allCategories = response.body().data;
                    Log.d("Data", "Refreshed");

                    int lastId = allCategories.get(allCategories.size() - 1).getId();

                    for (int i = 0; i < allCategories.size(); i++) {
                        categoriesMap.put(allCategories.get(i).getId(), allCategories.get(i).getName());
                        for (int j = 0; j < allCategories.get(i).getSubcategories().size(); j++){
                            categoriesMap.put(allCategories.get(i).getSubcategories().get(j).getId(), allCategories.get(i).getSubcategories().get(j).getName());
                        }
                    }

                    mOnGetCategorieslistener.onSuccess("List Size: " + allCategories.size(), categoriesMap);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Category>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetCategorieslistener.onFailure(t.getMessage());
            }
        });
    }
}
