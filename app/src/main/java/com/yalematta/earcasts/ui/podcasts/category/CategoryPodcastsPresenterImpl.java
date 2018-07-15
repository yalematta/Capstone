package com.yalematta.earcasts.ui.podcasts.category;

import com.yalematta.earcasts.data.models.category.CatPodcasts;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.data.remote.Interactor;
import com.yalematta.earcasts.ui.podcasts.details.PodcastBottomDialogContract;

import java.util.List;

/**
 * Created by yalematta on 7/15/18.
 */

public class CategoryPodcastsPresenterImpl implements CategoryPodcastsContract.Presenter, CategoryPodcastsContract.onGetDataListener {

    private final CategoryPodcastsContract.View mCategoryPodcastsContractView;
    private Interactor mIntractor;

    public CategoryPodcastsPresenterImpl(CategoryPodcastsContract.View CategoryPodcastsContractView) {
        mIntractor = new Interactor(this);
        mCategoryPodcastsContractView = CategoryPodcastsContractView;
        mCategoryPodcastsContractView.setPresenter(this);
    }

    @Override
    public void getCategoryPodcastsData(int categoryId, int page, int count) {
        mIntractor.getCategoryPodcasts(categoryId, page, count);
    }

    @Override
    public void onSuccess(String message, List<Podcast> catPodcasts) {
        mCategoryPodcastsContractView.onGetDataSuccess(message, catPodcasts);
    }

    @Override
    public void onFailure(String message) {
        mCategoryPodcastsContractView.onGetDataFailure(message);
    }


}