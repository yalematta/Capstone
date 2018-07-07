package com.yalematta.earcasts.ui.podcasts.episodes;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastActivity extends AppCompatActivity {

    private Podcast currentPodcast;
    private static final String PODCAST = "PODCAST";

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsedToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.expanded_image)
    ImageView ivLogo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        currentPodcast = getIntent().getParcelableExtra(PODCAST);

        initView();
    }

    private void initView() {

        Glide.with(this)
                .load(currentPodcast.getSmallImageURL())
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
