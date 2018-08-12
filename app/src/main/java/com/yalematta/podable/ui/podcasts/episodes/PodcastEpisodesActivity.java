package com.yalematta.podable.ui.podcasts.episodes;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Podcast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastEpisodesActivity extends AppCompatActivity {

    private Podcast currentPodcast;
    private static final String PODCAST = "PODCAST";
    private PodcastEpisodesPresenterImpl mPresenter;
    
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsedToolbar;
    @BindView(R.id.nested_scrollview) NestedScrollView nestedScrollView;
    @BindView(R.id.app_bar) AppBarLayout appBarLayout;
    @BindView(R.id.expanded_image) ImageView ivLogo;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        currentPodcast = getIntent().getParcelableExtra(PODCAST);
        PodcastEpisodesFragment fragment = new PodcastEpisodesFragment();
        mPresenter = new PodcastEpisodesPresenterImpl(fragment);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PODCAST, currentPodcast);
        fragment.setArguments(bundle);
        ft.replace(R.id.nested_scrollview, fragment);
        ft.commit();

        Glide.with(this)
                .asDrawable()
                .load(currentPodcast.getImgURL())
                .listener(new RequestListener() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                        Palette p = createPaletteSync(bitmap);
                        Palette.Swatch dominantSwatch = p.getDominantSwatch();

                        if(dominantSwatch != null){
                            int backgroundColor = dominantSwatch.getRgb();
                            appBarLayout.setBackgroundColor(backgroundColor);
                        }
                        return false;
                    }
                })
                .into(ivLogo);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsedToolbar.setTitle(currentPodcast.getTitle());
                    isShow = true;
                } else if(isShow) {
                    //careful there should a space between double quote otherwise it wont work
                    collapsedToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
