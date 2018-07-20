package com.yalematta.podable.ui.podcasts.details;

import com.yalematta.podable.BasePresenter;
import com.yalematta.podable.BaseView;
import com.yalematta.podable.data.models.podcast.Podcast;

/**
 * Created by yalematta on 6/16/18.
 */

public interface PodcastBottomDialogContract {

    interface View extends BaseView<Presenter> {
        void onGetDataSuccess(String message, Podcast podcast);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void getPodcastData(int podcastId);
    }

    interface Interactor {
        void getPodcastDetails(int podcastId);
    }

    interface onGetDataListener{
        void onSuccess(String message, Podcast podcast);
        void onFailure(String message);
    }
}
