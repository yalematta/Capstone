package com.yalematta.earcasts.ui.podcasts.details;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.ui.podcasts.episodes.PodcastEpisodesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/26/18.
 */

public class PodcastBottomDialogFragment extends BottomSheetDialogFragment implements PodcastBottomDialogContract.View, View.OnClickListener {

    private int mPodcastId;
    private Podcast selectedPodcast;
    private static final String PODCAST = "PODCAST";
    private static final String PODCAST_ID = "PODCAST_ID";
    private PodcastBottomDialogContract.Presenter mPresenter;

    private AnimatedVectorDrawable mAddDrawable;
    private AnimatedVectorDrawable mCheckDrawable;
    private boolean mFabFlag;

    @BindView(R.id.image) ImageView ivLogo;
    @BindView(R.id.title) TextView tvTitle;
    @BindView(R.id.subtitle) TextView tvSubtitle;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.description) TextView tvDescription;
    @BindView(R.id.bottomsheet) BottomSheetLayout bottomSheetLayout;

    public static PodcastBottomDialogFragment newInstance(int podcastId) {
        PodcastBottomDialogFragment fragment = new PodcastBottomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(PODCAST_ID, podcastId);
        fragment.setArguments(args);
        return fragment;
    }

    private int getPodcastId() {
        if (getArguments() != null) {
            return getArguments().getInt(PODCAST_ID);
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = View.inflate(getContext(), R.layout.podcast_bottom, null);

        ButterKnife.bind(this, contentView);
        if (getPodcastId() != 0) {
            mPodcastId = getPodcastId();
            mPresenter.getPodcastData(mPodcastId);
        }

        mAddDrawable = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.ic_add_animatable);
        mCheckDrawable = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.ic_check_animatable);

        bottomSheetLayout.shouldDimContentView();
        bottomSheetLayout.showWithSheetView(LayoutInflater.from(getActivity()).inflate(R.layout.podcast_bottom, bottomSheetLayout, false));

        fab.setOnClickListener(this);

        return contentView;
    }

    public void fabClick() {
        if (mFabFlag) {
            Intent intent = new Intent(getContext(), PodcastEpisodesActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putInt(PODCAST_ID, mPodcastId);
            mBundle.putParcelable(PODCAST, selectedPodcast);
            intent.putExtras(mBundle);
            startActivity(intent);
        } else {
            fab.setImageDrawable(mAddDrawable);
            mAddDrawable.start();
            mFabFlag = !mFabFlag;
        }
    }

    @Override
    public void setPresenter(PodcastBottomDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(String message, Podcast podcast) {

        selectedPodcast = new Podcast();
        selectedPodcast = podcast;

        tvTitle.setText(podcast.getTitle());
        tvSubtitle.setText(getString(R.string.subtitle));
        tvDescription.setText(podcast.getSubtitle());

        Glide.with(getActivity())
                .load(podcast.getSmallImageURL())
                .into(ivLogo);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                fabClick();
        }
    }
}
