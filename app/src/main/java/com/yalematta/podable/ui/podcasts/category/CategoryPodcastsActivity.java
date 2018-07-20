package com.yalematta.podable.ui.podcasts.category;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.category.Category;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/14/18.
 */

public class CategoryPodcastsActivity extends AppCompatActivity {

    private Category currentCategory;
    private static final String CATEGORY = "CATEGORY";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.app_bar) AppBarLayout appBarLayout;
    @BindView(R.id.nested_scrollview) NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        currentCategory = getIntent().getParcelableExtra(CATEGORY);
        getSupportActionBar().setTitle(currentCategory.getName());
        CategoryPodcastsFragment fragment = new CategoryPodcastsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CATEGORY, currentCategory);
        fragment.setArguments(bundle);
        ft.replace(R.id.nested_scrollview, fragment);
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
