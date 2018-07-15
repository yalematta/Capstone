package com.yalematta.earcasts.ui.podcasts.category;

import com.yalematta.earcasts.BaseView;
import com.yalematta.earcasts.BasePresenter;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 7/15/18.
 */

public interface CategoryPodcastsContract {

    interface View extends BaseView<CategoryPodcastsContract.Presenter> {
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
}
