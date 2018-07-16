package com.yalematta.earcasts.ui.podcasts.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.ui.main.adapter.PodcastAdapter;
import com.yalematta.earcasts.ui.main.featured.FeaturedContract;
import com.yalematta.earcasts.ui.main.featured.FeaturedTabFragment;
import com.yalematta.earcasts.ui.podcasts.adapter.CatPodcastAdapter;
import com.yalematta.earcasts.ui.podcasts.details.PodcastBottomDialogPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/14/18.
 */

public class CategoryPodcastsFragment extends Fragment implements CategoryPodcastsContract.View {

    private CategoryPodcastsContract.Presenter mPresenter;
    private CategoryPodcastsPresenterImpl mCategoryPodcastsPresenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private LinearLayoutManager linearLayoutManager;
    private CatPodcastAdapter catPodcastAdapter;

    public static CategoryPodcastsFragment newInstance() {
        return new CategoryPodcastsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cat_podcast_frag, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void setPresenter(CategoryPodcastsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {

    }



    @Override
    public void onGetDataSuccess(String message, List<Podcast> catPodcasts) {

    }

    @Override
    public void onGetDataFailure(String message) {

    }
}