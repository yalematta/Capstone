package com.yalematta.podable.ui.main.search;

import com.yalematta.podable.BasePresenter;
import com.yalematta.podable.BaseView;
import com.yalematta.podable.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 9/23/18.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {
        void onGetDataSuccess(String message, List<Podcast> list);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        List<Podcast> getSearchedData(String title, String term);
    }

    interface Interactor {
        void getSearchedPodcasts(String title, String term);
    }

    interface onGetDataListener{
        void onSuccess(String message, List<Podcast> list);
        void onFailure(String message);
    }
}
