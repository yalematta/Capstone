package com.yalematta.podable.ui.main.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.main.adapter.SearchAdapter;
import com.yalematta.podable.ui.main.featured.FeaturedContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 8/26/18.
 */

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private SearchContract.Presenter mPresenter;

    @BindView(R.id.listView) ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchable_layout);

        ButterKnife.bind(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchActivity.this, "clicked search result item is " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        // search
        handleSearch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch();
    }

    private void handleSearch() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);
             mPresenter = new SearchPresenterImpl(this);
             mPresenter.getSearchedData(searchQuery, searchQuery);
        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String selectedSuggestionRowId = intent.getDataString();
            //execution comes here when an item is selected from search suggestions
            //you can continue from here with user selected search item
            Toast.makeText(this, "selected search suggestion " + selectedSuggestionRowId,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(String message, List<Podcast> list) {
        SearchAdapter adapter = new SearchAdapter(this, android.R.layout.simple_dropdown_item_1line, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}