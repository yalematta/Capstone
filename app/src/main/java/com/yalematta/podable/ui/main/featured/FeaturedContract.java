package com.yalematta.podable.ui.main.featured;

import com.yalematta.podable.BasePresenter;
import com.yalematta.podable.BaseView;
import com.yalematta.podable.data.models.podcast.Podcast;

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
