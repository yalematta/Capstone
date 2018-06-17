package com.yalematta.earcasts.ui.main.featured;

import android.content.Context;

import com.yalematta.earcasts.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public class FeaturedPresenterImpl implements FeaturedContract.Presenter, FeaturedContract.onGetDataListener {

    private final FeaturedContract.View mFeaturedContractView;
    private Intractor mIntractor;

    public FeaturedPresenterImpl(FeaturedContract.View RestaurantContractView) {
        mIntractor = new Intractor(this);
        mFeaturedContractView = RestaurantContractView;
        mFeaturedContractView.setPresenter(this);
    }

//  @Override
//  public void getData(int currentPage, int podcastCount) {
//      mIntractor.getAllPodcasts(currentPage, podcastCount);
//  }

    @Override
    public void getFeaturedData(int podcastCount, String podcastLanguage) {
        mIntractor.getFeaturedPodcasts(podcastCount, podcastLanguage);
    }

    @Override
    public void onSuccess(String message, List<Podcast> list) {
        mFeaturedContractView.onGetDataSuccess(message, list);
    }

    @Override
    public void onFailure(String message) {
        mFeaturedContractView.onGetDataFailure(message);
    }
}
