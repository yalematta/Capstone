package com.yalematta.podable.ui.podcasts.episodes;

import com.yalematta.podable.BasePresenter;
import com.yalematta.podable.BaseView;
import com.yalematta.podable.data.models.podcast.Episode;
import com.yalematta.podable.data.models.podcast.Podcast;

import java.util.List;


/**
 * Created by yalematta on 7/9/18.
 */

public interface PodcastEpisodesContract {

    interface View extends BaseView<Presenter> {
        void onGetDataSuccess(String message, Podcast podcast);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void getEpisodes(int podcastId, int currentPage, int episodeCount);
    }

    interface Interactor {
        void getPodcastEpisodes(int podcastId, int currentPage, int episodeCount);
    }

    interface onGetDataListener{
        void onSuccess(String message, Podcast podcast);
        void onFailure(String message);
    }

    interface onEpisodeClickListener {
        void onEpisodeClick(Episode episode, String podcastSlug);
    }
}
