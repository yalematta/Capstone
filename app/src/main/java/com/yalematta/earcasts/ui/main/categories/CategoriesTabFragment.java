package com.yalematta.earcasts.ui.main.categories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.ui.podcasts.category.CategoryPodcastsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/16/18.
 */

public class CategoriesTabFragment extends Fragment implements CategoriesContract.View, AdapterView.OnItemClickListener {

    private ArrayAdapter<String> categoriesAdapter;
    private CategoriesContract.Presenter mPresenter;
    private List<Object> allCategories = new ArrayList<>();

    private static final String CATEGORY = "CATEGORY";
    private static final String CATEGORY_ID = "CATEGORY_ID";

    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.empty_view)
    TextView tvEmptyView;

    public static CategoriesTabFragment newInstance() {
        return new CategoriesTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.categories_frag, container, false);
        ButterKnife.bind(this, root);
        mPresenter.getCategoriesData();
        listView.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void setPresenter(CategoriesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(String message, List<Category> categoriesList) {
        allCategories.addAll(categoriesList);
        List<String> categoryNames = extractCategoryNames(categoriesList);
        categoriesAdapter = new ArrayAdapter<>(getContext(), R.layout.category_item, R.id.rowTextView, categoryNames);
        listView.setAdapter(categoriesAdapter);
    }

    private List<String> extractCategoryNames(List<Category> categoriesList) {
        List<String> categoryNames = new ArrayList<>();
        for (int i = 0; i < categoriesList.size(); i++) {
            categoryNames.add(categoriesList.get(i).getName());
        }
        return categoryNames;
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getContext(), CategoryPodcastsActivity.class);
        Category selectedCategory = (Category) allCategories.get(position);
        Bundle mBundle = new Bundle();
        mBundle.putInt(CATEGORY_ID, selectedCategory.getId());
        mBundle.putParcelable(CATEGORY, selectedCategory);
        intent.putExtras(mBundle);
        startActivity(intent);
    }
}

