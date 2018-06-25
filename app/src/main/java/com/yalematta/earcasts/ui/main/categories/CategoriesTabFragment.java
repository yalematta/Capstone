package com.yalematta.earcasts.ui.main.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.ui.main.adapter.CategoriesAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/16/18.
 */

public class CategoriesTabFragment extends Fragment implements CategoriesContract.View {

    private static final String TAG = CategoriesTabFragment.class.getSimpleName();

    private CategoriesContract.Presenter mPresenter;

    @BindView(R.id.list_view) ListView listView;
    @BindView(R.id.empty_view) TextView tvEmptyView;

    public CategoriesTabFragment() {

    }

    public static CategoriesTabFragment newInstance() {
        return new CategoriesTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.categories_frag, container, false);

        ButterKnife.bind(this, root);

        mPresenter.getCategoriesData();

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
    public void onGetDataSuccess(String message, List<String> categoriesList) {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categoriesList);
        listView.setAdapter(categoriesAdapter);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}

