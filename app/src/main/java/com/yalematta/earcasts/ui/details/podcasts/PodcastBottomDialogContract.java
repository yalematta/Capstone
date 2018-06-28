package com.yalematta.earcasts.ui.details.podcasts;

import com.yalematta.earcasts.BasePresenter;
import com.yalematta.earcasts.BaseView;
import com.yalematta.earcasts.data.models.podcast.Podcast;

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
