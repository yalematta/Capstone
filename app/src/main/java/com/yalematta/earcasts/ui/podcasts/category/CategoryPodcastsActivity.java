package com.yalematta.earcasts.ui.podcasts.category;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.ui.podcasts.episodes.PodcastEpisodesFragment;

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
