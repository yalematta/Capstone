package com.yalematta.podable.ui.podcasts.category;

import com.yalematta.podable.BaseView;
import com.yalematta.podable.BasePresenter;
import com.yalematta.podable.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 7/15/18.
 */

public interface CategoryPodcastsContract {

    interface View extends BaseView<Presenter> {
        void onGetDataSuccess(String message, List<Podcast> catPodcasts);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void getCategoryPodcastsData(int categoryId, int page, int count);
    }

    interface Interactor {
        void getCategoryPodcasts(int categoryId, int page, int count);
    }

    interface onGetDataListener{
        void onSuccess(String message, List<Podcast> catPodcasts);
        void onFailure(String message);
    }

    interface onPodcastClickListener {
        void onPodcastClick(int position);
    }
}
