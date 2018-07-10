package com.yalematta.earcasts.ui.main.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalematta.earcasts.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/16/18.
 */

public class CategoriesTabFragment extends Fragment implements CategoriesContract.View {

    private CategoriesContract.Presenter mPresenter;
    private ArrayAdapter<String> categoriesAdapter;

    @BindView(R.id.list_view) ListView listView;
    @BindView(R.id.empty_view) TextView tvEmptyView;

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
        categoriesAdapter = new ArrayAdapter<>(getContext(), R.layout.category_item, R.id.rowTextView, categoriesList);
        listView.setAdapter(categoriesAdapter);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}

