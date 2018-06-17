package com.yalematta.earcasts.data.remote;

import com.yalematta.earcasts.data.models.BaseListResponse;
import com.yalematta.earcasts.data.models.BaseResponse;
import com.yalematta.earcasts.data.models.category.CatPodcasts;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yalematta on 6/15/18.
 */

public interface ApiInterface {

    @GET("categories")
    Call<BaseListResponse<Category>> getCategories();

    @GET("feature/podcast/hot/languages")
    Call<BaseListResponse<String>> getFeaturedLanguages();

    @GET("podcasts")
    Call<BaseListResponse<Podcast>> getAllPodcasts(@Query("page") int page, @Query("count") int count);

    @GET("search/podcast")
    Call<BaseListResponse<Podcast>> searchPodcasts(@Query("title") String title, @Query("term") String term);

    @GET("feature/podcast/hot")
    Call<BaseListResponse<Podcast>> getFeaturedPodcasts(@Query("count") int count, @Query("language") String language);

    @GET("podcast/recommend")
    Call<BaseListResponse<Podcast>> getRecommendedPodcasts(@Query("podcast_id") int podcastID, @Query("count") int count);

    @GET("category")
    Call<BaseResponse<CatPodcasts>> getPodcastsByCategory(@Query("category_id") int categoryID, @Query("page") int page, @Query("count") int count);

}
