package com.yalematta.earcasts.ui.main.featured;

import android.util.Log;

import com.yalematta.earcasts.data.models.BaseListResponse;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.data.remote.ApiClient;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yalematta on 6/17/18.
 */

public class Intractor implements FeaturedContract.Interactor {

    private FeaturedContract.onGetDataListener mOnGetDatalistener;

    List<Podcast> allPodcasts = new ArrayList<>();

    public Intractor(FeaturedContract.onGetDataListener mOnGetDatalistener){
        this.mOnGetDatalistener = mOnGetDatalistener;
    }

//  @Override
//  public void getAllPodcasts(int currentPage, int podcastCount) {
//      Call<BaseListResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getAllPodcasts(currentPage, podcastCount);
//      call.enqueue(new Callback<BaseListResponse<Podcast>>() {
//
//          @Override
//          public void onResponse(Call<BaseListResponse<Podcast>> call, Response<BaseListResponse<Podcast>> response) {
//              if (response.isSuccessful()) {
//                  allPodcasts = response.body().data;
//                  Log.d("Data", "Refreshed");
//                  mOnGetDatalistener.onSuccess("List Size: " + allPodcasts.size(), allPodcasts);
//
//              } else {
//                  Log.v("Error", "401 authentication error");
//              }
//          }
//
//          @Override
//          public void onFailure(Call<BaseListResponse<Podcast>> call, Throwable t) {
//              Log.v("Error", t.getMessage());
//              mOnGetDatalistener.onFailure(t.getMessage());
//          }
//      });
//  }

    @Override
    public void getFeaturedPodcasts(int podcastCount, String podcastLanguage) {
        Call<BaseListResponse<Podcast>> call = ApiClient.getClient().getApiInterface().getFeaturedPodcasts(podcastCount, podcastLanguage);
        call.enqueue(new Callback<BaseListResponse<Podcast>>() {

            @Override
            public void onResponse(Call<BaseListResponse<Podcast>> call, Response<BaseListResponse<Podcast>> response) {
                if (response.isSuccessful()) {
                    allPodcasts = response.body().data;
                    Log.d("Data", "Refreshed");
                    mOnGetDatalistener.onSuccess("List Size: " + allPodcasts.size(), allPodcasts);

                } else {
                    Log.v("Error", "401 authentication error");
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<Podcast>> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
