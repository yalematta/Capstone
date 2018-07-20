package com.yalematta.podable.ui.main.categories;

import com.yalematta.podable.data.models.category.Category;
import com.yalematta.podable.data.remote.Interactor;

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
    public void onSuccess(String message, List<Category> categoriesList) {
        mCategoriesContractView.onGetDataSuccess(message, categoriesList);
    }

    @Override
    public void onFailure(String message) {
        mCategoriesContractView.onGetDataFailure(message);
    }
}
