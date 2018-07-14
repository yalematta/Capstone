package com.yalematta.earcasts.ui.main.categories;

import com.yalematta.earcasts.BasePresenter;
import com.yalematta.earcasts.BaseView;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import java.util.HashMap;
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
