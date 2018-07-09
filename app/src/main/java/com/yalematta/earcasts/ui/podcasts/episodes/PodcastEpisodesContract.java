package com.yalematta.earcasts.ui.podcasts.episodes;

import android.widget.ListView;

import com.yalematta.earcasts.BasePresenter;
import com.yalematta.earcasts.BaseView;
import com.yalematta.earcasts.data.models.podcast.Episode;

import java.util.List;


/**
 * Created by yalematta on 7/9/18.
 */

public interface PodcastEpisodesContract {

    interface View extends BaseView<PodcastEpisodesContract.Presenter> {
        void onGetDataSuccess(String message, List<Episode> episodes);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void getEpisodes(int podcastId, int currentPage, int episodeCount);
    }

    interface Interactor {
        void getPodcastEpisodes(int podcastId, int currentPage, int episodeCount);
    }

    interface onGetDataListener{
        void onSuccess(String message, List<Episode> episodes);
        void onFailure(String message);
    }
}
