package com.yalematta.earcasts.ui.podcasts.details;

import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.data.remote.Interactor;

/**
 * Created by yalematta on 6/16/18.
 */

public class PodcastBottomDialogPresenterImpl implements PodcastBottomDialogContract.Presenter, PodcastBottomDialogContract.onGetDataListener {

    private final PodcastBottomDialogContract.View mPodcastBottomDialogContractView;
    private Interactor mIntractor;

    public PodcastBottomDialogPresenterImpl(PodcastBottomDialogContract.View PodcastBottomDialogContractView) {
        mIntractor = new Interactor(this);
        mPodcastBottomDialogContractView = PodcastBottomDialogContractView;
        mPodcastBottomDialogContractView.setPresenter(this);
    }

    @Override
    public void getPodcastData(int podcastId) {
        mIntractor.getPodcastDetails(podcastId);
    }

    @Override
    public void onSuccess(String message, Podcast podcast) {
        mPodcastBottomDialogContractView.onGetDataSuccess(message, podcast);
    }

    @Override
    public void onFailure(String message) {
        mPodcastBottomDialogContractView.onGetDataFailure(message);
    }
}
