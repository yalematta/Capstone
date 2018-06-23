package com.yalematta.earcasts.ui.main.categories;

import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.data.remote.Interactor;
import com.yalematta.earcasts.ui.main.featured.FeaturedContract;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public class CategoriesPresenterImpl implements CategoriesContract.Presenter, CategoriesContract.onGetDataListener {

    private final CategoriesContract.View mCategoriesContractView;
    private Interactor mIntractor;

    public CategoriesPresenterImpl(CategoriesContract.View CategoriesContractView) {
        mIntractor = new Interactor(this);
        mCategoriesContractView = CategoriesContractView;
        mCategoriesContractView.setPresenter(this);
    }

    @Override
    public void getCategoriesData() {
        mIntractor.getCategories();
    }


    @Override
    public void onSuccess(String message, HashMap<Integer, String> map) {
        mCategoriesContractView.onGetDataSuccess(message, map);
    }

    @Override
    public void onFailure(String message) {
        mCategoriesContractView.onGetDataFailure(message);
    }
}
