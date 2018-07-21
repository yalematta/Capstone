package com.yalematta.podable.ui.podcasts.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.category.Category;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.main.featured.FeaturedPresenterImpl;
import com.yalematta.podable.ui.podcasts.adapter.CatPodcastAdapter;
import com.yalematta.podable.ui.podcasts.details.PodcastBottomDialogFragment;
import com.yalematta.podable.ui.podcasts.details.PodcastBottomDialogPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/14/18.
 */

public class CategoryPodcastsFragment extends Fragment implements CategoryPodcastsContract.View, CategoryPodcastsContract.onPodcastClickListener {

    private static final String CATEGORY = "CATEGORY";
    private CategoryPodcastsContract.Presenter mPresenter;
    private CategoryPodcastsPresenterImpl mCategoryPodcastsPresenter;
    private PodcastBottomDialogPresenterImpl mPodcastBottomDialogPresenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private LinearLayoutManager linearLayoutManager;
    private CatPodcastAdapter catPodcastAdapter;
    private Category currentCategory;
    private int currentCategoryId;

    public static CategoryPodcastsFragment newInstance() {
        return new CategoryPodcastsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cat_podcast_frag, container, false);
        ButterKnife.bind(this, root);
        currentCategory = (Category) getArguments().get(CATEGORY);
        currentCategoryId = currentCategory.getId();
        mPresenter.getCategoryPodcastsData(currentCategoryId , 0, 20);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

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
        catPodcastAdapter = new CatPodcastAdapter(getContext(), catPodcasts, this);
        recyclerView.setAdapter(catPodcastAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

    @Override
    public void onPodcastClick(int position) {
        PodcastBottomDialogFragment bottomPodcast = PodcastBottomDialogFragment.newInstance(position);
        mPodcastBottomDialogPresenter = new PodcastBottomDialogPresenterImpl(bottomPodcast);
        bottomPodcast.show(getActivity().getSupportFragmentManager(), "whatever_tag");
    }
}