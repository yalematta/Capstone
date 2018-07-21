package com.yalematta.podable.ui.podcasts.episodes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Episode;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.podcasts.adapter.EpisodeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/10/18.
 */

public class PodcastEpisodesFragment extends Fragment implements PodcastEpisodesContract.View {

    private Podcast currentPodcast;
    private EpisodeAdapter episodeAdapter;
    private LinearLayoutManager linearLayoutManager;
    private static final String PODCAST = "PODCAST";
    private PodcastEpisodesContract.Presenter mPresenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.episodes_frag, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        currentPodcast = getArguments().getParcelable(PODCAST);
        mPresenter.getEpisodes(currentPodcast.getId(), 0, 10);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setPresenter(PodcastEpisodesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {

    }

    @Override
    public void onGetDataSuccess(String message, List<Episode> episodes) {
        episodeAdapter = new EpisodeAdapter(this.getContext(), episodes);
        recyclerView.setAdapter(episodeAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}
