package com.yalematta.podable.ui.main.search;

import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.data.remote.Interactor;

import java.util.List;

/**
 * Created by yalematta on 9/23/18.
 */

public class SearchPresenterImpl implements SearchContract.Presenter, SearchContract.onGetDataListener {

    private final SearchContract.View mSearchContractView;
    private Interactor mIntractor;

    public SearchPresenterImpl(SearchContract.View SearchContractView) {
        mIntractor = new Interactor(this);
        mSearchContractView = SearchContractView;
        mSearchContractView.setPresenter(this);
    }

    @Override
    public List<Podcast> getSearchedData(String title, String term) {
        mIntractor.getSearchedPodcasts(title, term);
        return null;
    }

    @Override
    public void onSuccess(String message, List<Podcast> list) {
        mSearchContractView.onGetDataSuccess(message, list);
    }

    @Override
    public void onFailure(String message) {
        mSearchContractView.onGetDataFailure(message);
    }
}
