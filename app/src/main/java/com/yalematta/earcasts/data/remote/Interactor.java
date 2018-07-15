package com.yalematta.earcasts.data.remote;

import android.util.Log;

import com.yalematta.earcasts.data.models.BaseListResponse;
import com.yalematta.earcasts.data.models.BaseResponse;
import com.yalematta.earcasts.data.models.category.CatPodcasts;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.data.models.podcast.Episode;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.ui.podcasts.category.CategoryPodcastsContract;
import com.yalematta.earcasts.ui.podcasts.details.PodcastBottomDialogContract;
import com.yalematta.earcasts.ui.main.categories.CategoriesContract;
import com.yalematta.earcasts.ui.main.featured.FeaturedContract;
import com.yalematta.earcasts.ui.podcasts.episodes.PodcastEpisodesContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yalematta on 6/17/18.
 */

public class Interactor implements FeaturedContract.Interactor, CategoriesContract.Interactor,
        PodcastBottomDialogContract.Interactor, PodcastEpisodesContract.Interactor,
        CategoryPodcastsContract.Interactor {

    private FeaturedContract.onGetDataListener mOnGetPodcastsListener;
    private CategoriesContract.onGetDataListener mOnGetCategoriesListener;
    private PodcastEpisodesContract.onGetDataListener mOnGetPodcastEpisodesListener;
    private CategoryPodcastsContract.onGetDataListener mOnGetCategoryPodcastsListener;
    private PodcastBottomDialogContract.onGetDataListener mOnGetPodcastBottomDialogListener;


    Podcast selectedPodcast = new Podcast();
    List<Podcast> allPodcasts = new ArrayList<>();
    List<Category> categoriesList = new ArrayList<>();
    List<Category> allCategories = new ArrayList<>();
    List<Episode> podcastEpisodes = new ArrayList<>();
    List<Podcast> categoryPodcasts = new ArrayList<>();
    HashMap<Integer, Category> categoriesMap = new HashMap<>();

    public Interactor(FeaturedContract.onGetDataListener onGetPodcastsListener) {
        this.mOnGetPodcastsListener = onGetPodcastsListener;
    }

    public Interactor(CategoriesContract.onGetDataListener onGetCategoriesListener) {
        this.mOnGetCategoriesListener = onGetCategoriesListener;
    }

    public Interactor(PodcastBottomDialogContract.onGetDataListener onGetPodcastBottomDialogListener) {
        this.mOnGetPodcastBottomDialogListener = onGetPodcastBottomDialogListener;
    }

    public Interactor(PodcastEpisodesContract.onGetDataListener onGetPodcastEpisodesListener) {
        this.mOnGetPodcastEpisodesListener = onGetPodcastEpisodesListener;
    }

    public Interactor(CategoryPodcastsContract.onGetDataListener onGetCategoryPodcastsListener) {
        this.mOnGetCategoryPodcastsListener = onGetCategoryPodcastsListener;
    }

    //region All Podcasts
    @Override
    public void getAllPodcasts(int currentPage, int podcastCount) {
        Call<BaseListResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getAllPodcasts(currentPage, podcastCount);
        call.enqueue(new Callback<BaseListResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Podcast>> call, Response<BaseListResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    allPodcasts = response.body().data;
                    Log.d("Data", "Refreshed");
                    mOnGetPodcastsListener.onSuccess("List Size: " + allPodcasts.size(), allPodcasts);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetPodcastsListener.onFailure(t.getMessage());
            }
        });
    }
    //endregion

    //region Featured Podcasts
    @Override
    public void getFeaturedPodcasts(int podcastCount, String podcastLanguage) {
        Call<BaseListResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getFeaturedPodcasts(podcastCount, podcastLanguage);
        call.enqueue(new Callback<BaseListResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Podcast>> call, Response<BaseListResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    allPodcasts = response.body().data;
                    Log.d("Data", "Refreshed");
                    mOnGetPodcastsListener.onSuccess("List Size: " + allPodcasts.size(), allPodcasts);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetPodcastsListener.onFailure(t.getMessage());
            }
        });
    }
    //endregion

    //region Categories
    @Override
    public void getCategories() {
        Call<BaseListResponse<Category>> call = ApiClient.getClient().getApiInterface().getCategories();
        call.enqueue(new Callback<BaseListResponse<Category>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Category>> call, Response<BaseListResponse<Category>> response) {
                if (response.isSuccessful()) {

                    allCategories = response.body().data;
                    Log.d("Data", "Refreshed");

                    for (int i = 0; i < allCategories.size(); i++) {
                        categoriesMap.put(allCategories.get(i).getId(), allCategories.get(i));
                        if (allCategories.get(i).getSubcategories().size() > 0) {
                            for (int j = 0; j < allCategories.get(i).getSubcategories().size(); j++) {
                                categoriesMap.put(allCategories.get(i).getSubcategories().get(j).getId(), allCategories.get(i).getSubcategories().get(j));
                            }
                        }
                    }

                    for (int i = 0; i < categoriesMap.size(); i++) {
                        if (categoriesMap.get(i) != null)
                            categoriesList.add(categoriesMap.get(i));
                    }

                } else {
                    Log.v("Error", "401 authentication error");
                }

                if (categoriesList != null) {
                    mOnGetCategoriesListener.onSuccess("List Size: " + categoriesList.size(), categoriesList);
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Category>> call, Throwable t) {
                Log.v("Error", t.getMessage());

                mOnGetCategoriesListener.onFailure(t.getMessage());
            }
        });

    }
    //endregion

    //region Podcast Details
    @Override
    public void getPodcastDetails(int podcastId) {
        Call<BaseResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getPodcastDetails(podcastId);
        call.enqueue(new Callback<BaseResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseResponse<Podcast>> call, Response<BaseResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    selectedPodcast = response.body().data;
                    mOnGetPodcastBottomDialogListener.onSuccess("Selected Podcast: " + selectedPodcast.getTitle(), selectedPodcast);
                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetPodcastBottomDialogListener.onFailure(t.getMessage());
            }
        });
    }
    //endregion

    //region Podcast Episodes
    @Override
    public void getPodcastEpisodes(int podcastId, int currentPage, int episodeCount) {
        Call<BaseResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getPodcastEpisodes(podcastId, currentPage, episodeCount);
        call.enqueue(new Callback<BaseResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseResponse<Podcast>> call, Response<BaseResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    podcastEpisodes = response.body().data.getEpisodes();
                    Log.d("Data", "Refreshed");
                    mOnGetPodcastEpisodesListener.onSuccess("Episodes Size: " + podcastEpisodes.size(), podcastEpisodes);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetPodcastEpisodesListener.onFailure(t.getMessage());
            }
        });
    }
    //endregion

    //region Category Podcasts
    @Override
    public void getCategoryPodcasts(int categoryId, int page, int count) {
        Call<BaseResponse<CatPodcasts>> call = ApiClient.getClient().getApiInterface().getPodcastsByCategory(categoryId, page, count);
        call.enqueue(new Callback<BaseResponse<CatPodcasts>>() {

            @Override
            public void onResponse(Call<BaseResponse<CatPodcasts>> call, Response<BaseResponse<CatPodcasts>> response) {
                if (response.isSuccessful()) {
                    categoryPodcasts = response.body().data.getPodcasts();
                    Log.d("Data", "Refreshed");
                    mOnGetCategoryPodcastsListener.onSuccess("Podcasts size: " + categoryPodcasts.size(), categoryPodcasts);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CatPodcasts>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetCategoryPodcastsListener.onFailure(t.getMessage());
            }
        });
    }
    //endregion

}
