package com.yalematta.podable.ui.podcasts.episodes;

import com.yalematta.podable.data.models.podcast.Episode;
import com.yalematta.podable.data.remote.Interactor;

import java.util.List;

/**
 * Created by yalematta on 7/9/18.
 */

public class PodcastEpisodesPresenterImpl implements PodcastEpisodesContract.Presenter, PodcastEpisodesContract.onGetDataListener {

    private final PodcastEpisodesContract.View mPodcastEpisodesContractView;
    private Interactor mIntractor;

    public PodcastEpisodesPresenterImpl(PodcastEpisodesContract.View PodcastEpisodesContractView) {
        mIntractor = new Interactor(this);
        mPodcastEpisodesContractView = PodcastEpisodesContractView;
        mPodcastEpisodesContractView.setPresenter(this);
    }

    @Override
    public void getEpisodes(int podcastId, int currentPage, int episodeCount) {
        mIntractor.getPodcastEpisodes(podcastId, currentPage, episodeCount);
    }

    @Override
    public void onSuccess(String message, List<Episode> episodes) {
        mPodcastEpisodesContractView.onGetDataSuccess(message, episodes);
    }

    @Override
    public void onFailure(String message) {
        mPodcastEpisodesContractView.onGetDataFailure(message);
    }
}
