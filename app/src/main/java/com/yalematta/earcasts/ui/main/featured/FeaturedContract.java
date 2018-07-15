package com.yalematta.earcasts.ui.main.featured;

import com.yalematta.earcasts.BasePresenter;
import com.yalematta.earcasts.BaseView;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public interface FeaturedContract {

    interface View extends BaseView<Presenter> {
        void onGetDataSuccess(String message, List<Podcast> list);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(int currentPage, int podcastCount);
        void getFeaturedData(int podcastCount, String podcastLanguage);
    }

    interface Interactor {
        void getAllPodcasts(int currentPage, int podcastCount);
        void getFeaturedPodcasts(int podcastCount, String podcastLanguage);
    }

    interface onGetDataListener{
        void onSuccess(String message, List<Podcast> list);
        void onFailure(String message);
    }

     interface onPodcastClickListener {
        void onPodcastClick(int position);
    }
}
