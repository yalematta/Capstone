package com.yalematta.podable.ui.main.categories;

import com.yalematta.podable.BasePresenter;
import com.yalematta.podable.BaseView;
import com.yalematta.podable.data.models.category.Category;

import java.util.List;

/**
 * Created by yalematta on 6/16/18.
 */

public interface CategoriesContract {

    interface View extends BaseView<Presenter> {
        void onGetDataSuccess(String message, List<Category> categoriesList);
        void onGetDataFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void getCategoriesData();
    }

    interface Interactor {
        void getCategories();
    }

    interface onGetDataListener{
        void onSuccess(String message,  List<Category> categoriesList);
        void onFailure(String message);
    }
}
