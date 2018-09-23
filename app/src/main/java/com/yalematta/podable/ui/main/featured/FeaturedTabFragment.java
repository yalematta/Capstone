package com.yalematta.podable.ui.main.featured;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.main.adapter.PodcastAdapter;
import com.yalematta.podable.ui.podcasts.details.PodcastBottomDialogFragment;
import com.yalematta.podable.ui.podcasts.details.PodcastBottomDialogPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/16/18.
 */

public class FeaturedTabFragment extends Fragment implements FeaturedContract.View, FeaturedContract.onPodcastClickListener {

    private FeaturedContract.Presenter mPresenter;
    private PodcastBottomDialogPresenterImpl mPodcastBottomDialogPresenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private GridLayoutManager gridLayoutManager;
    private PodcastAdapter podcastAdapter;

    public FeaturedTabFragment() {

    }

    public static FeaturedTabFragment newInstance() {
        return new FeaturedTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.featured_frag, container, false);
        ButterKnife.bind(this, root);
        int numberOfColumns = 3;
        mPresenter.getFeaturedData(15 , "en");
        gridLayoutManager = new GridLayoutManager(this.getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);

        return root;
    }

    @Override
    public void setPresenter(FeaturedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(String message, List<Podcast> list) {
        podcastAdapter = new PodcastAdapter(getContext(), list, this);
        recyclerView.setAdapter(podcastAdapter);
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

