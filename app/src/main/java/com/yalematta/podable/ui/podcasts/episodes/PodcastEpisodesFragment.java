package com.yalematta.podable.ui.podcasts.episodes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Episode;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.podcasts.adapter.EpisodeAdapter;
import com.yalematta.podable.ui.podcasts.details.PodcastBottomDialogFragment;
import com.yalematta.podable.ui.podcasts.details.PodcastBottomDialogPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

/**
 * Created by yalematta on 7/10/18.
 */

public class PodcastEpisodesFragment extends Fragment implements PodcastEpisodesContract.View, PodcastEpisodesContract.onEpisodeClickListener {

    private Podcast currentPodcast;
    private EpisodeAdapter episodeAdapter;
    private LinearLayoutManager linearLayoutManager;
    private static final String PODCAST = "PODCAST";
    private PodcastEpisodesContract.Presenter mPresenter;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayout.VERTICAL));
    }

    @Override
    public void setPresenter(PodcastEpisodesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {

    }

    @Override
    public void onGetDataSuccess(String message, Podcast podcast) {
        episodeAdapter = new EpisodeAdapter(this.getContext(), podcast, this);
        recyclerView.setAdapter(episodeAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

    @Override
    public void onEpisodeClick(Episode episode, String podcastSlug) {

        verifyStoragePermissions(getActivity());

        if (isConnected())
            new DownloadEpisode(this.getActivity(), episode, podcastSlug, episode.getEnclosure());
        else
            Toast.makeText(this.getActivity(), "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
    }

    // Check if internet is present or not
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    // Verify if permission is granted
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
